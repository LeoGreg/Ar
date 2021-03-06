package am.basic.jdbcStart.util;

import am.basic.jdbcStart.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null ) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/test");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL57Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.STORAGE_ENGINE,"innodb");
                settings.put(Environment.FORMAT_SQL, "true");
                settings.put(Environment.HBM2DDL_AUTO, "update");


                configuration.setProperties(settings);


                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Comment.class);
                configuration.addAnnotatedClass(Student.class);
                configuration.addAnnotatedClass(Faculty.class);
                configuration.addAnnotatedClass(Book.class);
//                configuration.addAnnotatedClass(Teacher.class);
                configuration.addAnnotatedClass(Stock.class);
                configuration.addAnnotatedClass(Card.class);
                configuration.addAnnotatedClass(Teacher.class);
                configuration.addAnnotatedClass(Buyer.class);
                configuration.addAnnotatedClass(SaleCard.class);
                configuration.addAnnotatedClass(SaleStore.class);

//                configuration.addAnnotatedClass(Student.class);
//                configuration.addAnnotatedClass(Fakultet.class);
//                configuration.addAnnotatedClass(Book.class);
//                configuration.addAnnotatedClass(Teacher.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
