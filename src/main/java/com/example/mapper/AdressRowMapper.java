package com.example.mapper;

import com.example.model.Adress;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class AdressRowMapper implements RowMapper<Adress> {

    @Override
    public Adress mapRow(ResultSet rs, int rowNum) throws SQLException {
        Adress adress = new Adress();

        adress.setId(rs.getInt("a_id"));
        adress.setUserId(rs.getInt("a_user_id"));
        adress.setStreet(rs.getString("a_street"));
        adress.setCity(rs.getString("a_city"));
        adress.setPostalCode(rs.getString("a_postal_code"));

        return adress;
    }
}
