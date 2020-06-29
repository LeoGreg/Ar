package am.basic.jdbcStart.repository.impl.spring.jpa;

import am.basic.jdbcStart.model.Book;
import am.basic.jdbcStart.model.Faculty;
import am.basic.jdbcStart.model.Student;
import am.basic.jdbcStart.model.Teacher;
import am.basic.jdbcStart.repository.impl.StudentRepository;
import am.basic.jdbcStart.repository.impl.spring.jpa.studentEditingInfo.StudentTransferInfo;
import am.basic.jdbcStart.repository.impl.spring.jpa.studentEditingInfo.TransactionRepositoryOfStudent;
import am.basic.jdbcStart.repository.impl.spring.jpa.studentEditingInfo.WalletOfStudent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Transactional
public class StudentRepSpringJpa {
    private StudentTransferInfo studentTransferInfo;
    private WalletOfStudent walletOfStudent;
    private TransactionRepositoryOfStudent transactionRepositoryOfStudent;

    public StudentTransferInfo getStudentTransferInfo() {
        return studentTransferInfo;
    }

    public void setStudentTransferInfo(StudentTransferInfo studentTransferInfo) {
        this.studentTransferInfo = studentTransferInfo;
    }

    public TransactionRepositoryOfStudent getTransactionRepositoryOfStudent() {
        return transactionRepositoryOfStudent;
    }

    public void setTransactionRepositoryOfStudent(TransactionRepositoryOfStudent transactionRepositoryOfStudent) {
        this.transactionRepositoryOfStudent = transactionRepositoryOfStudent;
    }

    public WalletOfStudent getWalletOfStudent() {
        return walletOfStudent;
    }

    public void setWalletOfStudent(WalletOfStudent walletOfStudent) {
        this.walletOfStudent = walletOfStudent;
    }

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void update(Student student) {
        sessionFactory.getCurrentSession().update(student);
    }

    public Student getByNameAndSurname(String name, String surname) {
        return sessionFactory.getCurrentSession().createNativeQuery("SELECT * FROM student WHERE name=:Name AND surname=:Surname", Student.class).setParameter("Name", name).setParameter("Surname", surname).uniqueResult();
    }

    public List<Student> getAll() {
        return sessionFactory.getCurrentSession().createNativeQuery("SELECT * FROM student ", Student.class).getResultList();
    }

    public Student getByIdAll(int id) {
        return sessionFactory.getCurrentSession().find(Student.class, id);
    }

    public Faculty getByIdFaculty(int id) {
        return sessionFactory.getCurrentSession().find(Student.class, id).getFaculty();
    }

    public List<Book> getByIdBooks(int id) {
        return sessionFactory.getCurrentSession().find(Student.class, id).getBooks();
    }

    public List<Teacher> getByIdTeachers(int id) {
        return sessionFactory.getCurrentSession().find(Student.class, id).getTeachers();
    }

    public List<Student> findByNameAndSurname(String name, String surname) {
        return sessionFactory.getCurrentSession().createNativeQuery("SELECT * FROM student WHERE name LIKE('%',:Name,'%') AND LIKE('%',:Surname,'%')", Student.class).getResultList();
    }

    public void add(Student student) {
        sessionFactory.getCurrentSession().save(student);
    }

    public void delete(int id) {
        sessionFactory.getCurrentSession().delete(getByIdAll(id));
    }

    public void transfer(int fromId, int toId, int amount) {
        try {
            studentTransferInfo.getInfo(fromId, toId, amount);
        } catch (RuntimeException e) {
            System.out.println("info is available");
        }
        Session session = sessionFactory.getCurrentSession();
        walletOfStudent.editAmount(fromId, amount * -1);
        walletOfStudent.editAmount(toId, amount);
        transactionRepositoryOfStudent.save(fromId, toId);
    }

}
