package com.epam.rd.autocode.assessment.appliances.collection;

import com.epam.rd.autocode.assessment.appliances.model.*;

public interface Add {
    void addClient(Client client);

    void addEmployee(Employee employee);

    void addAppliance(Appliance appliance);

    void addOrder(Order order);

    void addManufacturer(Manufacturer manufacturer);
}
