package libraryAPI;

import java.sql.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseHandler {
    // Параметры подключения
    private static final String URL = "jdbc:postgresql://localhost:5433/postgres?loggerLevel=TRACE";
    private static final String USER = "postgres";
    private static final String PASSWORD = "12345678";

    public static Connection getConnection() {
        Connection connection = null;
        Logger logger = Logger.getLogger("org.postgresql");
        logger.setLevel(Level.FINEST );
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.FINEST );
        logger.addHandler(consoleHandler);

        try {
            // Регистрация драйвера (необязательно в новых версиях Java, но полезно для отладки)
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Подключение успешно!");
        } catch (ClassNotFoundException e) {
            System.err.println("Драйвер PostgreSQL не найден!");
        } catch (SQLException e) {
            System.err.println("Ошибка при подключении к БД: " + e.getMessage());
        }
        return connection;
    }
}