package web.service;

import web.models.User;

import java.util.List;

public interface UserService {
    void create(User user);

    List<User> readAll();

    User readById(int id);

    void update(User user);

    void delete(int id);
}
