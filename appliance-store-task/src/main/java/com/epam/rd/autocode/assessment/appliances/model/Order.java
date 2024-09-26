package com.epam.rd.autocode.assessment.appliances.model;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

public class Order {
    private long id;
    private Client client;
    private Employee employee;
    private Map<Appliance, BigDecimal> appliances;

    public Order() {
    }

    public Order(long id, Client client, Employee employee, Map<Appliance, BigDecimal> appliances) {
        this.id = id;
        this.client = client;
        this.employee = employee;
        this.appliances = appliances;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Map<Appliance, BigDecimal> getAppliances() {
        return appliances;
    }

    public void setAppliances(Map<Appliance, BigDecimal> appliances) {
        this.appliances = appliances;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return id == order.id && Objects.equals(client, order.client) && Objects.equals(employee, order.employee) && Objects.equals(appliances, order.appliances);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, employee, appliances);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", client=" + client +
                ", employee=" + employee +
                ", appliances=" + appliances +
                '}';
    }
}
