package org.example.dao;

import java.sql.SQLException;
import java.util.Optional;

/**
 * DAO класс для получения статистических данных о сетевой инфраструктуре.
 * Предоставляет агрегированную информацию из базы данных, такую как количество устройств в сети.
 */
public class StatisticsDao extends Dao {

    /**
     * Конструктор, инициализирующий драйвер PostgreSQL.
     * @throws ClassNotFoundException если драйвер PostgreSQL не найден в системе.
     */
    public StatisticsDao() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
    }

    /**
     * Подсчитывает общее количество устройств, имеющих статус 'online'.
     *
     * @return {@link Optional}, содержащий строковое представление количества онлайн-устройств,
     *         или пустой Optional, если данные не были получены.
     * @throws SQLException при ошибке выполнения SQL-запроса или доступа к базе данных.
     */
    public Optional<String> getAllOnlineDevices() throws SQLException {
        try (var connection = openConnection()) {
            try (var statement = connection.prepareStatement("SELECT COUNT(*) FROM networks.devices where devices.status='online'")) {
                var rs = statement.executeQuery();
                while(rs.next()) {
                    return rs.getString(1).describeConstable();
                }
            }
        }
        return Optional.empty();
    }
}