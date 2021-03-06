package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.models.User;

import java.util.List;

@Component
public class UserServiceImp implements UserService{

    @Qualifier("jpaUserDaoImp")
    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public void create(User user) {
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
        userDao.update(user);
    }
    @Transactional
    @Override
    public void delete(int id) {
        userDao.delete(id);
    }
}
