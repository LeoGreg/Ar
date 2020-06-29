package am.basic.jdbcStart.repository.impl.pay;

import am.basic.jdbcStart.Run;
import am.basic.jdbcStart.model.MasterCard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class LogRepository {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveEventLog(String action, String details, int from, int to) {

        Session session = sessionFactory.getCurrentSession();
        MasterCard masterCard = session.find(MasterCard.class, from);
        MasterCard masterCard1 = session.find(MasterCard.class, to);
        masterCard.setAction(action);
        masterCard.setDetails(details);
        masterCard1.setDetails(details);
        masterCard1.setAction(action);
        session.update(masterCard);
        session.update(masterCard1);
        System.out.println(action + details);

    }
}
