package org.example.service;

import org.example.dao.NetworkDao;
import org.example.model.Device;
import org.example.model.Network;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NetworkServiceTest{

    private static final String IP_PATTERN =
            "^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.?\\b){4}$";

    private static final String MAC_PATTERN = "^([0-9A-Fa-f]{2}[:]){5}([0-9A-Fa-f]{2})$";

    @Test
    public void validateIpTestFail() {
        assertThrows(IllegalArgumentException.class, () -> {
            String ipFail = "mailamail";
            Device device = new Device();
            device.setIpAddress(ipFail);
        });
    };

    @Test
    public void validateIpTestSuccess() {
        assertDoesNotThrow(() -> {
            String ipFail = "192.168.0.1";
            Device device = new Device();
            device.setIpAddress(ipFail);
        });
    }

    @Test
    public void validateMacTestFail() {
        assertThrows(IllegalArgumentException.class, () -> {
            String macFail = "123456789";
            Device device = new Device();
            device.setMacAddress(macFail);
        });
    }

    @Test
    public void validateMacTestSuccess() {
        assertThrows(IllegalArgumentException.class, () -> {
            String macFail = "00:1A:2B:3C:4D:5E";
            Device device = new Device();
            device.setMacAddress(macFail);
        });
    }
}
