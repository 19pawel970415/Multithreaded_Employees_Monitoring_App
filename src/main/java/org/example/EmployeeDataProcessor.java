package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class EmployeeDataProcessor implements Callable<BigDecimal> {

    @Override
    public BigDecimal call() throws Exception {
        List<BigDecimal> employeesSalaries = EmployeeDatabase.getSalaries();
        BigDecimal sum = employeesSalaries.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return sum;
    }
}
