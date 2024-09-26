package com.epam.rd.autocode.assessment.appliances.collection;

import com.epam.rd.autocode.assessment.appliances.model.Employee;
import com.epam.rd.autocode.assessment.appliances.model.Manufacturer;
import com.epam.rd.autocode.assessment.appliances.model.Order;

import java.util.List;

public interface Find {
    Manufacturer findManufacturerById(long id);

    Manufacturer findManufacturerByName(String name);

    List<Order> findOrderByEmployee(Employee employee);

    Order findCheapestOrder();

    Order findMostExpensiveOrder();
}
