package org.softwaretechnologies.employee;

import java.time.LocalDate;
import java.time.YearMonth;

public class Tester extends Employee{
    public Tester(String name, int baseSalary) {
        super(name, baseSalary);
    }

    @Override
    public int getMonthSalary(int month) {
        return EmployeeType.Tester.getMonthSalary(month);
    }
}
