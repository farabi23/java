package com.epam.rd.autocode.assessment.appliances.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.List;

import static com.epam.rd.autocode.assessment.appliances.model.TestConstants.*;
import static com.epam.rd.autocode.assessment.appliances.model.TestConstants.Appliance.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ApplianceTest {
    private static List<Field> allFields;
    private static List<Constructor<?>> allConstructors;
    private static List<Method> allMethods;

    @BeforeAll
    static void setup() throws ClassNotFoundException {
        final Class<?> clazz = Class.forName(APPLIANCE_TYPE);
        allFields = Arrays.asList(clazz.getDeclaredFields());
        allConstructors = Arrays.asList(clazz.getConstructors());
        allMethods = Arrays.asList(clazz.getDeclaredMethods());
    }

    /* Tests for CONSTRUCTORS */
    @Test
    @DisplayName("Count constructors")
    void checkCountConstructors() {
        assertEquals(CLASS_COUNT_CONSTRUCTORS, allConstructors.size());
    }

    @Test
    @DisplayName("Modifiers constructors can be public")
    void checkModifiersConstructors() {
        boolean actual = allConstructors.stream()
                .allMatch(constructor -> Modifier.isPublic(constructor.getModifiers()));
        assertTrue(actual);
    }

    @Test
    @DisplayName(CLASS_NAME + " has default constructor")
    void checkDefaultConstructor() {
        long count = allConstructors.stream()
                .filter(constructor -> constructor.getParameterCount() == 0)
                .count();
        assertEquals(1, count);
    }

    @Test
    @DisplayName(CLASS_NAME + " has constructor with " + PARAMETERS_IN_CONSTRUCTOR_WITH_PARAMETERS + " parameters")
    void checkConstructorWithParameter() {
        long count = allConstructors.stream()
                .filter(constructor -> constructor.getParameterCount() == PARAMETERS_IN_CONSTRUCTOR_WITH_PARAMETERS)
                .count();
        assertEquals(1, count);
    }

    @Test
    @DisplayName("Check parameter type in constructor with parameter")
    void checkParameterTypeForConstructorWithParameter() {
        final Constructor<?> constructor = allConstructors.stream()
                .filter(c -> c.getParameterCount() == PARAMETERS_IN_CONSTRUCTOR_WITH_PARAMETERS)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No constructor with parameters"));

        final List<Parameter> parameters = Arrays.asList(constructor.getParameters());


        parameters.stream()
                .filter(p -> p.getType().getTypeName().equals(LONG_TYPE))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No parameter with type " + LONG_TYPE));

        final long countString = parameters.stream()
                .filter(p -> p.getType().getTypeName().equals(STRING_TYPE))
                .count();

        parameters.stream()
                .filter(p -> p.getType().getTypeName().equals(CATEGORY_TYPE))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No parameter with type " + CLASS_PACKAGE + "." + TestConstants.Category.ENUM_NAME));

        parameters.stream()
                .filter(p -> p.getType().getTypeName().equals(LONG_TYPE))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No parameter with type " + CLASS_PACKAGE + "." + LONG_TYPE));

        parameters.stream()
                .filter(p -> p.getType().getTypeName().equals(POWER_TYPE_TYPE))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No parameter with type " + CLASS_PACKAGE + "." + TestConstants.PowerType.ENUM_NAME));

        parameters.stream()
                .filter(p -> p.getType().getTypeName().equals(INT_TYPE))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No parameter with type " + INT_TYPE));

        assertEquals(4, countString);
    }

    /* Tests for FIELDS */
    @Test
    @DisplayName("Count fields")
    void checkCountFields() {
        assertEquals(CLASS_COUNT_FIELDS, allFields.size());
    }

    @Test
    @DisplayName("Modifiers fields can be private")
    void checkModifiersFields() {
        long count = allFields.stream()
                .filter(f -> Modifier.isPrivate(f.getModifiers()))
                .count();

        assertEquals(CLASS_COUNT_FIELDS, count);
    }

    @ParameterizedTest
    @CsvSource({"id,1",
            "name,1",
            "client,0",
            "employee,0",
            "category,1",
            "model,1",
            "manufacturerId,1",
            "powerType,1",
            "characteristic,1",
            "description,1",
            "power,1"
    })
    @DisplayName("To " + CLASS_NAME + " check fields name")
    void checkFieldNameName(String name, long expected) {
        final long count = allFields.stream()
                .filter(f -> f.getName().equals(name))
                .count();
        assertEquals(expected, count);
    }

    /*not for student*/
    @DisplayName("Check field type and field name")
    @ParameterizedTest
    @CsvFileSource(resources = "/ApplianceFields.csv")
    void checkNameFieldType(String fieldType, String fieldName, long expected) {
        final long countLong = allFields.stream()
                .filter(f -> f.getType().getTypeName().equals(fieldType)
                        & f.getName().equals(fieldName))
                .count();
        assertEquals(expected, countLong);
    }

    /**/
    @DisplayName("Check getter and setter")
    @ParameterizedTest
    @CsvFileSource(resources = "/ApplianceMethod.csv")
    void checkGetterAndSetter(String methodName,long expected) {
        final long countLong = allMethods.stream()
                .filter(m -> m.getName().equals(methodName))
                .count();
        assertEquals(expected, countLong);
    }
}