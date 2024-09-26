package com.epam.rd.autocode.assessment.appliances.collection;

import com.epam.rd.autocode.assessment.appliances.model.Appliance;
import com.epam.rd.autocode.assessment.appliances.model.Manufacturer;
import com.epam.rd.autocode.assessment.appliances.model.Order;

import java.util.Set;

public interface Sort {
    Set<Manufacturer> sortManufacturersByName();

    Set<Order> sortOrderByClientId();

    Set<Appliance> sortAppliancesByCategory();

    Set<Order> sortOrderByAmount();
}
