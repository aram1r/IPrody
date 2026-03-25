package com.example.mapper;

import com.example.model.Address;
import com.example.model.User;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AdressResultSetExtractor implements ResultSetExtractor<List<Address>> {
    private final UserRowMapper userRowMapper;
    private final AddressRowMapper addressRowMapper;

    @Autowired
    public AdressResultSetExtractor(UserRowMapper userRowMapper, AddressRowMapper addressRowMapper) {
        this.userRowMapper = userRowMapper;
        this.addressRowMapper = addressRowMapper;
    }

    @Override
    public @Nullable List<Address> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Integer,Address> addresses = new HashMap<>();
        while (rs.next()) {
            var addressId = rs.getInt("a_id");
            Address address = addresses.getOrDefault(addressId, addressRowMapper.mapRow(rs, rs.getRow()));
            if (rs.getObject("u_id", Integer.class) != null) {
                User user = userRowMapper.mapRow(rs, rs.getRow());
                address.setUser(user);
                user.setAddress(address);
            }
            if (!addresses.containsKey(addressId)) {
                addresses.put(addressId, address);
            }
        }
        return addresses.values().stream().toList();
    }
}
