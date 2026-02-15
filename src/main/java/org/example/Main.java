package org.example;

import org.example.dao.DeviceConnectionDao;
import org.example.dao.DeviceDao;
import org.example.dao.NetworkDao;
import org.example.dao.StatisticsDao;
import org.example.service.NetworkService;
import org.example.ui.UiController;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        var uiController = new UiController(new Scanner(System.in), System.out);
        var networkDao = new NetworkDao();
        var deviceDao = new DeviceDao();
        var deviceConnectionDao = new DeviceConnectionDao();
        var statisticsDao = new StatisticsDao();
        var service = new NetworkService(networkDao, uiController, deviceDao, deviceConnectionDao, statisticsDao);
        service.process();
    }
}