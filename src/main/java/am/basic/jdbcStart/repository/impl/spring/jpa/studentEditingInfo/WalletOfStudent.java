package am.basic.jdbcStart.repository.impl.spring.jpa.studentEditingInfo;

import am.basic.jdbcStart.model.Student;
import am.basic.jdbcStart.service.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class WalletOfStudent {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void editAmount(int fromId, double amount) {
        Session session = sessionFactory.getCurrentSession();
        Student student = session.find(Student.class, fromId);
        student.setBalance((int) (student.getBalance() + amount));

    }
}
