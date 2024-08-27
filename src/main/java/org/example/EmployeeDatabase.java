package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeDatabase {
    static volatile List<Employee> employees = new ArrayList<>();

    static void addEmployee(Employee employee) {
        employees.add(employee);
    }

    static List<BigDecimal> getSalaries() {
        List<BigDecimal> salaries = employees.stream()
                .map(e -> e.getSalary())
                .collect(Collectors.toList());
        return salaries;
    }
}
