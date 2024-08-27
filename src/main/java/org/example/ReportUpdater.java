package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class ReportUpdater implements Runnable {

    private BigDecimal currentSum;
    private final CyclicBarrier cyclicBarrier;

    public ReportUpdater(BigDecimal currentSum, CyclicBarrier cyclicBarrier) {
        this.currentSum = currentSum;
        this.cyclicBarrier = cyclicBarrier;
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
            cyclicBarrier.await();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return;
        } catch (BrokenBarrierException e) {
            System.err.println(e.getMessage());
            return;
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
            return;
        }
    }
}
