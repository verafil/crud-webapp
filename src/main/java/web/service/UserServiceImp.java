package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import web.dto.UserDto;
import web.models.Role;
import web.models.User;
import web.repository.RoleRepository;
import web.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImp implements UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User findByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public void updateUser(UserDto userDto) {
        User user = fromUserDtoToUser(userDto);
   //     user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = fromUserDtoToUser(userDto);
  //      user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public List<User> readAll() {
        return userRepository.findAll();
    }

    @Override
    public User readById(int id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    private User fromUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        if (userDto.getPassword().isEmpty()) {
            user.setPassword(userRepository.findById(userDto.getId()).get().getPassword());
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        }
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setAge(userDto.getAge());

        Set<Role> roles = new LinkedHashSet<>();
        if (userDto.getRoles() != null) {
            for (String roleName : userDto.getRoles()) {
                Role roleInBase = roleRepository.findByName(roleName);
                if (roleInBase != null) {
                    roles.add(roleInBase);
                } else {
                    roles.add(new Role(roleName));
                }
            }
        } else {
            roles = null;
        }
        user.setRoles(roles);
        return user;
    }
}
