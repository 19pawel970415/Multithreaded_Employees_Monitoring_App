package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class Employee {
    private String name;
    private String surname;
    private BigDecimal salary;
}
