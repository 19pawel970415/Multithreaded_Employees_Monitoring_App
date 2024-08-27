package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;

public class ReportUpdater implements Runnable {

    private BigDecimal currentSum;
    private final CountDownLatch latch;

    public ReportUpdater(BigDecimal currentSum, CountDownLatch latch) {
        this.currentSum = currentSum;
        this.latch = latch;
    }

    @Override
    public void run() {
        StringBuilder toReport = new StringBuilder();
        toReport.append("The current sum is: ");
        toReport.append(currentSum);

        File file = new File("report.txt");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(toReport.toString());
            writer.flush();
            latch.countDown();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return;
        }
    }
}
