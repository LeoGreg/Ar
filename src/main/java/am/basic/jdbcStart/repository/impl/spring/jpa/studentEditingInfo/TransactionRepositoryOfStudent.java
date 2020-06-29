package am.basic.jdbcStart.repository.impl.spring.jpa.studentEditingInfo;

import am.basic.jdbcStart.model.MasterCard;
import am.basic.jdbcStart.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class TransactionRepositoryOfStudent {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
     @Transactional(propagation = Propagation.REQUIRED)
    public void save(int userFromId, int userToId) {
        Session session = sessionFactory.getCurrentSession();
        Student student = session.find(Student.class, userFromId);
        session.update(student);
        Student student1 = session.find(Student.class, userToId);
        session.update(student1);
    }
}
