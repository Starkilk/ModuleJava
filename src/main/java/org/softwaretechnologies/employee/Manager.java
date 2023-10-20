package org.softwaretechnologies.employee;

public class Manager extends Employee{
    protected Manager(String name, int baseSalary) {
        super(name, baseSalary);
    }

    @Override
    public int getMonthSalary(int month) {
        return 0;
    }
}
