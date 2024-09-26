package com.epam.rd.autocode.assessment.appliances.collection;

import com.epam.rd.autocode.assessment.appliances.model.*;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import spoon.Launcher;
import spoon.reflect.declaration.CtType;
import spoon.reflect.factory.Factory;

import static org.junit.jupiter.api.Assertions.*;

class ShopTest {
    private static String PATH = "src/test/resources/";
    private static String CSV_MANUFACTURERS = PATH + "Manufacturer.csv";
    private static String CSV_CLIENTS = PATH + "Clients.csv";
    private static String CSV_EMPLOYEES = PATH + "Employees.csv";
    private static String CSV_APPLIANCES = PATH + "Appliances.csv";
    private static String CSV_ORDERS = PATH + "Orders.csv";
    private static String CSV_APPLIANCES_IN_ORDERS = PATH + "AppliancesInOrder.csv";

    private static List<Field> allFields;
    private static List<Constructor<?>> allConstructors;
    private static List<Method> allMethods;

    private static List<Class<?>> allInterfaces;
    private static Set<Employee> correctEmployees;
    private static Set<Client> correctClient;
    private static Set<Manufacturer> correctManufacturer;
    private static Set<Appliance> correctAppliances;
    private static Set<Order> correctOrders;
    private static Map<Appliance, BigDecimal> correctAppliancesInOrders;

    /*not for user*/
    private static Factory spoon;

    @BeforeAll
    static void setup() throws Exception {
        final Class<?> clazz = Class.forName(TestConstants.SHOP_TYPE);

        allFields = Arrays.asList(clazz.getDeclaredFields());
        allConstructors = Arrays.asList(clazz.getConstructors());
        allMethods = Arrays.asList(clazz.getDeclaredMethods());
        allInterfaces = Arrays.asList(clazz.getInterfaces());

        correctEmployees = readEmployee();
        correctClient = readClients();
        correctManufacturer = readManufacturers();
        correctAppliances = readAppliances();
        correctOrders = readOrders();
        /* NOT FOR USER*/
        final String[] args = {"-i", "src/main/java/"};
        final Launcher launcher = new Launcher();
        launcher.setArgs(args);
        launcher.buildModel();
        spoon = launcher.getFactory();
    }

