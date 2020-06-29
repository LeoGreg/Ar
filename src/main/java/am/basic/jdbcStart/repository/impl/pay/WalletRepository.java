package am.basic.jdbcStart.repository.impl.pay;

import am.basic.jdbcStart.Run;
import am.basic.jdbcStart.model.MasterCard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.omg.SendingContext.RunTime;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class WalletRepository {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void save(MasterCard masterCard) {
        Session session = sessionFactory.getCurrentSession();
        session.save(masterCard);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void editAmount(int userId, double amount) {

        Session session = sessionFactory.getCurrentSession();
        MasterCard masterCard = session.find(MasterCard.class, userId);
        masterCard.setBalance(masterCard.getBalance() + amount);
    }
}
