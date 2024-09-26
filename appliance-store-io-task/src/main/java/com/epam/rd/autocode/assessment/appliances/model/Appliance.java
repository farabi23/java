package com.epam.rd.autocode.assessment.appliances.model;

import java.util.Objects;

public class Appliance {
    private long id;
    private String name;
    private Category category;
    private String model;
    private long manufacturerId;
    private PowerType powerType;
    private String characteristic;
    private String description;
    private int power;

    // Default Constructor
    public Appliance() {
    }

    // Parameterized Constructor
    public Appliance(long id, String name, Category category, String model, long manufacturerId,
                     PowerType powerType, String characteristic, String description, int power) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.model = model;
        this.manufacturerId = manufacturerId;
        this.powerType = powerType;
        this.characteristic = characteristic;
        this.description = description;
        this.power = power;
    }

    // Getters and Setters
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public PowerType getPowerType() {
        return powerType;
    }

    public void setPowerType(PowerType powerType) {
        this.powerType = powerType;
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    // Override Methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Appliance)) return false;
        Appliance appliance = (Appliance) o;
        return id == appliance.id && manufacturerId == appliance.manufacturerId && power == appliance.power &&
                Objects.equals(name, appliance.name) && category == appliance.category &&
                Objects.equals(model, appliance.model) && powerType == appliance.powerType &&
                Objects.equals(characteristic, appliance.characteristic) &&
                Objects.equals(description, appliance.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, model, manufacturerId, powerType, characteristic, description, power);
    }

    @Override
    public String toString() {
        return "Appliance{id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", model='" + model + '\'' +
                ", manufacturerId=" + manufacturerId +
                ", powerType=" + powerType +
                ", characteristic='" + characteristic + '\'' +
                ", description='" + description + '\'' +
                ", power=" + power +
                '}';
    }
}

