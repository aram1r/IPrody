package org.example.ui;

import org.example.model.Device;
import org.example.model.DeviceConnection;
import org.example.model.Model;
import org.example.model.Network;
import org.example.service.UserChoice;

import java.io.PrintStream;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Контроллер пользовательского интерфейса (UI).
 * Отвечает за отображение меню, чтение данных с клавиатуры и вывод информации/ошибок в консоль.
 */
public class UiController {
    /** Источник входных данных */
    private final Scanner scanner;
    /** Поток для вывода информации */
    private final PrintStream outStream;

    /**
     * Создает экземпляр контроллера.
     *
     * @param scanner объект для чтения ввода.
     * @param outStream поток для вывода (например, System.out).
     */
    public UiController(Scanner scanner, PrintStream outStream) {
        this.scanner = scanner;
        this.outStream = outStream;
    }

    /**
     * Отображает список доступных действий и считывает код выбранной операции.
     *
     * @return целочисленный код действия.
     */
    public int getUserAction() {
        outStream.println("Enter: ");
        for (var choice : UserChoice.values()) {
            outStream.println(choice.getCode() + " " + choice.getDescription());
        }

        var code = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера после nextInt()
        return code;
    }

    /** Выводит данные сети в консоль. @param network объект сети. */
    public void print(Network network) {
        outStream.println(network);
    }

    /** Выводит данные устройства в консоль. @param device объект устройства. */
    public void print(Device device) {
        outStream.println(device);
    }

    /** Выводит данные соединения в консоль. @param connection объект соединения. */
    public void print(DeviceConnection connection) {
        outStream.println(connection);
    }

    /**
     * Предоставляет пользователю выбор объекта из списка по его ID.
     * Будет повторять запрос, пока не будет введен корректный существующий ID.
     *
     * @param <T> тип модели, наследуемой от {@link Model}.
     * @param models список доступных объектов.
     * @param nameOfChoice название категории выбора (для вывода сообщения).
     * @return выбранный объект модели.
     */
    public <T extends Model> T selectOf(List<T> models, String nameOfChoice) {
        while (true) {
            outStream.println("Enter " + nameOfChoice + " id: ");
            for (var model : models) {
                outStream.println(model);
            }

            long id = readLong();
            for (var model : models) {
                if (model.getId() == id)
                    return model;
            }

            outStream.println("Id was incorrect; try again");
        }
    }

    /**
     * Запрашивает у пользователя новые данные для редактирования существующей сети.
     *
     * @param network объект сети для редактирования.
     * @return обновленный объект сети.
     */
    public Network edit(Network network) {
        network.setName(readString("name"));
        network.setDescription(readString("description"));
        return network;
    }

    /**
     * Считывает данные для создания новой сети.
     * @return новый объект {@link Network}.
     */
    public Network readNetwork() {
        return edit(new Network());
    }

    /**
     * Считывает данные для создания соединения (тип и статус).
     * @return новый объект {@link DeviceConnection}.
     */
    public DeviceConnection readConnection() {
        var type = readString("type");
        var status = readString("status");
        return new DeviceConnection(type, status);
    }

    /**
     * Вспомогательный метод для чтения строки из консоли с приглашением ко вводу.
     *
     * @param name название поля.
     * @return введенная пользователем строка.
     */
    private String readString(String name) {
        outStream.println("Enter " + name + ":");
        return scanner.nextLine();
    }

    /**
     * Вспомогательный метод для чтения числа типа long с очисткой буфера.
     * @return введенное число.
     */
    private long readLong() {
        long value = scanner.nextLong();
        scanner.nextLine();
        return value;
    }

    /** Выводит сообщение об ошибке. @param error текст ошибки. */
    public void printError(String error) {
        outStream.println(error);
    }

    /** Выводит информационное сообщение. @param message текст сообщения. */
    public void printInfo(String message) {
        outStream.println(message);
    }

    /**
     * Считывает полный набор данных для создания нового устройства.
     *
     * @return новый объект {@link Device}.
     */
    public Device readDevice() {
        var name = readString("name");
        var ipAddress = readString("ip address");
        var macAddress = readString("mac address");
        var type = readString("type");
        var status = readString("status");
        return new Device(name, ipAddress, macAddress, type, status);
    }
}