    //check interfaces
    @DisplayName("Checks implemented interfaces")
    @ParameterizedTest
    @CsvFileSource(resources = "/shopInterfaces.csv")
    void checkInterfacesName(String name) {
        allInterfaces.stream()
                .filter(i -> i.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Must be implement interface " + name + "."));
    }

    //Check methods
    @DisplayName("Checks implemented methods")
    @ParameterizedTest
    @CsvFileSource(resources = "/shopMethods.csv")
    void checkMethodsName(String methodName) {
        allMethods.stream()
                .filter(method -> method.getName().equals(methodName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No method with name " + methodName + "."));
    }

    //not for student
    @ParameterizedTest
    @CsvFileSource(resources = "/shopMethods.csv")
    void checkFields(String name, String type) {
        allMethods.stream()
                .filter(method -> method.getReturnType().getName().equals(type) && method.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No method with name " + name + " and type " + type));
    }

    // Adds tests
    @Test
    @DisplayName("Checks method addManufacturer")
    void checkAddManufacturerIntoShop() throws Exception {
        final Set<Manufacturer> manufacturers = new HashSet<>(correctManufacturer);
        final Shop shop = new Shop();
        manufacturers.forEach(manufacturer -> {
            shop.addManufacturer(manufacturer);
        });
        assertEquals(manufacturers.size(), shop.getManufacturers().size());
    }

    @Test
    @DisplayName("Checks method addClient")
    void checkAddClientIntoShop() throws Exception {
        final Set<Client> clients = new HashSet<>(correctClient);
        final Shop shop = new Shop();
        clients.forEach(client -> {
            shop.addClient(client);
        });
        assertEquals(clients.size(), shop.getClients().size());
    }

    @Test
    @DisplayName("Checks method addEmployee")
    void checkAddEmployeeIntoShop() throws Exception {
        final Set<Employee> employees = new HashSet<>(correctEmployees);
        final Shop shop = new Shop();
        employees.forEach(employee -> {
            shop.addEmployee(employee);
        });
        assertEquals(employees.size(), shop.getEmployees().size());
    }

    @Test
    @DisplayName("Checks method addAppliance")
    void checkAddApplianceIntoShop() throws Exception {
        final Set<Appliance> appliances = new HashSet<>(correctAppliances);
        final Shop shop = new Shop();
        appliances.forEach(appliance -> {
            shop.addAppliance(appliance);
        });
        assertEquals(appliances.size(), shop.getAppliances().size());
    }

    @Test
    @DisplayName("Checks method addOrder")
    void checkAddOrderIntoShop() throws Exception {
        final Set<Order> orders = new HashSet<>(correctOrders);
        final Shop shop = new Shop();
        orders.forEach(order -> {
            shop.addOrder(order);
        });
        assertEquals(orders.size(), shop.getOrders().size());
    }

    @Test
    @DisplayName("Checks pass null, null in method addManufacturer")
    void givenNullNull_whenAddManufacturer_thenOneNull() {
        final Shop shop = new Shop();
        shop.addManufacturer(null);
        shop.addManufacturer(null);
        assertEquals(1, shop.getManufacturers().size());
    }

    @Test
    @DisplayName("Checks pass null, null in method addClient")
    void givenNullNull_whenAddClient_thenOneNull() {
        final Shop shop = new Shop();
        shop.addClient(null);
        shop.addClient(null);
        assertEquals(1, shop.getClients().size());
    }

    @Test
    @DisplayName("Checks pass null, null in method addEmployee")
    void givenNullNull_whenAddEmployee_thenOneNull() {
        final Shop shop = new Shop();
        shop.addEmployee(null);
        shop.addEmployee(null);
        assertEquals(1, shop.getEmployees().size());
    }

    @Test
    @DisplayName("Checks pass null, null in method addAppliance")
    void givenNullNull_whenAddAppliance_thenOneNull() {
        final Shop shop = new Shop();
        shop.addAppliance(null);
        shop.addAppliance(null);
        assertEquals(1, shop.getAppliances().size());
    }

    @Test
    @DisplayName("Checks pass null, null in method addOrder")
    void givenNullNull_whenAddOrder_thenOneNull() {
        final Shop shop = new Shop();
        shop.addOrder(null);
        shop.addOrder(null);
        assertEquals(1, shop.getOrders().size());
    }

    //check Find
    @Test
    void givenId_whenFindManufacturerById_thenManufacturer() throws Exception {
        final Set<Manufacturer> manufacturers = readManufacturers();
        final Shop shop = new Shop();
        manufacturers.forEach(manufacturer -> shop.addManufacturer(manufacturer));

        final Manufacturer expected = getManufacturer(4L);
        final Manufacturer actual = shop.findManufacturerById(4L);

        assertEquals(expected, actual);
    }

    @Test
    void givenId_whenFindManufacturerById_thenRuntimeException() throws Exception {
        final Set<Manufacturer> manufacturers = readManufacturers();
        final Shop shop = new Shop();
        manufacturers.forEach(manufacturer -> shop.addManufacturer(manufacturer));

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            final Manufacturer actual = shop.findManufacturerById(25);
        });

        assertEquals("Manufacturer with id=25 was not found", thrown.getMessage());
    }

    @Test
    void givenName_whenFindManufacturerByName_thenManufacturer() throws Exception {
        final Set<Manufacturer> manufacturers = readManufacturers();
        final Shop shop = new Shop();
        manufacturers.forEach(manufacturer -> shop.addManufacturer(manufacturer));

        final Manufacturer expected = getManufacturer(2);
        final Manufacturer actual = shop.findManufacturerByName("Dell");

        assertEquals(expected, actual);
    }

    @Test
    void givenName_whenFindManufacturerByName_thenRuntimeException() throws Exception {
        final Set<Manufacturer> manufacturers = readManufacturers();
        final Shop shop = new Shop();
        manufacturers.forEach(manufacturer -> shop.addManufacturer(manufacturer));

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            final Manufacturer actual = shop.findManufacturerByName("25");
        });

        assertEquals("Manufacturer with name=25 was not found", thrown.getMessage());
    }

