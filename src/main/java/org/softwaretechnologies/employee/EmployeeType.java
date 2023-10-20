package org.softwaretechnologies.employee;

import java.time.LocalDate;
import java.time.YearMonth;

/**
 * Тип сотрудника
 */
public enum EmployeeType {
    /*
    Формула вычисления зп: если месяц четный, то baseSalary, иначе baseSalary/2
     */
    Manager{
        @Override
        public int getMonthSalary(int month) {
            if (month % 2 == 0) {
                return baseSalary;
            } else {
                return baseSalary / 2;
            }
        }
    },

    /*
    Формула вычисления зп: всегда baseSalary
     */
    Programmer{
        @Override
        public int getMonthSalary(int month) {
            return baseSalary;
        }
    },
    /*
    Формула вычисления зп: baseSalary * количество дней в месяце в текущем году
    Вычисление количества дней в месяце: YearMonth.of(LocalDate.now().getYear(), month).lengthOfMonth()
     */
    Tester{
        @Override
        public int getMonthSalary(int month) {
            return baseSalary * YearMonth.of(LocalDate.now().getYear(), month).lengthOfMonth();
        }
    };

    protected int baseSalary;

    public void setBaseSalary(int baseSalary) {
        this.baseSalary = baseSalary;
    }

    public abstract int getMonthSalary(int month);

}
