package web.dao;

import web.models.Role;
import web.models.User;

import java.util.List;
import java.util.Set;

public interface UserDao {
    void create(User user);

    List<User> readAll();

    List<Role> readAllRoles();

    User readById(int id);

    void update(User user);

    void delete(int id);

    User getUserByName(String name);

}
