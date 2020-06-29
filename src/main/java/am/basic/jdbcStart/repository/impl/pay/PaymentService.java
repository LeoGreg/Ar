package am.basic.jdbcStart.repository.impl.pay;

import am.basic.jdbcStart.Run;
import am.basic.jdbcStart.model.MasterCard;
import am.basic.jdbcStart.repository.impl.pay.card.PayIn;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class PaymentService   {
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private TransactionRepository transactionRepository;
    private LogRepository logRepository;
    private WalletRepository walletRepository;

    public PaymentService(TransactionRepository transactionRepository, LogRepository logRepository, WalletRepository walletRepository) {
        this.transactionRepository = transactionRepository;
        this.logRepository = logRepository;
        this.walletRepository = walletRepository;
    }

    @Transactional()
    public synchronized void transfer(int userFrom, int userTo, double amount) {
//        try {
//            logRepository.saveEventLog("transferOfMoney", "from user " + userFrom + " to user " + userTo + " amount " + amount, userFrom, userTo);
//        } catch (Exception e) {
//            System.exit(0);
//        }
        try {
            logRepository.saveEventLog("transferOfMoney", "from user " + userFrom + " to user " + userTo + " amount " + amount, userFrom, userTo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        walletRepository.editAmount(userFrom, amount * -1);
        walletRepository.editAmount(userTo, amount);
        transactionRepository.save(userFrom, userTo, amount);
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public  MasterCard getAll(int id) {
        Session session = sessionFactory.getCurrentSession();
        MasterCard card = session.find(MasterCard.class, id);
        return card;
    }


    @Transactional
    public  void update(MasterCard masterCard)   {
        Session session = sessionFactory.getCurrentSession();
        masterCard.setYear(4);
        masterCard.setBalance(8888);
        session.update(masterCard);
    }


}

