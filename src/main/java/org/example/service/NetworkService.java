package org.example.service;

import org.example.dao.DeviceConnectionDao;
import org.example.dao.DeviceDao;
import org.example.dao.NetworkDao;
import org.example.dao.StatisticsDao;
import org.example.model.Network;
import org.example.ui.UiController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Основной сервисный класс для управления бизнес-логикой компьютерной сети.
 * Координирует работу между пользовательским интерфейсом (UI) и объектами доступа к данным (DAO).
 */
public class NetworkService {
    /** DAO для работы с сетями */
    private final NetworkDao networkDao;
    /** Контроллер пользовательского интерфейса */
    private final UiController uiController;
    /** DAO для работы с устройствами */
    private final DeviceDao deviceDao;
    /** DAO для работы с соединениями устройств */
    private final DeviceConnectionDao deviceConnectionDao;
    /** DAO для получения статистических данных */
    private final StatisticsDao statisticsDao;

    /**
     * Создает экземпляр сервиса с необходимыми зависимостями.
     *
     * @param networkDao DAO для сетей.
     * @param uiController контроллер UI.
     * @param deviceDao DAO для устройств.
     * @param deviceConnectionDao DAO для соединений.
     * @param statisticsDao DAO для статистики.
     */
    public NetworkService(NetworkDao networkDao, UiController uiController, DeviceDao deviceDao, DeviceConnectionDao deviceConnectionDao, StatisticsDao statisticsDao) {
        this.networkDao = networkDao;
        this.uiController = uiController;
        this.deviceDao = deviceDao;
        this.deviceConnectionDao = deviceConnectionDao;
        this.statisticsDao = statisticsDao;
    }

    /**
     * Запускает основной цикл обработки команд пользователя.
     * Метод работает бесконечно, пока не будет получена команда EXIT.
     * Обрабатывает ошибки ввода и SQL-исключения, выводя их через UI.
     */
    public void process() {
        while (true) {
            int code = uiController.getUserAction();
            var userChoice = UserChoice.valueOf(code);
            if (userChoice.isEmpty()) {
                System.out.println("code was incorrect; try again");
                continue;
            }

            var choice = userChoice.get();
            if (choice == UserChoice.EXIT)
                break;

            try {
                processChoice(choice);
            } catch (SQLException | UserErrorException e) {
                uiController.printError(e.getMessage());
            }
        }
    }

    /**
     * Выполняет конкретную операцию в зависимости от выбора пользователя.
     *
     * @param userChoice выбранное действие (добавление, редактирование, удаление и т.д.).
     * @throws SQLException при ошибках работы с базой данных.
     * @throws UserErrorException при логических ошибках пользователя (например, попытка связать несуществующие устройства).
     */
    private void processChoice(UserChoice userChoice) throws SQLException, UserErrorException {
        switch (userChoice) {
            case ADD_NETWORK -> {
                var network = uiController.readNetwork();
                network = networkDao.save(network);
                uiController.print(network);
            }
            case ADD_DEVICE -> {
                List<Network> networks = networkDao.getAllNetworks();
                if (networks.isEmpty())
                    throw new UserErrorException("Networks not found; add them first");

                var networkToConnect = uiController.selectOf(networks, "network to connect");
                var device = uiController.readDevice();
                device.setNetworkId(networkToConnect.getId());
                device = deviceDao.save(device);
                uiController.print(device);
            }
            case CONNECT_DEVICES -> {
                var allDevices = deviceDao.getAllDevices();
                if (allDevices.size() < 2)
                    throw new UserErrorException("2 devices not found; add them first");

                var deviceFrom = uiController.selectOf(allDevices, "device from");
                var devicesWithoutSelected = allDevices.stream()
                        .filter(device -> !device.equals(deviceFrom))
                        .toList();
                var deviceTo = uiController.selectOf(devicesWithoutSelected, "device to");
                var deviceConnection = uiController.readConnection();
                deviceConnection.setDeviceFromId(deviceFrom.getId());
                deviceConnection.setDeviceToId(deviceTo.getId());
                deviceConnection = deviceConnectionDao.save(deviceConnection);
                uiController.print(deviceConnection);
            }
            case EDIT_NETWORK -> {
                List<Network> networks = networkDao.getAllNetworks();
                if (networks.isEmpty())
                    throw new UserErrorException("Networks not found; add them first");

                var networkToEdit = uiController.selectOf(networks, "network to connect");
                networkToEdit = uiController.edit(networkToEdit);
                networkToEdit = networkDao.update(networkToEdit);
                uiController.print(networkToEdit);
            }
            case REMOVE_CONNECTION -> {
                var allDeviceConnections = deviceConnectionDao.getAllConnections();
                var connectionToRemove = uiController.selectOf(allDeviceConnections, "connection to remove");
                deviceConnectionDao.remove(connectionToRemove);
                uiController.printInfo("Connection " + connectionToRemove + " successfully removed");
            }
            case REMOVE_NETWORK -> {
                var allNetworks = networkDao.getEmptyNetworks();
                var networkToRemove = uiController.selectOf(allNetworks, "network to remove");
                networkDao.remove(networkToRemove);
                uiController.printInfo("Network " + networkToRemove + " successfully removed");
            }
            case GET_ONLINE_DEVICES -> {
                var onlineDevices = statisticsDao.getAllOnlineDevices();
                System.out.println("Online devices now: " + onlineDevices);
            }
            case GET_ALL_NETWORKS -> {
                List<Network> networks = networkDao.getAllNetworks();
                for (Network network : networks) {
                    System.out.println(network.getName() + " " + network.getDescription());
                }
            }
            default -> uiController.printError("Unexpected code; contact administrator");
        }
    }
}