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
public class AdressResultSetExtractor implements ResultSetExtractor<List<Adress>> {
    private final UserRowMapper userRowMapper;
    private final AdressRowMapper adressRowMapper;

    @Autowired
    public AdressResultSetExtractor(UserRowMapper userRowMapper, AdressRowMapper adressRowMapper) {
        this.userRowMapper = userRowMapper;
        this.adressRowMapper = adressRowMapper;
    }

    @Override
    public @Nullable List<Adress> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Integer,Adress> addresses = new HashMap<>();
        while (rs.next()) {
            var adressId = rs.getInt("a_id");
            Adress adress = addresses.getOrDefault(adressId, adressRowMapper.mapRow(rs, rs.getRow()));
            if (rs.getObject("u_id", Integer.class) != null) {
                if (adress.getUsers() == null) {
                    adress.setUsers(new ArrayList<>());
                }
                adress.getUsers().add(userRowMapper.mapRow(rs, rs.getRow()));
            }
            if (!addresses.containsKey(adressId)) {
                addresses.put(adressId, adress);
            }
        }
        return addresses.values().stream().toList();
    }
}
