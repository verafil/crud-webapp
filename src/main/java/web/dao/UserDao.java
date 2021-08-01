package web.dao;

import web.models.User;

import java.util.List;

public interface UserDao {
    void create(User user);

    List<User> readAll();

    User readById(int id);

    void update(User user);

    void delete(int id);

    User getUserByName(String name);

}
