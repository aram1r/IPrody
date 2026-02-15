import org.example.dao.DeviceConnectionDao;
import org.example.dao.DeviceDao;
import org.example.model.Device;
import org.example.model.DeviceConnection;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        try {
            DeviceDao deviceDao = new DeviceDao();
            HashMap<Device, DeviceConnection> deviceConnections = deviceDao.getAllConnectionsByDevices();
            for (Device device : deviceConnections.keySet()) {
                System.out.println(device);
                DeviceConnection deviceConnection = deviceConnections.get(device);
                System.out.println(deviceConnection);
            }

            System.out.println(deviceDao.filterDeviceBy("id", "1", '='));;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}