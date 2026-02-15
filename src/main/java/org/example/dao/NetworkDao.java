package org.example.dao;

import org.example.model.Device;
import org.example.model.DeviceConnection;
import org.example.model.Model;
import org.example.model.Network;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * DAO класс для управления объектами сетей (Network) в базе данных.
 * Позволяет выполнять операции поиска, создания, обновления и удаления записей в таблице networks.networks.
 */
public class NetworkDao extends Dao{

    /**
     * Конструктор, инициализирующий драйвер PostgreSQL.
     * @throws ClassNotFoundException если драйвер PostgreSQL не найден.
     */
    public NetworkDao() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
    }

    /**
     * Возвращает список всех сетей, зарегистрированных в системе.
     *
     * @return список объектов {@link Network}.
     * @throws SQLException при ошибке доступа к базе данных.
     */
    public List<Network> getAllNetworks() throws SQLException {
        try (var connection = openConnection()) {
            try (var statement = connection.createStatement()) {
                var result = statement.executeQuery("select * from networks.networks");
                List<Network> networks = new ArrayList<>();
                while (result.next()) {
                    networks.add(toModel(result));
                }
                return networks;
            }
        }
    }

    /**
     * Возвращает список "пустых" сетей, к которым не привязано ни одно устройство.
     *
     * @return список объектов {@link Network}, не имеющих связанных записей в таблице устройств.
     * @throws SQLException при ошибке выполнения SQL-запроса.
     */
    public List<Network> getEmptyNetworks() throws SQLException {
        try (var connection = openConnection()) {
            try (var statement = connection.createStatement()) {
                var result = statement.executeQuery("select ns.id, ns.name, ns.description, ns.created_at from networks.networks ns left join networks.devices nd on nd.network_id = ns.id where nd.id is null");
                List<Network> networks = new ArrayList<>();
                while (result.next()) {
                    networks.add(toModel(result));
                }
                return networks;
            }
        }
    }

    /**
     * Создает новый объект Network на основе текущей строки ResultSet.
     *
     * @param resultSet результат запроса к БД.
     * @return заполненный объект {@link Network}.
     * @throws SQLException при ошибке чтения данных.
     */
    public Network toModel(ResultSet resultSet) throws SQLException {
        return toModel(resultSet, new Network());
    }

    /**
     * Заполняет существующий объект Network данными из ResultSet.
     *
     * @param resultSet результат запроса к БД.
     * @param network объект, в который будут записаны данные.
     * @return переданный объект {@link Network} с обновленными полями.
     * @throws SQLException при ошибке чтения данных.
     */
    public Network toModel(ResultSet resultSet, Network network) throws SQLException {
        network.setId(resultSet.getLong("id"));
        network.setName(resultSet.getString("name"));
        network.setDescription(resultSet.getString("description"));
        network.setCreatedAt(resultSet.getTimestamp("created_at"));
        return network;
    }

    /**
     * Удаляет сеть из таблицы по умолчанию.
     *
     * @param network объект сети для удаления.
     * @throws SQLException при ошибке удаления.
     */
    public void remove(Network network) throws SQLException {
        remove(network, "networks");
    }

    /**
     * Универсальный метод для удаления записи из любой таблицы схемы networks по ID модели.
     *
     * @param model объект модели, содержащий ID.
     * @param tableName имя таблицы для удаления записи.
     * @throws SQLException при ошибке выполнения SQL.
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
     * Сохраняет новую сеть в базе данных.
     *
     * @param network объект сети для сохранения.
     * @return сохраненный объект с заполненными ID и датой создания.
     * @throws SQLException при ошибке вставки.
     */
    public Network save(Network network) throws SQLException {
        try (var connection = openConnection()) {
            try (var statement = connection.prepareStatement("insert into networks.networks (name, description) values (?,?)", Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, network.getName());
                statement.setString(2, network.getDescription());
                statement.execute();

                var result = statement.getGeneratedKeys();
                result.next();
                network.setId(result.getLong("id"));
                network.setCreatedAt(result.getTimestamp("created_at"));
                return network;
            }
        }
    }

    /**
     * Обновляет данные существующей сети.
     *
     * @param network объект сети с обновленными полями.
     * @return актуальное состояние объекта из базы данных.
     * @throws SQLException при ошибке обновления.
     */
    public Network update(Network network) throws SQLException {
        try (var connection = openConnection()) {
            try (var statement = connection.prepareStatement("update networks.networks set name=?,description=? where id=? returning id, name, description, created_at")) {
                statement.setString(1, network.getName());
                statement.setString(2, network.getDescription());
                statement.setLong(3, network.getId());
                var result = statement.executeQuery();

                result.next();
                return toModel(result, network);
            }
        }
    }

    /**
     * Поиск сети по идентификатору.
     *
     * @param id уникальный идентификатор сети.
     * @return объект Network или null, если запись не найдена.
     * @throws SQLException при ошибке запроса.
     */
    public Network findById(int id) throws SQLException {
        Network network = null;
        try (var connection = openConnection()) {
            try (var statement = connection.prepareStatement("select * from networks.networks where id = ?")) {
                statement.setLong(1, id);
                var result = statement.executeQuery();
                if (result.next()) {
                    network = new Network();
                    return toModel(result, network);
                }
            }
        }
        return network;
    }

    /**
     * Поиск сети по ее названию.
     *
     * @param name название сети.
     * @return объект Network или null.
     * @throws SQLException при ошибке запроса.
     */
    public Network findByName(String name) throws SQLException {
        Network network = null;
        try (var connection = openConnection()) {
            try (var statement = connection.prepareStatement("select * from networks.networks where name = ?")) {
                statement.setString(1, name);
                var result = statement.executeQuery();
                if (result.next()) {
                    network = new Network();
                    return toModel(result, network);
                }
            }
        }
        return network;
    }

    /**
     * Поиск сети по ее описанию.
     *
     * @param description текст описания.
     * @return объект Network или null.
     * @throws SQLException при ошибке запроса.
     */
    public Network findByDescription(String description) throws SQLException {
        Network network = null;
        try (var connection = openConnection()) {
            try (var statement = connection.prepareStatement("select * from networks.networks where description = ?")) {
                statement.setString(1, description);
                var result = statement.executeQuery();
                if (result.next()) {
                    network = new Network();
                    return toModel(result, network);
                }
            }
        }
        return network;
    }

    /**
     * Поиск сети по дате создания.
     *
     * @param createdAt дата создания сети.
     * @return объект Network или null.
     * @throws SQLException при ошибке запроса.
     */
    public Network findByCreatedAt(Date createdAt) throws SQLException {
        Network network = null;
        try (var connection = openConnection()) {
            try (var statement  = connection.prepareStatement("select * from networks.networks where created_at = ?")) {
                statement.setTimestamp(1, new Timestamp(createdAt.getTime()));
                var result = statement.executeQuery();
                if (result.next()) {
                    network = new Network();
                    return toModel(result, network);
                }
            }
        }
        return network;
    }
}