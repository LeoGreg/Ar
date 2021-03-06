package am.basic.jdbcStart.repository.impl.jpa;

import am.basic.jdbcStart.model.User;

import am.basic.jdbcStart.repository.impl.UserRepository;

import am.basic.jdbcStart.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
//import java.util.Set;


//import static am.basic.jdbcStart.util.constants.ParameterKeys.*;

public class UserRepositoryJpaImpl implements UserRepository {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//    private Set<String> set;
//    private User user;

//    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//         user = (User) request.getSession().getAttribute(USER_ATTRIBUTE_KEY);
//    }


    public void onCreate() {
//        set = new HashSet<>();
//        set.add(user.getCode());
//        System.out.println(set);
    }

    @Override
    public void add(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();

    }

    @Override
    public void update(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();

    }

    @Override
    public List<User> getAll() {
        Session session = sessionFactory.openSession();
        NativeQuery<User> nativeQuery = session.createNativeQuery("SELECT * FROM user", User.class);
        List<User> users = nativeQuery.getResultList();
        return users;

    }


    @Override
    public List<User> findByName(String name) {
        String query = "SELECT * FROM user WHERE name = %:nameParameters";
        Session session = sessionFactory.openSession();
        NativeQuery<User> nativeQuery = session.createNativeQuery(query, User.class);
        nativeQuery.setParameter("nameParameter", name);
        List<User> users = nativeQuery.getResultList();
        return users;

    }

    @Override
    public List<User> findByNameAndSurname(String name, String surname) {
        String query = "SELECT * FROM user WHERE name LIKE(CONCAT('%',:nameParameter,'%')) AND  surname LIKE(CONCAT('%',:surnameParam,'%'))";
        Session session = sessionFactory.openSession();
        NativeQuery<User> nativeQuery = session.createNativeQuery(query, User.class);
        nativeQuery.setParameter("nameParameter", name);
        nativeQuery.setParameter("surnameParam", surname);
        List<User> users = nativeQuery.getResultList();
        return users;

    }

    @Override
    public User getById(int id) {
        Session session = sessionFactory.openSession();
        User user = session.find(User.class, id);
        session.close();
        return user;
    }

    @Override
    public User getByUsername(String username) {
        String query = "SELECT * FROM user WHERE username = :username  ";
        Session session = sessionFactory.openSession();
        NativeQuery<User> nativeQuery = session.createNativeQuery(query, User.class);
        nativeQuery.setParameter("username", username);
        User user = nativeQuery.uniqueResult();
        return user;
    }

    public User getByUsernameCriteria(String username) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("username"), username));
        Query<User> query = session.createQuery(criteriaQuery);
        User user = query.uniqueResult();
        return user;
    }

    @Override
    public void delete(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
    }
}