    @Test
    void givenEmployee_whenFindOrderByEmployee_thenTrue() throws Exception {
        final Set<Order> orders = readOrders();
        Shop shop = new Shop();
        orders.forEach(order -> shop.addOrder(order));

        List<Order> expected = Arrays.asList(getOrderById(1L), getOrderById(3L), getOrderById(2L));

        Employee employee = getEmployee(2L);
        List<Order> actual = shop.findOrderByEmployee(employee);

        assertEquals(3, actual.size());
        assertTrue(actual.contains(getOrderById(1L)));
        assertTrue(actual.contains(getOrderById(2L)));
        assertTrue(actual.contains(getOrderById(3L)));
    }

    @Test
    void givenNull_whenFindOrderByEmployee_thenZero() throws Exception {
        final Set<Order> orders = readOrders();
        Shop shop = new Shop();
        orders.forEach(order -> shop.addOrder(order));

        List<Order> expected = new ArrayList<>();

        List<Order> actual = shop.findOrderByEmployee(null);

        assertEquals(expected, actual);
    }

    @Test
    void givenNullAndOrdersHasEmployeeNull_whenFindOrderByEmployee_thenOk() throws Exception {
        final Set<Order> orders = readOrders();
        Shop shop = new Shop();
        final Order orderNullClient = getOrderById(6l);
        orderNullClient.setEmployee(null);

        shop.addOrder(orderNullClient);

        List<Order> expected = new ArrayList<>();
        expected.add(orderNullClient);

        List<Order> actual = shop.findOrderByEmployee(null);

        assertEquals(expected, actual);
    }

    //check Sort
    @Test
    void givenTwoManufacturer_whenSortManufacturersByName_thenTrue() {
        final Shop shop = new Shop();
        final Manufacturer position2 = getManufacturer(1);
        final Manufacturer position1 = getManufacturer(2);
        shop.addManufacturer(position2);
        shop.addManufacturer(position1);

        final TreeSet<Manufacturer> manufacturers = (TreeSet<Manufacturer>) shop.sortManufacturersByName();

        assertEquals(position1, manufacturers.first());
        assertEquals(position2, manufacturers.last());
    }

    @Test
    void givenOneManufacturerWithNameNull_whenSortManufacturersByName_thenTrue() {
        final Shop shop = new Shop();
        final Manufacturer position2 = getManufacturer(1);
        final Manufacturer position1 = getManufacturer(2);
        position2.setName(null);
        shop.addManufacturer(position2);
        shop.addManufacturer(position1);

        final TreeSet<Manufacturer> manufacturers = (TreeSet<Manufacturer>) shop.sortManufacturersByName();

        assertEquals(position1, manufacturers.first());
        assertEquals(position2, manufacturers.last());
    }

    @Test
    void givenSecondManufacturerWithNameNull_whenSortManufacturersByName_thenTrue() {
        final Shop shop = new Shop();
        final Manufacturer position2 = getManufacturer(1);
        final Manufacturer position1 = getManufacturer(2);
        position1.setName(null);
        shop.addManufacturer(position2);
        shop.addManufacturer(position1);

        final TreeSet<Manufacturer> manufacturers = (TreeSet<Manufacturer>) shop.sortManufacturersByName();

        assertEquals(position1, manufacturers.last());
        assertEquals(position2, manufacturers.first());
    }

    @Test
    void givenClients_whenSortOrderByClientId_then_Ok() {
        Shop shop = new Shop();
        final Order position1 = getOrderById(1L);
        final Order position2 = getOrderById(5L);
        shop.addOrder(position1);
        shop.addOrder(position2);
        final TreeSet<Order> orders = (TreeSet<Order>) shop.sortOrderByClientId();

        assertEquals(position1, orders.first());
        assertEquals(position2, orders.last());
    }

