package org.softwaretechnologies.employee;

import java.time.LocalDate;

public class Manager extends Employee{
    public Manager(String name, int baseSalary) {
        super(name, baseSalary);
    }

    @Override
    public int getMonthSalary(int month) {
        return EmployeeType.Manager.getMonthSalary(month);
    }
}
