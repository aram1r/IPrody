package resources.model;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSession {
    private static final SessionFactory sessionFactory;
    static {
        try {
            Configuration configuration = new Configuration();
            configuration.setProperty("hibernate.connection.driver_class",

                    "org.postgresql.Driver");

            configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5433/postgres");

            configuration.setProperty("hibernate.connection.username",

                    "postgres");

            configuration.setProperty("hibernate.connection.password",

                    "12345678");

            configuration.setProperty("hibernate.current_session_context_class", "thread");

            configuration.setProperty("hibernate.dialect",

                    "org.hibernate.dialect.PostgreSQLDialect");

            configuration.setProperty("hibernate.show_sql", "true");
            configuration.setProperty("hibernate.format_sql", "true");
            configuration.setProperty("hibernate.hbm2ddl.auto", "none");
// Register entity classes

            configuration.addAnnotatedClass(resources.model.university.Student.class);

            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
