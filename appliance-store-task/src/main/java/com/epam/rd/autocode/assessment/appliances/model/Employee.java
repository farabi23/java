package com.epam.rd.autocode.assessment.appliances.model;

import java.util.Objects;

public class Employee extends User{
    private String department;

    public Employee() {
    }

    public Employee(long id, String name, String email, String password, String department) {
        super(id, name, email, password);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return Objects.equals(department, employee.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), department);
    }

    @Override
    public String toString() {
        return "Employee{id="+super.getId()+
                ", name='"+super.getName()+'\'' +
                ", email='"+super.getEmail()+'\'' +
                ", password='"+super.getPassword()+'\'' +
                ", department='" + department + '\'' +
                "}";
    }
}
