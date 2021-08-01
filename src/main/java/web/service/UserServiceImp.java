package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.models.Role;
import web.models.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserServiceImp implements UserService{

    @Qualifier("jpaUserDaoImp")
    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    @Override
    public void create(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList()).contains("sad");
        userDao.create(user);
    }
    @Transactional(readOnly = true)
    @Override
    public List<User> readAll() {
        return userDao.readAll();
    }
    @Transactional
    @Override
    public User readById(int id) {
        return userDao.readById(id);
    }
    @Transactional
    @Override
    public void update(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.update(user);
    }
    @Transactional
    @Override
    public void delete(int id) {
        userDao.delete(id);
    }
}
