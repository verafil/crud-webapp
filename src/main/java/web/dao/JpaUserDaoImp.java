package web.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.expression.Sets;
import web.models.Role;
import web.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;

@Component
@Transactional(readOnly = true)
public class JpaUserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void create(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> readAll() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public List<Role> readAllRoles() {
        return entityManager.createQuery("select u from Role u", Role.class).getResultList();
    }

    @Override
    public User readById(int id) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.id=:id", User.class);
        query.setParameter("id", id);
        //return query.getSingleResult();
        return query.getResultList().stream().findAny().orElse(null);
    }

    @Override
    public User getUserByName(String username) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.username=:username", User.class);
        query.setParameter("username", username);
        return query.getResultList().stream().findAny().orElse(null);
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public void delete(int id) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.id=:id", User.class);
        query.setParameter("id", id);
        entityManager.remove(query.getResultList().stream().findAny().orElse(null));
    }
}
