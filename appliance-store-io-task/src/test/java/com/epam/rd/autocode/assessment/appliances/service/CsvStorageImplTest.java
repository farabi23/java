package com.epam.rd.autocode.assessment.appliances.service;

import com.epam.rd.autocode.assessment.appliances.model.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CsvStorageImplTest {
    public static final String PATH = "src/test/resources/";
    public static final String PATH_OUT = "out/";

    public static final String FILE_CLIENTS = "Clients.csv";
    public static final String CLIENT_IN = PATH + FILE_CLIENTS;
    public static final String CLIENT_OUT = PATH + PATH_OUT + FILE_CLIENTS;

    public static final String FILE_MANUFACTURERS = "Manufacturer.csv";
    public static final String MANUFACTURER_IN = PATH + FILE_MANUFACTURERS;
    public static final String MANUFACTURER_OUT = PATH + PATH_OUT + FILE_MANUFACTURERS;

    public static final String FILE_EMPLOYEES = "employees.csv";
    public static final String EMPLOYEES_IN = PATH + FILE_EMPLOYEES;
    public static final String EMPLOYEES_OUT = PATH + PATH_OUT + FILE_EMPLOYEES;

    public static final String FILE_APPLIANCES = "appliances.csv";
    public static final String APPLIANCES_IN = PATH + FILE_APPLIANCES;
    public static final String APPLIANCES_OUT = PATH + PATH_OUT + FILE_APPLIANCES;

    public static final String FILE_ORDERS = "orders.csv";
    public static final String ORDERS_IN = PATH + FILE_ORDERS;
    public static final String ORDERS_OUT = PATH + PATH_OUT + FILE_ORDERS;


    private static CsvStorageImpl storage;

    @BeforeAll
    static void setup() {
        storage = new CsvStorageImpl();
    }

    @Test
    void compareClients() throws IOException {
        try (InputStream in = new FileInputStream(CLIENT_IN);
             OutputStream out = new FileOutputStream(CLIENT_OUT)) {
            List<Client> list = storage.read(in, Mapper::csvToClient);
            storage.write(out, list, Mapper::clientToCsv);
        }
        assertTrue(compareFiles(CLIENT_IN, CLIENT_OUT));
    }
    @Test
    void compareManufacturers() throws IOException {
        try (InputStream in = new FileInputStream(MANUFACTURER_IN);
             OutputStream out = new FileOutputStream(MANUFACTURER_OUT)) {
            List<Manufacturer> list = storage.read(in, Mapper::csvToManufacturer);
            storage.write(out, list, Mapper::manufacturerToCsv);
        }
        assertTrue(compareFiles(MANUFACTURER_IN, MANUFACTURER_OUT));
    }

    @Test
    void compareEmployees() throws IOException {
        try (InputStream in = new FileInputStream(EMPLOYEES_IN);
             OutputStream out = new FileOutputStream(EMPLOYEES_OUT)) {
            List<Employee> list = storage.read(in, Mapper::csvToEmployee);
            storage.write(out, list, Mapper::employeeToCsv);
        }
        assertTrue(compareFiles(EMPLOYEES_IN, EMPLOYEES_OUT));
    }

    @Test
    void compareAppliances() throws IOException {
        try (InputStream in = new FileInputStream(APPLIANCES_IN);
             OutputStream out = new FileOutputStream(APPLIANCES_OUT)) {
            List<Appliance> list = storage.read(in, Mapper::csvToAppliance);
            storage.write(out, list, Mapper::applianceToCsv);
        }
        assertTrue(compareFiles(APPLIANCES_IN, APPLIANCES_OUT));
    }

    @Test
    void compareOrders() throws IOException {
        try (InputStream in = new FileInputStream(ORDERS_IN);
             OutputStream out = new FileOutputStream(ORDERS_OUT)) {
            List<Order> list = storage.read(in, Mapper::csvToOrder);
            storage.write(out, list, Mapper::orderToCsv);
        }
        assertTrue(compareFiles(ORDERS_IN, ORDERS_OUT));
    }



    /* service methods */
    private static boolean compareFiles(String file1, String file2) throws IOException {
        final List<String> fromIn = Files.readAllLines(Path.of(file1));
        fromIn.remove(0);
        final List<String> fromOut = Files.readAllLines(Path.of(file2));

        if (fromIn.size() != fromOut.size()) {
            return false;
        }
        final boolean b = fromIn.removeAll(fromOut);

        return fromIn.size() == 0;
    }
}