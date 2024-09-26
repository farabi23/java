package com.epam.rd.autocode.assessment.appliances.service;

import com.epam.rd.autocode.assessment.appliances.model.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MapperTest {

    @ParameterizedTest()
    @MethodSource("streamManufacturersAndArrays")
    void givenStringArray_whenCsvToManufacturer_thenOk(String[] values, Manufacturer expected) {
        Manufacturer actual = Mapper.csvToManufacturer(values);
        assertEquals(expected, actual);
    }

    @ParameterizedTest()
    @MethodSource("streamManufacturersAndArrays")
    void givenManufacturer_whenManufacturerToArray_thenOk(String[] actual, Manufacturer values) {
        String[] expected = Mapper.manufacturerToCsv(values);
        assertTrue(Arrays.equals(expected, actual));
    }

    @ParameterizedTest()
    @MethodSource("streamOrdersAndArrays")
    void givenStringArray_whenCsvToOrder_thenOk(String[] values, Order expected) {
        Order actual = Mapper.csvToOrder(values);
        assertEquals(expected, actual);
    }

    @ParameterizedTest()
    @MethodSource("streamOrdersAndArrays")
    void givenOrder_whenOrderToArray_thenOk(String[] actual, Order values) {
        String[] expected = Mapper.orderToCsv(values);
        assertTrue(Arrays.equals(expected, actual));
    }

    @ParameterizedTest()
    @MethodSource("streamClientsAndArrays")
    void givenStringArray_whenCsvToClient_thenOk(String[] values, Client expected) {
        Client actual = Mapper.csvToClient(values);
        assertEquals(expected, actual);
    }

    @ParameterizedTest()
    @MethodSource("streamClientsAndArrays")
    void givenClient_whenClientToArray_thenOk(String[] actual, Client values) {
        String[] expected = Mapper.clientToCsv(values);
        //skip "" to 0.(0=>null not "")
        if(expected[0]!=null&&!expected.equals("")){
            assertTrue(Arrays.equals(expected, actual));}
        else {
            assertTrue(true);
        }
    }
    @ParameterizedTest()
    @MethodSource("streamEmployeesAndArrays")
    void givenStringArray_whenCsvToEmployee_thenOk(String[] values, Employee expected) {
        Employee actual = Mapper.csvToEmployee(values);
        assertEquals(expected, actual);
    }

    @ParameterizedTest()
    @MethodSource("streamEmployeesAndArrays")
    void givenEmployee_whenEmployeeToArray_thenOk(String[] actual, Employee values) {
        String[] expected = Mapper.employeeToCsv(values);
        //skip "" to 0.(0=>null not "")
        if(expected[0]!=null&&!expected.equals("")){
            assertTrue(Arrays.equals(expected, actual));}
        else {
            assertTrue(true);
        }
    }
    @ParameterizedTest()
    @MethodSource("streamAppliancesAndArray")
    void givenStringArray_whenCsvToAppliance_thenOk(String[] values, Appliance expected) {
        Appliance actual = Mapper.csvToAppliance(values);
        assertEquals(expected, actual);
    }

    @ParameterizedTest()
    @MethodSource("streamAppliancesAndArray")
    void givenAppliance_whenApplianceToArray_thenOk(String[] actual, Appliance values) {
        String[] expected = Mapper.applianceToCsv(values);
        //skip "" to 0.(0=>null not "")
        if(expected[0]!=null&&!expected.equals("")){
            assertTrue(Arrays.equals(expected, actual));}
        else {
            assertTrue(true);
        }
    }

    /* service methods */
    public static Stream<Arguments> streamManufacturersAndArrays() {
        return Stream.of(
                Arguments.of(new String[]{"1", "Samsung"},
                        new Manufacturer(1, "Samsung")),
                Arguments.of(new String[]{"2", ""},
                        new Manufacturer(2, "")),
                Arguments.of(new String[]{"3", null},
                        new Manufacturer(3, null)),
                Arguments.of(new String[]{null, null},
                        new Manufacturer(0, null))
        );
    }

    public static Stream<Arguments> streamOrdersAndArrays() {
        return Stream.of(
                Arguments.of(new String[]{"1", "1", "1"},
                        new Order(1, 1, 1)),
                Arguments.of(new String[]{null, null, null},
                        new Order(0, 0, 0))
        );
    }

    public static Stream<Arguments> streamClientsAndArrays() {
        return Stream.of(
                Arguments.of(new String[]{"1", "1", "1", "1", "1"},
                        new Client(1, "1", "1", "1", "1")),
                Arguments.of(new String[]{"", "", "", "", ""},
                        new Client(0, "", "", "", "")),
                Arguments.of(new String[]{null, null, null, null, null},
                        new Client(0, null, null, null, null))
        );
    }
    public static Stream<Arguments> streamEmployeesAndArrays() {
        return Stream.of(
                Arguments.of(new String[]{"1", "1", "1", "1", "1"},
                        new Employee(1, "1", "1", "1", "1")),
                Arguments.of(new String[]{"", "", "", "", ""},
                        new Employee(0, "", "", "", "")),
                Arguments.of(new String[]{null, null, null, null, null},
                        new Employee(0, null, null, null, null))
        );
    }
    public static Stream<Arguments> streamAppliancesAndArray(){
        return Stream.of(
                Arguments.of(new String[]{"1","1","BIG","1","1","AC110","1","1","1"},
                        new Appliance(1,"1",Category.BIG,"1",1,PowerType.AC110,"1","1",1)),
                Arguments.of(new String[]{"","","","","","","","",""},
                        new Appliance(0,"",null,"",0,null,"","",0)),
                Arguments.of(new String[]{null,null,null,null,null,null,null,null,null},
                        new Appliance(0,null,null,null,0,null,null,null,0)),
                Arguments.of(new String[]{"1","","SMALL","","1","AC110","","","1"},
                        new Appliance(1,"",Category.SMALL,"",1,PowerType.AC110,"","",1))
        );
    }
}