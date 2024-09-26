package com.epam.rd.autocode.assessment.appliances.model;

import java.util.Objects;

public class Manufacturer {
    private long id;
    private String name;

    // Default Constructor
    public Manufacturer() {
    }

    // Parameterized Constructor
    public Manufacturer(long id, String name) {
        this.id = id;
        this.name = name;
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

    // Overriding equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Manufacturer)) return false;
        Manufacturer that = (Manufacturer) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    // Overriding hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    // Overriding toString method
    @Override
    public String toString() {
        return "Manufacturer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
