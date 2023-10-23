package org.softwaretechnologies.employee;

import java.lang.annotation.ElementType;

public class Programmer extends Employee{
    public Programmer(String name, int baseSalary) {
        super(name, baseSalary);
    }

    @Override
    public int getMonthSalary(int month) {
        return baseSalary;
    }
}
