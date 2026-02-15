package org.example.dao;

import org.example.model.Device;
import org.example.model.DeviceConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * DAO класс для управления объектами устройств (Device) в базе данных.
 * Обеспечивает CRUD операции и специализированные выборки из таблицы networks.devices,
 * а также связь с соединениями устройств.
 */
public class DeviceDao extends Dao {

    /** DAO для работы с соединениями устройств. */
    private DeviceConnectionDao deviceConnectionDao = new DeviceConnectionDao();

    /**
     * Конструктор, инициализирующий драйвер PostgreSQL.
     * @throws ClassNotFoundException если драйвер PostgreSQL не найден.
     */
    public DeviceDao() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
    }

    /**
     * Сохраняет новое устройство в базу данных.
     *
     * @param device объект устройства для сохранения.
     * @return сохраненный объект с присвоенным ID и датой создания.
     * @throws SQLException при ошибке выполнения SQL-запроса.
     */
    public Device save(Device device) throws SQLException {
        try (var connection = openConnection()) {
            try (var statement = connection.prepareStatement("insert into networks.devices (name, ip_address, mac_address, type, status, network_id) values (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, device.getName());
                statement.setString(2, device.getIpAddress());
                statement.setString(3, device.getMacAddress());
                statement.setString(4, device.getType());
                statement.setString(5, device.getStatus());
                statement.setLong(6, device.getNetworkId());
                statement.execute();

                var result = statement.getGeneratedKeys();
                result.next();
                device.setId(result.getLong("id"));
                device.setCreatedAt(result.getTimestamp("created_at"));
                return device;
            }
        }
    }

    /**
     * Маппинг текущей строки ResultSet в объект Device.
     *
     * @param resultSet результат SQL-запроса.
     * @return объект {@link Device}.
     * @throws SQLException при ошибке чтения данных из ResultSet.
     */
    public Device toDeviceModel(ResultSet resultSet) throws SQLException {
        Device device = new Device();
        device.setId(resultSet.getLong("id"));
        device.setName(resultSet.getString("name"));
        device.setNetworkId(resultSet.getLong("network_id"));
        device.setIpAddress(resultSet.getString("ip_address"));
        device.setMacAddress(resultSet.getString("mac_address"));
        device.setStatus(resultSet.getString("status"));
        device.setType(resultSet.getString("type"));
        device.setCreatedAt(resultSet.getTimestamp("created_at"));
        return device;
    }

    /**
     * Получает список всех устройств из таблицы.
     *
     * @return список объектов Device.
     * @throws SQLException при ошибке доступа к БД.
     */
    public List<Device> getAllDevices() throws SQLException {
        try (var connection = openConnection()) {
            try (var statement = connection.createStatement()) {
                var result = statement.executeQuery("select * from networks.devices");
                List<Device> devices = new ArrayList<>();
                while (result.next()) {
                    devices.add(toDeviceModel(result));
                }
                return devices;
            }
        }
    }

    /**
     * Выполняет поиск устройств по точному совпадению в указанной колонке.
     *
     * @param column имя колонки в таблице.
     * @param value строковое значение для поиска.
     * @return список найденных устройств.
     * @throws SQLException при ошибке выполнения запроса.
     */
    public List<Device> findDeviceBy (String column, String value) throws SQLException {;
        try (var connection = openConnection()) {
            try (var statement = connection.createStatement()) {
                var result = statement.executeQuery("select * from networks.devices where " + column + "='" + value + "'");
                List<Device> devices = new ArrayList<>();
                while (result.next()) {
                    devices.add(toDeviceModel(result));
                }
                return devices;
            }
        }
    }

    /**
     * Фильтрует устройства с использованием оператора сравнения.
     *
     * @param column имя колонки.
     * @param value значение для сравнения.
     * @param operator оператор сравнения (например, '>', '<', '=').
     * @return список отфильтрованных устройств.
     * @throws SQLException при ошибке выполнения запроса.
     */
    public List<Device> filterDeviceBy (String column, String value, Character operator) throws SQLException {
        try (var connection = openConnection()) {
            try (var statement = connection.createStatement()) {
                var result = statement.executeQuery("select * from networks.devices where " + column + operator + "'" + value + "'");
                List<Device> devices = new ArrayList<>();
                while (result.next()) {
                    devices.add(toDeviceModel(result));
                }
                return devices;
            }
        }
    }

    /**
     * Получает мапу устройств и их сетевых атрибутов (экспериментальный метод).
     *
     * @return HashMap, где ключ — Device, значение — объект соединения.
     * @throws SQLException при ошибке запроса.
     * @deprecated Метод требует доработки логики маппинга (см. TODO в коде).
     */
    public HashMap<Device, DeviceConnection> getAllDevicesWithConnection()  throws SQLException {
        HashMap<Device, DeviceConnection> connections = new HashMap<>();
        try (var connection = openConnection()) {
            try (var statement = connection.createStatement()) {
                var result = statement.executeQuery("select networks.devices.name as device_name, n.name as network_name from networks.devices" +
                        " left join networks.networks n on networks.devices.network_id = n.id");
                while (result.next()) {
                    var device = toDeviceModel(result);
                    if (device != null) {
                        var deviceConnection = connections.get(device);
                        if (deviceConnection != null) {
                            connections.put(device, deviceConnection);
                        };
                    }
                }
                return connections;
            }
        }
    }

    /**
     * Получает устройства вместе с их активными соединениями через JOIN.
     *
     * @return HashMap с парами Устройство-Соединение.
     * @throws SQLException при ошибке доступа к данным.
     */
    public HashMap<Device, DeviceConnection> getAllConnectionsByDevices() throws SQLException {
        HashMap<Device, DeviceConnection> connections = new HashMap<>();
        try (var connection = openConnection()) {
            try (var statement = connection.prepareStatement("SELECT d.*, n.* FROM networks.devices d JOIN networks.connections n ON d.id = n.device_from_id")) {
                var result = statement.executeQuery();
                while (result.next()) {
                    var device = toDeviceModel(result);
                    if (device != null) {
                        var deviceConnection = connections.get(device);
                        if (deviceConnection != null) {
                            connections.put(device, deviceConnection);
                        }
                    }
                }
            }
        }
        return connections;
    }
}
