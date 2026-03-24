package com.example.dao;

import com.example.mapper.AdressResultSetExtractor;
import com.example.model.Adress;
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
public class AdressesDaoImpl implements AdressDao {

    private final JdbcTemplate jdbcTemplate;
    private final AdressResultSetExtractor adressResultSetExtractor;

    @Autowired
    public AdressesDaoImpl(DataSource dataSource, AdressResultSetExtractor adressResultSetExtractor) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.adressResultSetExtractor = adressResultSetExtractor;
    }


    @Override
    public void create(Adress adress) {
        try {
            jdbcTemplate.update("INSERT INTO users.addresses(street, city, postal_code, user_id) VALUES(?,?,?,?)",
                    adress.getStreet(), adress.getCity(), adress.getPostalCode(), adress.getUserId());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Adress> findAll() {
        // Используем BeanPropertyRowMapper для автоматического маппинга полей
        return jdbcTemplate.query("SELECT id, street, city, postal_code as postalCode, user_id as userId FROM users.addresses",
                adressResultSetExtractor);
    }

    @Override
    public void update(Adress adress) {
        try {
            jdbcTemplate.update("UPDATE users.addresses SET street = ?, city = ?, postal_code = ?, user_id = ? WHERE id = ?",
                    adress.getStreet(), adress.getCity(), adress.getPostalCode(), adress.getUserId(), adress.getId());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Adress adress) {
        try {
            jdbcTemplate.update("DELETE FROM users.addresses WHERE id = ?", adress.getId());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Adress findById(int id) {
        try {
            Adress result = jdbcTemplate.query("SELECT id, street, city, postal_code as postalCode, user_id as userId FROM users.addresses WHERE id = ?",
                    adressResultSetExtractor, id).getFirst();
            return result;
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public void create(List<Adress> addresses) {
        jdbcTemplate.batchUpdate("INSERT INTO users.addresses(street, city, postal_code, user_id) VALUES(?,?,?,?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setString(1, addresses.get(i).getStreet());
                        ps.setString(2, addresses.get(i).getCity());
                        ps.setString(3, addresses.get(i).getPostalCode());
                        ps.setInt(4, addresses.get(i).getUserId());
                    }

                    @Override
                    public int getBatchSize() {
                        return addresses.size();
                    }
                });
    }
}