    @Test
    void givenOrdersWhenFirstClientIsNull_whenSortOrderByClientId_then_Ok() {
        Shop shop = new Shop();
        final Order position1 = getOrderById(4L);
        final Order position2 = getOrderById(5L);
        position1.setClient(null);
        shop.addOrder(position1);
        shop.addOrder(position2);
        final TreeSet<Order> orders = (TreeSet<Order>) shop.sortOrderByClientId();

        assertEquals(position2, orders.first());
        assertEquals(position1, orders.last());
    }

    @Test
    void givenOrdersWhenSecondClientIsNull_whenSortOrderByClientId_then_Ok() {
        Shop shop = new Shop();
        final Order position1 = getOrderById(1L);
        final Order position2 = getOrderById(5L);
        position2.setClient(null);
        shop.addOrder(position1);
        shop.addOrder(position2);
        final TreeSet<Order> orders = (TreeSet<Order>) shop.sortOrderByClientId();

        assertEquals(position1, orders.first());
        assertEquals(position2, orders.last());
    }

    @Test
    void givenOrders_whenSortOrderByAmount_ThenOk() {
        Shop shop = new Shop();
        final Order positionMax = getOrderById(2L);
        final Order positionMin = getOrderById(3L);
        shop.addOrder(positionMax);
        shop.addOrder(positionMin);

        final TreeSet<Order> orders = (TreeSet<Order>) shop.sortOrderByAmount();

        assertEquals(2, orders.size());
        assertEquals(positionMax, orders.last());
        assertEquals(positionMin, orders.first());

    }

    @Test
    void givenAppliances_whenSortApplicationByCategory_thenOk() {
        Shop shop = new Shop();
        final Appliance appliance1 = getAppliance(3L);
        final Appliance appliance2 = getAppliance(4L);

        shop.addAppliance(appliance1);
        shop.addAppliance(appliance2);

        final TreeSet<Appliance> appliances = (TreeSet<Appliance>) shop.sortAppliancesByCategory();

        assertEquals(appliance2, appliances.first());
        assertEquals(appliance1, appliances.last());
    }

    @Test
    void givenOrders_whenFindCheaperOrder_thenCheaperOrder() throws Exception {
        final Shop shop = new Shop();
        final Set<Order> orders = readOrders();
        orders.forEach(order -> shop.addOrder(order));

        Order expected = getOrderById(3);

        Order actual = shop.findCheapestOrder();

        assertEquals(expected, actual);
    }

