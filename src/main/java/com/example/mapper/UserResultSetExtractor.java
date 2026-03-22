package com.example.mapper;

import com.example.model.Adress;
import com.example.model.User;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserResultSetExtractor implements ResultSetExtractor<List<User>>{
    private final UserRowMapper userRowMapper;
    private final AdressRowMapper adressRowMapper;

    @Autowired
    public UserResultSetExtractor(UserRowMapper userRowMapper, AdressRowMapper adressRowMapper) {
        this.userRowMapper = userRowMapper;
        this.adressRowMapper = adressRowMapper;
    }

    @Override
    public @Nullable List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Integer,User> users = new HashMap<>();
        while (rs.next()) {
            var userId = rs.getInt("u_id");
            User user = users.getOrDefault(userId, userRowMapper.mapRow(rs, rs.getRow()));
            if (rs.getObject("u_id", Integer.class) != null) {
                if (user.getAdress() == null) {
                    user.setAdress(new Adress());
                }
                user.setAdress(adressRowMapper.mapRow(rs, rs.getRow()));
            }
            if (!users.containsKey(userId)) {
                users.put(userId, user);
            }
        }
        return users.values().stream().toList();
    }
}