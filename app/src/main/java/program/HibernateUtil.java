package program;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static SessionFactory sessionFactory = buildSessionFactor();

    private static SessionFactory buildSessionFactor() {
        try {
            if(sessionFactory == null) {
                var standardRegistery = new StandardServiceRegistryBuilder()
                        .configure("hibernate.cfg.xml").build();

                var metaData = new MetadataSources(standardRegistery)
                        .getMetadataBuilder().build();
                sessionFactory = metaData.getSessionFactoryBuilder().build();
            }
            return sessionFactory;
        } catch (HibernateException e) {
            System.err.println("Initial sessionFactory creation failed." + e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        sessionFactory.close();
    }
}
