package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

public class ReportUpdater implements Runnable {

    private BigDecimal currentSum;

    public ReportUpdater(BigDecimal currentSum) {
        this.currentSum = currentSum;
    }

    @Override
    public void run() {
        StringBuilder toReport = new StringBuilder();
        toReport.append("The current sum is: ");
        toReport.append(currentSum);

        File file = new File("report.txt");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(toReport.toString());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return;
        }
    }
}
