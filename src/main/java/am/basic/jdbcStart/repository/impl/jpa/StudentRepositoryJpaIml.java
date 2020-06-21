//package am.basic.jdbcStart.repository.impl.jpa;
//
//
//import am.basic.jdbcStart.model.Student;
//import am.basic.jdbcStart.repository.impl.StudentRepository;
//import am.basic.jdbcStart.util.HibernateUtil;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//
//
//import java.sql.SQLException;
//import java.util.List;
//
//public class StudentRepositoryJpaIml implements StudentRepository {
//    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//
//    @Override
//    public void update(Student student) {
//        Session s = sessionFactory.openSession();
//        s.beginTransaction();
//        s.update(student);
//        s.getTransaction().commit();
//        s.close();
//    }
//
//    @Override
//    public Student getByNameAndSurname(String name, String surname) throws SQLException {
//        return null;
//    }
//
//    @Override
//    public List<Student> getAll() throws SQLException {
//        return null;
//    }
//
//    @Override
//    public Student getById(int id)    {
//        Session s = sessionFactory.openSession();
//        Student student = s.find(Student.class, id);
//        return student;
//    }
//
//    @Override
//    public List<Student> findByNameAndSurname(String name, String surname) throws SQLException {
//        return null;
//    }
//
//    @Override
//    public void add(Student student) throws SQLException {
//
//    }
//
//    @Override
//    public void delete(int id) throws SQLException {
//
//    }
//
//    @Override
//    public void transfer(Student from, Student to, int amount) {
//
//    }
//}
