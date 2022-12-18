package CheckRunner.dataplaceholders;

import CheckRunner.config.Config;
import CheckRunner.entity.DiscountCard;
import CheckRunner.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class FillDataFromPostgreSQL {

    public static void fillProductList(ArrayList<Product> allProducts) {

        try (SessionFactory factory = new Configuration()
                .setProperty("hibernate.connection.driver_class", Config.getProperty(Config.HIBERNATE_DRIVER_CLASS))
                .setProperty("hibernate.connection.url", Config.getProperty(Config.HIBERNATE_URL))
                .setProperty("hibernate.connection.username", Config.getProperty(Config.HIBERNATE_USERNAME))
                .setProperty("hibernate.connection.password", Config.getProperty(Config.HIBERNATE_PASSWORD))
                .setProperty("hibernate.current_session_context_class", Config.getProperty(Config.HIBERNATE_SESSION_CONTEXT_CLASS))
                .setProperty("hibernate.dialect", Config.getProperty(Config.HIBERNATE_DIALECT))
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();
             Session session = factory.getCurrentSession()) {

            session.beginTransaction();

            List<Product> products = session.createQuery("FROM Product", Product.class).getResultList();
            allProducts.addAll(products);

            session.getTransaction().commit();
        }
    }

    public static void fillDiscountCardList(ArrayList<DiscountCard> allDiscountCards) {

        try (SessionFactory factory = new Configuration()
                .setProperty("hibernate.connection.driver_class", Config.getProperty(Config.HIBERNATE_DRIVER_CLASS))
                .setProperty("hibernate.connection.url", Config.getProperty(Config.HIBERNATE_URL))
                .setProperty("hibernate.connection.username", Config.getProperty(Config.HIBERNATE_USERNAME))
                .setProperty("hibernate.connection.password", Config.getProperty(Config.HIBERNATE_PASSWORD))
                .setProperty("hibernate.current_session_context_class", Config.getProperty(Config.HIBERNATE_SESSION_CONTEXT_CLASS))
                .setProperty("hibernate.dialect", Config.getProperty(Config.HIBERNATE_DIALECT))
                .addAnnotatedClass(DiscountCard.class)
                .buildSessionFactory();
             Session session = factory.getCurrentSession()) {

            session.beginTransaction();

            List<DiscountCard> discountCards = session.createQuery("FROM DiscountCard", DiscountCard.class).getResultList();
            allDiscountCards.addAll(discountCards);

            session.getTransaction().commit();
        }
    }
}