    @Test
    void givenEmptyOrders_whenFindCheaperOrder_thenCheaperOrder() {
        final Shop shop = new Shop();
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            Order actual = shop.findCheapestOrder();
        });
        String expected = "Order not found";
        assertEquals(expected, thrown.getMessage());
    }

    @Test
    void givenOrders_whenFindExpensiveOrder_thenExpensiveOrder() throws Exception {
        final Shop shop = new Shop();
        final Set<Order> orders = readOrders();
        orders.forEach(order -> shop.addOrder(order));

        Order expected = getOrderById(4);

        Order actual = shop.findMostExpensiveOrder();

        assertEquals(expected, actual);
    }

    @Test
    void givenEmptyOrders_whenFindExpensiveOrder_thenExpensiveOrder() {
        final Shop shop = new Shop();
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            Order actual = shop.findMostExpensiveOrder();
        });
        String expected = "Order not found";
        assertEquals(expected, thrown.getMessage());
    }

    @Test
    void checkStreamApi() throws ClassNotFoundException {
        CtType<Shop> agencyClass = spoon.Type().get(Shop.class.getName());
        Class<?>[] classes = {Find.class, Sort.class, Add.class};
        Arrays.stream(classes)
                .map(Class::getDeclaredMethods)
                .flatMap(Stream::of)
                .map(m -> agencyClass.getMethodsByName(m.getName()).stream())
                .flatMap(Function.identity())
                .forEach(m -> assertTrue(
                        m.getReferencedTypes().stream()
                                .map(el -> el.getQualifiedName())
                                .filter(name -> name.startsWith("java.util.stream")
                                        || name.startsWith("java.util.function"))
                                .map(el -> Boolean.FALSE)
                                .findAny().orElse(Boolean.TRUE),
                        () -> "Method " + m.getSignature() + " must not use Stream API and types from the "
                                + "java.util.function package")
                );
    }

    // services methods
    @SuppressWarnings("unchecked")
    private static List<String[]> readCSV(String file) throws IOException, CsvException {
        Path path = Paths.get(file);
        Reader reader = Files.newBufferedReader(path);
        CSVReader csvReader = new CSVReader(reader);
        csvReader.skip(1);
        List<String[]> lines = csvReader.readAll();
        csvReader.close();
        return lines;
    }

    @SuppressWarnings("unchecked")
    private static Set<Manufacturer> readManufacturers() throws Exception {
        final List<String[]> lines = readCSV(CSV_MANUFACTURERS);

        final Set<Manufacturer> manufacturers = lines.stream()
                .map(line -> new Manufacturer(Long.valueOf(line[0]), line[1]))
                .collect(Collectors.toCollection(HashSet::new));

        return manufacturers;
    }

    @SuppressWarnings("unchecked")
    private static Set<Client> readClients() throws Exception {
        final List<String[]> lines = readCSV(CSV_CLIENTS);

        final Set<Client> clients = lines.stream()
                .map(line -> new Client(Long.valueOf(line[0]), line[1], line[2], line[3], line[4]))
                .collect(Collectors.toCollection(HashSet::new));

        return clients;
    }

    @SuppressWarnings("unchecked")
    private static Set<Employee> readEmployee() throws Exception {
        final List<String[]> lines = readCSV(CSV_EMPLOYEES);

        final Set<Employee> employee = lines.stream()
                .map(line -> new Employee(Long.valueOf(line[0]), line[1], line[2], line[3], line[4]))
                .collect(Collectors.toCollection(HashSet::new));

        return employee;
    }

    @SuppressWarnings("unchecked")
    private static Set<Appliance> readAppliances() throws Exception {
        final List<String[]> lines = readCSV(CSV_APPLIANCES);

        final Set<Appliance> appliances = lines.stream()
                .map(line -> new Appliance(Long.valueOf(line[0])
                        , line[1]
                        , Category.valueOf(line[2])
                        , line[3]
                        , getManufacturer(Long.valueOf(line[4]))
                        , PowerType.valueOf(line[5])
                        , line[6]
                        , line[7]
                        , Integer.valueOf(line[8])
                ))
                .collect(Collectors.toCollection(HashSet::new));

        return appliances;
    }

    private static Manufacturer getManufacturer(long id) {
        return correctManufacturer.stream()
                .filter(manufacturer -> manufacturer.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No manufacturer with id " + id));
    }

    @SuppressWarnings("unchecked")
    private static Set<Order> readOrders() throws Exception {
        final List<String[]> lines = readCSV(CSV_ORDERS);
        final List<String[]> linesA = readCSV(CSV_APPLIANCES_IN_ORDERS);

        final Set<Order> orders = lines.stream()
                .map(line -> new Order(Long.valueOf(line[0])
                        , getClient(Long.valueOf(line[1]))
                        , getEmployee(Long.valueOf(line[2]))
                        , linesA.stream()
                        .filter(lineAppliance -> Long.valueOf(lineAppliance[0]) == Long.valueOf(line[0]))
                        .collect(Collectors.toMap(lineAppliance -> getAppliance(Long.valueOf(lineAppliance[1]))
                                , lineAppliance -> new BigDecimal(lineAppliance[2])))))
                .collect(Collectors.toCollection(HashSet::new));

        return orders;
    }

    private static Appliance getAppliance(long id) {
        return correctAppliances.stream()
                .filter(appliance -> appliance.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No appliance with id " + id));
    }

    private static Employee getEmployee(long id) {
        return correctEmployees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No employee with id " + id));
    }

    private static Client getClient(long id) {
        return correctClient.stream()
                .filter(client -> client.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No client with id " + id));
    }

    private static Order getOrderById(long id) {
        return correctOrders.stream()
                .filter(order -> order.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No order with id " + id));
    }
}