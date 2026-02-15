package org.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Dao {

    /** Метод для открытия соединения с базой данных
     *
     * @return Connection - соединение с базой данных
     * @throws SQLException
     */
    static Connection openConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5433/postgres", "postgres", "12345678");
    }
}
