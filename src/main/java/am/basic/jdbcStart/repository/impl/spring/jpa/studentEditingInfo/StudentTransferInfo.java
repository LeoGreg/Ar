package am.basic.jdbcStart.repository.impl.spring.jpa.studentEditingInfo;

import am.basic.jdbcStart.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class StudentTransferInfo {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void getInfo(int userFrom, int userTo, double amount) {
        Session session = sessionFactory.getCurrentSession();
        Student student = session.find(Student.class, userFrom);
        Student student1 = session.find(Student.class, userTo);
        System.out.println("money transfer from " + student.getName() + " : " + student.getSurname() + " to " + student1.getName() + " : " + student1.getSurname() + "\n" + "transfering money : " + amount);

    }
}
