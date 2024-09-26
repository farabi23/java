package com.epam.rd.autocode.assessment.appliances.service;

import com.epam.rd.autocode.assessment.appliances.model.*;

public class Mapper {

    // Converts an array of strings to a Manufacturer object
    public static Manufacturer csvToManufacturer(String[] values) {
        long id = parseLong(values[0]);
        String name = parseString(values[1]);
        return new Manufacturer(id, name);
    }

    // Converts a Manufacturer object to an array of strings
    public static String[] manufacturerToCsv(Manufacturer manufacturer) {
        String[] result = new String[2];
        result[0] = manufacturer.getId() == 0 ? "" : String.valueOf(manufacturer.getId());
        result[1] = manufacturer.getName() == null ? "" : manufacturer.getName();
        return result;
    }

    // Converts an array of strings to an Order object
    public static Order csvToOrder(String[] values) {
        long id = parseLong(values[0]);
        long employeeId = parseLong(values[1]);
        long clientId = parseLong(values[2]);
        return new Order(id, employeeId, clientId);
    }

    // Converts an Order object to an array of strings
    public static String[] orderToCsv(Order order) {
        String[] result = new String[3];
        result[0] = order.getId() == 0 ? "" : String.valueOf(order.getId());
        result[1] = order.getEmployeeId() == 0 ? "" : String.valueOf(order.getEmployeeId());
        result[2] = order.getClientId() == 0 ? "" : String.valueOf(order.getClientId());
        return result;
    }

    // Converts an array of strings to a Client object
    public static Client csvToClient(String[] values) {
        long id = parseLong(values[0]);
        String name = parseString(values[1]);
        String email = parseString(values[2]);
        String password = parseString(values[3]);
        String card = parseString(values[4]);
        return new Client(id, name, email, password, card);
    }

    // Converts a Client object to an array of strings
    public static String[] clientToCsv(Client client) {
        String[] result = new String[5];
        result[0] = client.getId() == 0 ? "" : String.valueOf(client.getId());
        result[1] = client.getName() == null ? "" : client.getName();
        result[2] = client.getEmail() == null ? "" : client.getEmail();
        result[3] = client.getPassword() == null ? "" : client.getPassword();
        result[4] = client.getCard() == null ? "" : client.getCard();
        return result;
    }

    // Converts an array of strings to an Employee object
    public static Employee csvToEmployee(String[] values) {
        long id = parseLong(values[0]);
        String name = parseString(values[1]);
        String email = parseString(values[2]);
        String password = parseString(values[3]);
        String department = parseString(values[4]);
        return new Employee(id, name, email, password, department);
    }

    // Converts an Employee object to an array of strings
    public static String[] employeeToCsv(Employee employee) {
        String[] result = new String[5];
        result[0] = employee.getId() == 0 ? "" : String.valueOf(employee.getId());
        result[1] = employee.getName() == null ? "" : employee.getName();
        result[2] = employee.getEmail() == null ? "" : employee.getEmail();
        result[3] = employee.getPassword() == null ? "" : employee.getPassword();
        result[4] = employee.getDepartment() == null ? "" : employee.getDepartment();
        return result;
    }

    // Converts an array of strings to an Appliance object
    public static Appliance csvToAppliance(String[] values) {
        long id = parseLong(values[0]);
        String name = parseString(values[1]);
        Category category = parseCategory(values[2]);
        String model = parseString(values[3]);
        long manufacturerId = parseLong(values[4]);
        PowerType powerType = parsePowerType(values[5]);
        String characteristic = parseString(values[6]);
        String description = parseString(values[7]);
        int power = parseInt(values[8]);
        return new Appliance(id, name, category, model, manufacturerId, powerType, characteristic, description, power);
    }

    // Converts an Appliance object to an array of strings
    public static String[] applianceToCsv(Appliance appliance) {
        String[] result = new String[9];
        result[0] = appliance.getId() == 0 ? "" : String.valueOf(appliance.getId());
        result[1] = appliance.getName() == null ? "" : appliance.getName();
        result[2] = appliance.getCategory() == null ? "" : appliance.getCategory().name();
        result[3] = appliance.getModel() == null ? "" : appliance.getModel();
        result[4] = appliance.getManufacturerId() == 0 ? "" : String.valueOf(appliance.getManufacturerId());
        result[5] = appliance.getPowerType() == null ? "" : appliance.getPowerType().name();
        result[6] = appliance.getCharacteristic() == null ? "" : appliance.getCharacteristic();
        result[7] = appliance.getDescription() == null ? "" : appliance.getDescription();
        result[8] = appliance.getPower() == 0 ? "" : String.valueOf(appliance.getPower());
        return result;
    }

    // Helper method to parse String to long, handling null and empty cases
    private static long parseLong(String value) {
        return (value == null || value.isEmpty()) ? 0 : Long.parseLong(value);
    }

    // Helper method to parse String to int, handling null and empty cases
    private static int parseInt(String value) {
        return (value == null || value.isEmpty()) ? 0 : Integer.parseInt(value);
    }

    // Helper method to parse String, handling null and empty cases
    private static String parseString(String value) {
        return (value == null || value.isEmpty()) ? null : value;
    }

    // Helper method to parse Category, handling null and empty cases
    private static Category parseCategory(String value) {
        return (value == null || value.isEmpty()) ? null : Category.valueOf(value);
    }

    // Helper method to parse PowerType, handling null and empty cases
    private static PowerType parsePowerType(String value) {
        return (value == null || value.isEmpty()) ? null : PowerType.valueOf(value);
    }
}
