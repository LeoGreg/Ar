package am.basic.jdbcStart;


import am.basic.jdbcStart.model.User;
import am.basic.jdbcStart.repository.impl.pay.PaymentService;

import am.basic.jdbcStart.repository.impl.spring.jpa.UserRepositorySpringJpaImpl;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;


public class Run {
    private SessionFactory sessionFactory;

    public static void main(String[] args) {


        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        UserRepositorySpringJpaImpl userRepositorySpringJpa = context.getBean(UserRepositorySpringJpaImpl.class);
        List<User> list = userRepositorySpringJpa.getAll();

        System.out.println(list);

/*
        PaymentService paymentService = context.getBean(PaymentService.class);
        MasterCard masterCard = paymentService.getAll(1);
        new Thread() {
            public void run() {
                paymentService.update(masterCard);
            }
        }.start();
        new Thread() {
            public void run() {
                System.out.println("111111111" + paymentService.getAll(masterCard.getId()));

            }
        }.start();
*/


    }
}



