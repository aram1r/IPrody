package org.example.dao;

import org.example.model.Device;
import org.example.model.DeviceConnection;
import org.example.model.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * DAO класс для управления объектами соединений устройств (DeviceConnection) в базе данных.
 * Предоставляет методы для создания, поиска и удаления записей в таблице networks.connections.
 */
public class DeviceConnectionDao extends Dao {

    /**
     * Конструктор, инициализирующий драйвер PostgreSQL.
     * @throws ClassNotFoundException если драйвер PostgreSQL не найден в classpath.
     */
    public DeviceConnectionDao() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
    }

    /**
     * Сохраняет новое соединение в базу данных.
     * Автоматически устанавливает объекту сгенерированный ID и дату создания.
     *
     * @param deviceConnection объект соединения для сохранения.
     * @return сохраненный объект с заполненными полями id и createdAt.
     * @throws SQLException при ошибке выполнения SQL-запроса.
     */
    public DeviceConnection save(DeviceConnection deviceConnection) throws SQLException {
        try (var connection = openConnection()) {
            try (var statement = connection.prepareStatement("insert into networks.connections (device_from_id, device_to_id, status) values (?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
                statement.setLong(1, deviceConnection.getDeviceFromId());
                statement.setLong(2, deviceConnection.getDeviceToId());
                // В вашем коде было 4 параметра в set, но 3 в SQL запросе. Исправлено логически:
                statement.setString(3, deviceConnection.getStatus());
                statement.execute();

                var result = statement.getGeneratedKeys();
                result.next();
                deviceConnection.setId(result.getLong("id"));
                deviceConnection.setCreatedAt(result.getTimestamp("created_at"));
                return deviceConnection;
            }
        }
    }

    /**
     * Преобразует строку ResultSet в объект DeviceConnection.
     *
     * @param resultSet текущая строка результата запроса.
     * @return объект DeviceConnection с данными из БД.
     * @throws SQLException при ошибке чтения данных из ResultSet.
     */
    public DeviceConnection toConnectionModel(ResultSet resultSet) throws SQLException {
        DeviceConnection deviceConnection = new DeviceConnection();
        deviceConnection.setId(resultSet.getLong("id"));
        deviceConnection.setDeviceToId(resultSet.getLong("device_to_id"));
        deviceConnection.setDeviceFromId(resultSet.getLong("device_from_id"));
        deviceConnection.setStatus(resultSet.getString("status"));
        deviceConnection.setType(resultSet.getString("type"));
        deviceConnection.setCreatedAt(resultSet.getTimestamp("created_at"));
        return deviceConnection;
    }

    /**
     * Удаляет запись соединения из таблицы по умолчанию.
     *
     * @param deviceConnection объект для удаления.
     * @throws SQLException при ошибке доступа к БД.
     */
    public void remove(DeviceConnection deviceConnection) throws SQLException {
        remove(deviceConnection, "connections");
    }

    /**
     * Универсальный метод удаления записи из указанной таблицы по ID.
     *
     * @param model объект модели, содержащий ID.
     * @param tableName имя таблицы в схеме networks.
     * @throws SQLException при ошибке выполнения удаления.
     */
    public void remove(Model model, String tableName) throws SQLException {
        try (var connection = openConnection()) {
            try (var statement = connection.prepareStatement("delete from networks." + tableName + " where id = ?")) {
                statement.setLong(1, model.getId());
                statement.execute();
            }
        }
    }

    /**
     * Возвращает список всех соединений из базы данных.
     *
     * @return список объектов {@link DeviceConnection}.
     * @throws SQLException при ошибке запроса.
     */
    public List<DeviceConnection> getAllConnections() throws SQLException {
        try (var connection = openConnection()) {
            try (var statement = connection.createStatement()) {
                var result = statement.executeQuery("select * from networks.connections");
                List<DeviceConnection> connections = new ArrayList<>();
                while (result.next()) {
                    connections.add(toConnectionModel(result));
                }
                return connections;
            }
        }
    }

    /**
     * Поиск соединения по его уникальному идентификатору.
     *
     * @param id идентификатор записи.
     * @return объект DeviceConnection или null, если запись не найдена.
     * @throws SQLException при ошибке запроса.
     */
    public DeviceConnection getConnectionById(int id) throws SQLException {
        DeviceConnection deviceConnection = null;
        try (var connection = openConnection()) {
            try (var statement = connection.prepareStatement("select * from networks.connections where id = ?")) {
                statement.setInt(1, id);
                var result = statement.executeQuery();
                if (result.next()) {
                    deviceConnection = toConnectionModel(result);
                }
            }
        }
        return deviceConnection;
    }

    /**
     * Поиск первого найденного соединения по ID исходящего устройства.
     *
     * @param deviceId ID устройства-отправителя.
     * @return объект DeviceConnection или null.
     * @throws SQLException при ошибке запроса.
     */
    public DeviceConnection getConnectionByDeviceFromId(int deviceId) throws SQLException {
        DeviceConnection deviceConnection = null;
        try (var connection = openConnection()) {
            try (var statement = connection.prepareStatement("select * from networks.connections where device_from_id = ?")) {
                statement.setInt(1, deviceId);
                var result = statement.executeQuery();
                if (result.next()) {
                    deviceConnection = toConnectionModel(result);
                }
            }
        }
        return deviceConnection;
    }

    /**
     * Поиск первого найденного соединения по ID входящего устройства.
     *
     * @param deviceFromId ID устройства-получателя.
     * @return объект DeviceConnection или null.
     * @throws SQLException при ошибке запроса.
     */
    public DeviceConnection getConnectionByDeviceToId(int deviceFromId) throws SQLException {
        DeviceConnection deviceConnection = null;
        try (var connection = openConnection()) {
            try (var statement = connection.prepareStatement("select * from networks.connections where device_to_id = ?")) {
                statement.setInt(1, deviceFromId);
                var result = statement.executeQuery();
                if (result.next()) {
                    deviceConnection = toConnectionModel(result);
                }
            }
        }
        return deviceConnection;
    }

    /**
     * Поиск соединения по точной дате и времени создания.
     *
     * @param createdAt дата создания.
     * @return объект DeviceConnection или null.
     * @throws SQLException при ошибке запроса.
     */
    public DeviceConnection getConnectionByCreatedAt(Date createdAt) throws SQLException {
        DeviceConnection deviceConnection = null;
        try (var connection = openConnection()) {
            try (var statement = connection.prepareStatement("select * from networks.connections where created_at = ?")) {
                statement.setTimestamp(1, new Timestamp(createdAt.getTime()));
                var result = statement.executeQuery();
                if (result.next()) {
                    deviceConnection = toConnectionModel(result);
                }
            }
        }
        return deviceConnection;
    }

    /**
     * Поиск соединения по его статусу.
     *
     * @param status строковое представление статуса.
     * @return объект DeviceConnection или null.
     * @throws SQLException при ошибке запроса.
     */
    public DeviceConnection getConnectionByStatus(String status) throws SQLException {
        DeviceConnection deviceConnection = null;
        try (var connection = openConnection()) {
            try (var statement = connection.prepareStatement("select * from networks.connections where status = ?")) {
                statement.setString(1, status);
                var result = statement.executeQuery();
                if (result.next()) {
                    deviceConnection = toConnectionModel(result);
                }
            }
        }
        return deviceConnection;
    }
}
