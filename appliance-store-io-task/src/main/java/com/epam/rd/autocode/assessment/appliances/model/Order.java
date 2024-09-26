package com.epam.rd.autocode.assessment.appliances.model;

import java.util.Objects;

public class Order {
    private long id;
    private long employeeId;
    private long clientId;

    // Default Constructor
    public Order() {
    }

    // Parameterized Constructor
    public Order(long id, long employeeId, long clientId) {
        this.id = id;
        this.employeeId = employeeId;
        this.clientId = clientId;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    // Overriding equals method
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return id == order.id && employeeId == order.employeeId && clientId == order.clientId;
    }

    // Overriding hashCode method
    @Override
    public int hashCode() {
        return Objects.hash(id, employeeId, clientId);
    }

    // Overriding toString method
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", employeeId=" + employeeId +
                ", clientId=" + clientId +
                '}';
    }
}
