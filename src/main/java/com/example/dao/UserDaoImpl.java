package com.example.dao;

import com.example.mapper.UserResultSetExtractor;
import com.example.model.User;
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
public class UserDaoImpl implements UserDao {

    private final JdbcTemplate jdbcTemplate;
    private final UserResultSetExtractor userResultSetExtractor;

    @Autowired
    public UserDaoImpl (DataSource dataSource, UserResultSetExtractor userResultSetExtractor) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.userResultSetExtractor = userResultSetExtractor;
    }

    @Override
    public void create(User user) {
        try {
            jdbcTemplate.update("INSERT INTO users.users(name,email) VALUES(?,?)", user.getName(), user.getEmail());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select u.id as u_id, u.name as u_name, u.email as u_email, a.id as a_id, a.user_id as a_user_id, a.street as a_street, a.city as a_city, a.postal_code as a_postal_code " +
                "from users.users u left join users.adresses a on u.id=a.user_id", userResultSetExtractor);
    }

    @Override
    public void update(User user) {
        try {
            jdbcTemplate.update("UPDATE users.users SET name = ?, email = ? WHERE id = ?",  user.getName(), user.getEmail(), user.getId());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        try {
            jdbcTemplate.update("DELETE FROM users.users WHERE id = ?", user.getId());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findById(int id) {
        List<User> users = jdbcTemplate.query("select u.id as u_id, u.name as u_name, u.email as u_email, a.id as a_id, a.user_id as a_user_id, a.street as a_street, a.city as a_city, a.postal_code as a_postal_code " +
                "from users.users u left join users.adresses a on u.id=a.user_id where u.id=?", userResultSetExtractor, id);
        if (users==null || users.isEmpty()) {
            return null;
        }

        if (users.size()>1) {
            throw new RuntimeException("More than one author found with id: " + id);
        }

        return users.getFirst();
    }

    @Override
    public void create(List<User> users) {
        jdbcTemplate.batchUpdate("INSERT INTO users.users(name,email) VALUES(?,?)", new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, users.get(i).getName());
                ps.setString(2, users.get(i).getEmail());
            }

            @Override
            public int getBatchSize() {
                return users.size();
            }
        });
    }
}