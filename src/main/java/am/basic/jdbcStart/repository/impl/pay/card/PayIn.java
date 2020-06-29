package am.basic.jdbcStart.repository.impl.pay.card;

import am.basic.jdbcStart.model.MasterCard;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

public interface PayIn {
    @Transactional()
    void transfer(int userFrom, int userTo, double amount);

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    MasterCard getAll(int id);

    @Transactional
    void update(MasterCard masterCard);
}
