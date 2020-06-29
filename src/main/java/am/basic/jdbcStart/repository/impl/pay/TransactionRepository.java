package am.basic.jdbcStart.repository.impl.pay;

import am.basic.jdbcStart.Run;
import am.basic.jdbcStart.model.MasterCard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class TransactionRepository {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(int userFromId, int userToId, double amount) {
        Session session = sessionFactory.getCurrentSession();
        MasterCard masterCard = session.find(MasterCard.class, userFromId);
        session.update(masterCard);
        MasterCard masterCard1 = session.find(MasterCard.class, userToId);
        session.update(masterCard1);
    }
}
