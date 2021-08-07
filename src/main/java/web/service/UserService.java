package web.service;

import web.models.User;

import java.util.List;

public interface UserService {

    public User findByUsername(String userName);

    public void save(User user);

    public List<User> readAll();


    public User readById(int id);

    public void delete(int id);

}
