package web.service;

import web.dto.UserDto;
import web.models.User;

import java.util.List;

public interface UserService {

    public User findByUsername(String userName);

    public void updateUser(UserDto user);

    public void saveUser(UserDto userDto);

    public List<User> readAll();


    public User readById(int id);

    public void delete(int id);

}
