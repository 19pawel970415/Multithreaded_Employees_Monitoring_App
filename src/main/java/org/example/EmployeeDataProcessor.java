package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class EmployeeDataProcessor implements Callable<BigDecimal> {

    List<BigDecimal> employeesSalaries = EmployeeDatabase.getSalaries();

    @Override
    public BigDecimal call() throws Exception {
        BigDecimal sum = employeesSalaries.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return sum;
    }
}
