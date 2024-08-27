package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmployeeDatabase {
    private static List<Employee> employees = new ArrayList<>();

    void addEmployee(Employee employee) {
        employees.add(employee);
    }

    static List<BigDecimal> getSalaries() {
        List<BigDecimal> salaries = employees.stream()
                .map(e -> e.getSalary())
                .collect(Collectors.toList());
        return salaries;
    }
}
