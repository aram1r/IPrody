package com.example.dao;

import com.example.mapper.AdressResultSetExtractor;
import com.example.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class AddressesDaoImpl implements AddressDao {

    private final JdbcTemplate jdbcTemplate;
    private final AdressResultSetExtractor adressResultSetExtractor;

    @Autowired
    public AddressesDaoImpl(DataSource dataSource, AdressResultSetExtractor adressResultSetExtractor) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.adressResultSetExtractor = adressResultSetExtractor;
    }


    @Override
    public void create(Address address) {
        try {
            jdbcTemplate.update("INSERT INTO users.addresses(street, city, postal_code, user_id) VALUES(?,?,?,?)",
                    address.getStreet(), address.getCity(), address.getPostalCode(), address.getUser().getId());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Address> findAll() {
        // Используем BeanPropertyRowMapper для автоматического маппинга полей
        return jdbcTemplate.query("SELECT id, street, city, postal_code as postalCode, user_id as userId FROM users.addresses",
                adressResultSetExtractor);
    }

    @Override
    public void update(Address address) {
        try {
            jdbcTemplate.update("UPDATE users.addresses SET street = ?, city = ?, postal_code = ?, user_id = ? WHERE id = ?",
                    address.getStreet(), address.getCity(), address.getPostalCode(), address.getUser().getId(), address.getId());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Address address) {
        try {
            jdbcTemplate.update("DELETE FROM users.addresses WHERE id = ?", address.getId());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Address findById(int id) {
        try {
            Address result = jdbcTemplate.query("SELECT id, street, city, postal_code as postalCode, user_id as userId FROM users.addresses WHERE id = ?",
                    adressResultSetExtractor, id).getFirst();
            return result;
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public void create(List<Address> addresses) {
        jdbcTemplate.batchUpdate("INSERT INTO users.addresses(street, city, postal_code, user_id) VALUES(?,?,?,?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, addresses.get(i).getStreet());
                        ps.setString(2, addresses.get(i).getCity());
                        ps.setString(3, addresses.get(i).getPostalCode());
                        ps.setLong(4, addresses.get(i).getUser().getId());
                    }

                    @Override
                    public int getBatchSize() {
                        return addresses.size();
                    }
                });
    }
}
