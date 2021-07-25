package web.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.models.User;

import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class UserDaoImp implements UserDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void create(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> readAll() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
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
    public void delete(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User a where a.id = :id");
        sessionFactory.getCurrentSession().delete((User) query.setParameter("id", id).uniqueResult());
    }
}
