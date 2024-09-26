package com.epam.rd.autocode.assessment.appliances.collection;

import com.epam.rd.autocode.assessment.appliances.model.*;

import java.math.BigDecimal;
import java.util.*;

public class Shop implements Add, Find, Sort {
    private Set<Client> clients = new HashSet<>();
    private Set<Employee> employees = new HashSet<>();
    private Set<Order> orders = new HashSet<>();
    private Set<Appliance> appliances = new HashSet<>();
    private Set<Manufacturer> manufacturers = new HashSet<>();

    // Add Methods
    @Override
    public void addClient(Client client) {
        clients.add(client);
    }

    @Override
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    @Override
    public void addAppliance(Appliance appliance) {
        appliances.add(appliance);
    }

    @Override
    public void addOrder(Order order) {
        orders.add(order);
    }

    @Override
    public void addManufacturer(Manufacturer manufacturer) {
        manufacturers.add(manufacturer);
    }

    // Find Methods
    @Override
    public Manufacturer findManufacturerById(long id) {
        for (Manufacturer manufacturer : manufacturers) {
            if (manufacturer.getId() == id) {
                return manufacturer;
            }
        }
        throw new RuntimeException("Manufacturer with id=" + id + " was not found");
    }

    @Override
    public Manufacturer findManufacturerByName(String name) {
        for (Manufacturer manufacturer : manufacturers) {
            if (manufacturer.getName() != null && manufacturer.getName().equals(name)) {
                return manufacturer;
            }
        }
        throw new RuntimeException("Manufacturer with name='" + name + "' was not found");
    }

    @Override
    public List<Order> findOrderByEmployee(Employee employee) {
        List<Order> result = new ArrayList<>();
        for (Order order : orders) {
            if (order.getEmployee() == null || order.getEmployee().equals(employee)) {
                result.add(order);
            }
        }
        return result;
    }

    @Override
    public Order findCheapestOrder() {
        if (orders.isEmpty()) {
            throw new RuntimeException("Order not found");
        }
        Order cheapestOrder = null;
        BigDecimal minAmount = null;
        for (Order order : orders) {
            BigDecimal totalAmount = getOrderTotalAmount(order);
            if (minAmount == null || totalAmount.compareTo(minAmount) < 0) {
                minAmount = totalAmount;
                cheapestOrder = order;
            }
        }
        return cheapestOrder;
    }

    @Override
    public Order findMostExpensiveOrder() {
        if (orders.isEmpty()) {
            throw new RuntimeException("Order not found");
        }
        Order mostExpensiveOrder = null;
        BigDecimal maxAmount = null;
        for (Order order : orders) {
            BigDecimal totalAmount = getOrderTotalAmount(order);
            if (maxAmount == null || totalAmount.compareTo(maxAmount) > 0) {
                maxAmount = totalAmount;
                mostExpensiveOrder = order;
            }
        }
        return mostExpensiveOrder;
    }

    // Helper Method
    private BigDecimal getOrderTotalAmount(Order order) {
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (BigDecimal amount : order.getAppliances().values()) {
            totalAmount = totalAmount.add(amount);
        }
        return totalAmount;
    }

    // Sort Methods
    @Override
    public Set<Manufacturer> sortManufacturersByName() {
        List<Manufacturer> sortedList = new ArrayList<>(manufacturers);
        sortedList.sort(new Comparator<Manufacturer>() {
            @Override
            public int compare(Manufacturer m1, Manufacturer m2) {
                if (m1.getName() == null) return 1;
                if (m2.getName() == null) return -1;
                return m1.getName().compareTo(m2.getName());
            }
        });
        return new LinkedHashSet<>(sortedList);
    }

    @Override
    public Set<Order> sortOrderByClientId() {
        List<Order> sortedList = new ArrayList<>(orders);
        sortedList.sort(new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return Long.compare(o1.getClient().getId(), o2.getClient().getId());
            }
        });
        return new LinkedHashSet<>(sortedList);
    }

    @Override
    public Set<Appliance> sortAppliancesByCategory() {
        List<Appliance> sortedList = new ArrayList<>(appliances);
        sortedList.sort(new Comparator<Appliance>() {
            @Override
            public int compare(Appliance a1, Appliance a2) {
                return a1.getCategory().compareTo(a2.getCategory());
            }
        });
        return new LinkedHashSet<>(sortedList);
    }

    @Override
    public Set<Order> sortOrderByAmount() {
        List<Order> sortedList = new ArrayList<>(orders);
        sortedList.sort(new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return getOrderTotalAmount(o1).compareTo(getOrderTotalAmount(o2));
            }
        });
        return new LinkedHashSet<>(sortedList);
    }

    // Getter Methods
    public Set<Manufacturer> getManufacturers() {
        return manufacturers;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public Set<Appliance> getAppliances() {
        return appliances;
    }
}
