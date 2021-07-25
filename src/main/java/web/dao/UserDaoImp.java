package web.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.models.User;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public List<User> readAll() {
        return (List<User>) sessionFactory.getCurrentSession().createQuery("from User").list();
    }

    @Override
    public User readById(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User a where a.id = :id");
        return (User) query.setParameter("id", id).uniqueResult();
    }

    @Override
    public void update(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public void delete(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }
}
