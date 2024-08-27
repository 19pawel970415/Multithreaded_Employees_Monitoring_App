package org.example;

import java.util.List;

public class Iterator implements Runnable {

    private List<Employee> employees = EmployeeDatabase.employees;

    @Override
    public void run() {
        while (true) {
            for (Employee employee : employees) {
                System.out.println("From iterator: " + employee);
                if (Thread.currentThread().isInterrupted()) {
                    return;
                }
            }
        }
    }
}
