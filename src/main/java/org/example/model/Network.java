package org.example.model;

import java.util.Date;

/**
 * Модель данных, представляющая логическую сеть (Network).
 * Используется для группировки устройств и хранения информации о сегменте инфраструктуры.
 */
public class Network implements Model {
    /** Уникальный идентификатор сети */
    private long id;
    /** Название сети */
    private String name;
    /** Краткое описание или назначение сети */
    private String description;
    /** Дата и время создания записи о сети в базе данных */
    private Date createdAt;

    /**
     * Конструктор по умолчанию для создания пустого объекта.
     */
    public Network() {}

    /**
     * Полный конструктор для инициализации всех полей объекта.
     * Обычно применяется при извлечении существующих данных из БД.
     *
     * @param id уникальный идентификатор.
     * @param name название сети.
     * @param description описание сети.
     * @param createdAt дата создания.
     */
    public Network(long id, String name, String description, Date createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
    }

    /**
     * Конструктор для создания новой сети с базовыми параметрами.
     *
     * @param name название сети.
     * @param description описание или примечания к сети.
     */
    public Network(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Возвращает уникальный идентификатор сети.
     * @return id сети.
     */
    public long getId() {
        return id;
    }

    /**
     * Устанавливает уникальный идентификатор сети.
     * @param id новый идентификатор.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Возвращает название сети.
     * @return строка с именем.
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает название сети.
     * @param name новое имя сети.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает описание сети.
     * @return текст описания.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Устанавливает описание сети.
     * @param description текст описания.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Возвращает дату и время создания сети.
     * @return объект {@link Date}.
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Устанавливает дату создания сети.
     * @param createdAt дата создания.
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Возвращает строковое представление объекта Network.
     * @return строка с данными сети.
     */
    @Override
    public String toString() {
        return "Network{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}