package org.example.model;

import java.util.Date;
import java.util.Objects;

/**
 * Модель данных, представляющая сетевое устройство (Device).
 * Содержит информацию об идентификаторах, сетевых адресах, статусе и типе устройства.
 * Реализует встроенную валидацию IP и MAC адресов.
 */
public class Device implements Model {
    /** Уникальный идентификатор устройства */
    private long id;
    /** Имя устройства */
    private String name;
    /** IP-адрес устройства */
    private String ipAddress;
    /** MAC-адрес устройства */
    private String macAddress;
    /** Тип устройства (например, Router, PC, Switch) */
    private String type;
    /** Текущий статус (например, online, offline) */
    private String status;
    /** Идентификатор сети, к которой принадлежит устройство */
    private long networkId;
    /** Дата и время создания записи в системе */
    private Date createdAt;

    /** Регулярное выражение для проверки корректности IPv4 адреса */
    private static final String IP_PATTERN =
            "^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.?\\b){4}$";

    /** Регулярное выражение для проверки корректности MAC-адреса (формат XX:XX:XX:XX:XX:XX) */
    private static final String MAC_PATTERN = "^([0-9A-Fa-f]{2}[:]){5}([0-9A-Fa-f]{2})$";

    /**
     * Конструктор по умолчанию.
     */
    public Device() {}

    /**
     * Конструктор для создания нового устройства с валидацией адресов.
     *
     * @param name имя устройства.
     * @param ipAddress IP-адрес (должен соответствовать {@link #IP_PATTERN}).
     * @param macAddress MAC-адрес (должен соответствовать {@link #MAC_PATTERN}).
     * @param type тип устройства.
     * @param status начальный статус.
     * @throws IllegalArgumentException если IP или MAC адрес не прошли валидацию.
     */
    public Device(String name, String ipAddress, String macAddress, String type, String status) {
        if (validateIpAddress(ipAddress) && validateMacAddress(macAddress)) {
            this.name = name;
            this.ipAddress = ipAddress;
            this.macAddress = macAddress;
            this.type = type;
            this.status = status;
        } else {
            throw new IllegalArgumentException("Invalid data provided");
        }
    }

    /**
     * Полный конструктор для инициализации всех полей (обычно используется при загрузке из БД).
     *
     * @param id уникальный идентификатор.
     * @param name имя устройства.
     * @param ipAddress IP-адрес.
     * @param macAddress MAC-адрес.
     * @param type тип устройства.
     * @param status статус.
     * @param networkId ID сети.
     * @param createdAt дата создания.
     */
    public Device(long id, String name, String ipAddress, String macAddress,
                  String type, String status, long networkId, Date createdAt) {
        this.id = id;
        this.name = name;
        this.ipAddress = ipAddress;
        this.macAddress = macAddress;
        this.type = type;
        this.status = status;
        this.networkId = networkId;
        this.createdAt = createdAt;
    }

    /**
     * Проверяет соответствие строки формату IPv4.
     * @param ipAddress строка для проверки.
     * @return true, если адрес корректен.
     */
    public boolean validateIpAddress(String ipAddress) {
        return ipAddress.matches(IP_PATTERN);
    }

    /**
     * Проверяет соответствие строки формату MAC-адреса.
     * @param macAddress строка для проверки.
     * @return true, если адрес корректен.
     */
    private boolean validateMacAddress(String macAddress) {
        return macAddress.matches(MAC_PATTERN);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * Устанавливает IP-адрес с предварительной проверкой.
     * @param ipAddress новый IP-адрес.
     * @throws IllegalArgumentException если формат адреса неверен.
     */
    public void setIpAddress(String ipAddress) {
        if (validateIpAddress(ipAddress)) {
            this.ipAddress = ipAddress;
        } else  {
            throw new IllegalArgumentException("Invalid data provided");
        }
    }

    public String getMacAddress() {
        return macAddress;
    }

    /**
     * Устанавливает MAC-адрес с предварительной проверкой.
     * @param macAddress новый MAC-адрес.
     * @throws IllegalArgumentException если формат адреса неверен.
     */
    public void setMacAddress(String macAddress) {
        if (validateMacAddress(macAddress)) {
            this.macAddress = macAddress;
        } else {
            throw new IllegalArgumentException("Invalid data provided");
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getNetworkId() {
        return networkId;
    }

    public void setNetworkId(long networkId) {
        this.networkId = networkId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", macAddress='" + macAddress + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", networkId=" + networkId +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Device device = (Device) object;
        return id == device.id && networkId == device.networkId && Objects.equals(name, device.name) && Objects.equals(ipAddress, device.ipAddress) && Objects.equals(macAddress, device.macAddress) && Objects.equals(type, device.type) && Objects.equals(status, device.status) && Objects.equals(createdAt, device.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, ipAddress, macAddress, type, status, networkId, createdAt);
    }
}