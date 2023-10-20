package org.softwaretechnologies;

import org.softwaretechnologies.employee.*;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private final String name;
    private final List<Employee> employeeList;

    public Company(String name) {
        this.name = name;
        this.employeeList = new ArrayList<>();
    }

    /**
     * Создает и добавляет сотрудника в коллекцию employeeList.
     * @param name имя работника
     * @param baseSalary базовая зарплата сотрудника
     * @param type тип работника
     */
    public void addEmployee(String name, int baseSalary, EmployeeType type) {
        if(type == EmployeeType.Manager){
            Employee employee = new Manager(name,baseSalary);
            employeeList.add(employee);
        }

        if(type == EmployeeType.Programmer){
            Employee employee = new Programmer(name,baseSalary);
            employeeList.add(employee);
        }

        if(type == EmployeeType.Tester){
            Employee employee = new Tester(name,baseSalary);
            employeeList.add(employee);
        }

    }

    /**
     * Возвращает сумму зарплат всех сотрудников за указанный месяц
     * @param month номер месяца
     * @return сумма зарплат всех сотрудников за указанный месяц
     */
    public int getMonthSalary(int month) {
        int counter = 0;
        for(Employee employee : employeeList){
            counter += employee.getMonthSalary(month);
        }

        return counter;
    }

    public String getName() {
        return name;
    }
}
