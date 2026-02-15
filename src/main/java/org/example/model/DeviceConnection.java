package org.example.model;

import java.util.Date;

/**
 * Модель данных, представляющая соединение между устройствами (DeviceConnection).
 * Хранит информацию о том, какие устройства связаны, а также тип и текущий статус этого соединения.
 */
public class DeviceConnection implements Model {
    /** Уникальный идентификатор соединения */
    private long id;
    /** Идентификатор устройства-инициатора (откуда) */
    private long deviceFromId;
    /** Идентификатор целевого устройства (куда) */
    private long deviceToId;
    /** Тип соединения (например, проводное, беспроводное) */
    private String type;
    /** Статус соединения (например, active, inactive) */
    private String status;
    /** Дата и время создания записи о соединении */
    private Date createdAt;


    /**
     * Конструктор по умолчанию для создания пустого объекта.
     */
    public DeviceConnection() {}

    /**
     * Конструктор для создания соединения с указанием его типа и статуса.
     *
     * @param type тип соединения.
     * @param status текущий статус.
     */
    public DeviceConnection(String type, String status) {
        this.type = type;
        this.status = status;
    }

    /**
     * Полный конструктор для инициализации всех полей соединения.
     * Обычно используется при получении данных из базы данных.
     *
     * @param id уникальный идентификатор.
     * @param deviceFromId ID устройства-отправителя.
     * @param deviceToId ID устройства-получателя.
     * @param type тип соединения.
     * @param status статус соединения.
     * @param createdAt дата создания записи.
     */
    public DeviceConnection(long id, long deviceFromId, long deviceToId, String type, String status, Date createdAt) {
        this.id = id;
        this.deviceFromId = deviceFromId;
        this.deviceToId = deviceToId;
        this.type = type;
        this.status = status;
        this.createdAt = createdAt;
    }

    /**
     * Возвращает уникальный идентификатор соединения.
     * @return id соединения.
     */
    public long getId() {
        return id;
    }

    /**
     * Устанавливает уникальный идентификатор соединения.
     * @param id новый идентификатор.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Возвращает идентификатор исходящего устройства.
     * @return id устройства-инициатора.
     */
    public long getDeviceFromId() {
        return deviceFromId;
    }

    /**
     * Устанавливает идентификатор исходящего устройства.
     * @param deviceFromId новый id устройства-инициатора.
     */
    public void setDeviceFromId(long deviceFromId) {
        this.deviceFromId = deviceFromId;
    }

    /**
     * Возвращает идентификатор входящего устройства.
     * @return id целевого устройства.
     */
    public long getDeviceToId() {
        return deviceToId;
    }

    /**
     * Устанавливает идентификатор входящего устройства.
     * @param deviceToId новый id целевого устройства.
     */
    public void setDeviceToId(long deviceToId) {
        this.deviceToId = deviceToId;
    }

    /**
     * Возвращает тип соединения.
     * @return строка с описанием типа.
     */
    public String getType() {
        return type;
    }

    /**
     * Устанавливает тип соединения.
     * @param type новый тип соединения.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Возвращает текущий статус соединения.
     * @return строка с описанием статуса.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Устанавливает текущий статус соединения.
     * @param status новый статус.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Возвращает дату создания записи о соединении.
     * @return объект {@link Date}.
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Устанавливает дату создания записи о соединении.
     * @param createdAt дата создания.
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Возвращает строковое представление объекта DeviceConnection.
     * @return строка, содержащая все поля объекта.
     */
    @Override
    public String toString() {
        return "DeviceConnection{" +
                "id=" + id +
                ", deviceFromId=" + deviceFromId +
                ", deviceToId=" + deviceToId +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}