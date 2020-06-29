package am.basic.jdbcStart.repository.impl.spring.jpa;

import am.basic.jdbcStart.model.User;

import am.basic.jdbcStart.repository.impl.UserRepository;
import am.basic.jdbcStart.repository.impl.jpa.UserRepositoryJpaImpl;
import am.basic.jdbcStart.service.serviceInterfaces.UserServiceInterface;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.transaction.annotation.Transactional;


import java.sql.SQLException;
import java.util.List;

@Transactional
public class UserRepositorySpringJpaImpl   {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void add(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

//    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void update(User user) {
        Session session = sessionFactory.getCurrentSession();
//        String query = "UPDATE user SET name=:n,surname=:s,code=:c,username=:u,password=:p,status=:s WHERE id=:i";
//        NativeQuery<User> nativeQuery = session.createNativeQuery(query, User.class);
//        nativeQuery.setParameter("n", user.getName());
//        nativeQuery.setParameter("s", user.getSurname());
//        nativeQuery.setParameter("c", user.getCode());
//        nativeQuery.setParameter("u", user.getUsername());
//        nativeQuery.setParameter("p", user.getPassword());
//        nativeQuery.setParameter("s", user.getStatus());
//        nativeQuery.setParameter("i", user.getId());
        session.update(user);
    }
    public List<User> getAll() {
        Session session = sessionFactory.getCurrentSession();
        String query = "SELECT * FROM user";
        NativeQuery<User> nativeQuery = session.createNativeQuery(query, User.class);
        List<User> list = nativeQuery.getResultList();
        return list;
    }
    public List<User> findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        String query = "SELECT * FROM user WHERE name LIKE(CONCAT('%',:Name,'%'))";
        NativeQuery<User> nativeQuery = session.createNativeQuery(query, User.class);
        nativeQuery.setParameter("Name", name);
        return nativeQuery.getResultList();
    }
    public List<User> findByNameAndSurname(String name, String surname) {
        Session session = sessionFactory.getCurrentSession();
        String query = "SELECT * FROM user WHERE name LIKE(CONCAT('%',:Name,'%')) AND surname LIKE(CONCAT('%',:Surname,'%'))";
        NativeQuery<User> nativeQuery = session.createNativeQuery(query, User.class);
        nativeQuery.setParameter("Name", name);
        nativeQuery.setParameter("Surname", name);
        return nativeQuery.getResultList();
    }
    public User getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.find(User.class, id);
    }
    public User getByUsername(String username) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        String query = "SELECT * FROM user WHERE username=:Username";
        NativeQuery<User> nativeQuery = session.createNativeQuery(query, User.class);
        nativeQuery.setParameter("Username", username);
        return nativeQuery.uniqueResult();
    }
    public void delete(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(user);
    }
}
