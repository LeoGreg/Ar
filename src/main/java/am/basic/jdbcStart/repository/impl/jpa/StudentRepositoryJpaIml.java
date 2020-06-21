package am.basic.jdbcStart.repository.impl.jpa;


import am.basic.jdbcStart.model.Student;
import am.basic.jdbcStart.repository.impl.StudentRepository;
import am.basic.jdbcStart.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import java.util.List;

public class StudentRepositoryJpaIml implements StudentRepository {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public void update(Student student) {
        Session s = sessionFactory.openSession();
        s.beginTransaction();
        s.update(student);
        s.getTransaction().commit();
        s.close();
    }

    @Override
    public Student getByNameAndSurname(String name, String surname) {
        Session session = sessionFactory.openSession();
        String native_query = "SELECT * FROM student " +
                "WHERE " +
                "name =: Name " +
                "AND" +
                "surname=:Surname";
        NativeQuery<Student> nativeQuery = session.createNativeQuery(native_query, Student.class);
        nativeQuery.setParameter("Name", name);
        nativeQuery.setParameter("Surname", surname);
        Student student = nativeQuery.uniqueResult();
        return student;
    }

    @Override
    public List<Student> getAll() {
        List<Student> list = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            String query = "SELECT * FROM student";
            NativeQuery<Student> nativeQuery = session.createNativeQuery(query, Student.class);
            list = nativeQuery.getResultList();
            if (list == null || session == null) {
                throw new InternalError();
            }
        } catch (InternalError e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;

    }

    @Override
    public Student getById(int id) {
        Session s = sessionFactory.openSession();
        Student student = s.find(Student.class, id);
        return student;
    }

    @Override
    public List<Student> findByNameAndSurname(String name, String surname) {
        String query = "SELECT * FROM student WHERE name LIKE(CONCAT('%',:nameParameter,'%')) AND  surname LIKE(CONCAT('%',:surnameParam,'%'))";
        Session session = sessionFactory.openSession();
        NativeQuery<Student> nativeQuery = session.createNativeQuery(query, Student.class);
        nativeQuery.setParameter("nameParameter", name);
        nativeQuery.setParameter("surnameParam", surname);
        List<Student> users = nativeQuery.getResultList();
        return users;
    }


    @Override
    public void add(Student student) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(student);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Student student = getById(id);
        session.beginTransaction();
        session.delete(student);
        session.getTransaction().commit();

    }

    @Override
    public void transfer(Student from, Student to, int amount) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        int balanceFrom = from.getBalance();
        from.setBalance(balanceFrom - amount);
        session.update(from);
        session.getTransaction().commit();
        session.beginTransaction();
        int balanceTo = to.getBalance();
        to.setBalance(balanceTo + amount);
        session.update(to);
        session.getTransaction().commit();
        session.close();

    }
}
