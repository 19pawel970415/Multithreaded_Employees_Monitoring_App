package org.example;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.*;

public class Main {

    private static final int UPPER_BOUND = 1001;
    private static Random random = new Random();

    public static void main(String[] args) {
        setUpDb();

        CountDownLatch latch = new CountDownLatch(1);

        EmployeeDataProcessor processor = new EmployeeDataProcessor();


        Iterator iterator = new Iterator();

        ExecutorService executor = Executors.newFixedThreadPool(2);
        ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();

        Future<?> iteratorSubmit = executor.submit(iterator);
        Future<BigDecimal> submit = executor.submit(processor);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
            return;
        }
        iteratorSubmit.cancel(true);


        BigDecimal sum = BigDecimal.ZERO;
        try {
             sum = submit.get();
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        } catch (ExecutionException e) {
            System.err.println(e.getMessage());
        }

        ReportUpdater reportUpdater = new ReportUpdater(sum, latch);
        scheduledExecutor.scheduleAtFixedRate(reportUpdater, 0,5000, TimeUnit.MILLISECONDS);

        try {
            latch.await();
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }

        executor.shutdown();
        scheduledExecutor.shutdown();
        try {
            boolean executorFinished = executor.awaitTermination(30000, TimeUnit.MILLISECONDS);
            boolean scheduledExecutorFinished = scheduledExecutor.awaitTermination(30000, TimeUnit.MILLISECONDS);
            if (!executorFinished) {
                executor.shutdownNow();
            }
            if (!scheduledExecutorFinished) {
                scheduledExecutor.shutdownNow();
            }
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
            executor.shutdownNow();
            System.out.println("Not done on time!");
            Thread.currentThread().interrupt();
            return;
        }
    }

    private static void setUpDb() {
        for (int i = 0; i < 5000; i++) {
            int randomId = random.nextInt(UPPER_BOUND);
            BigDecimal salary = BigDecimal.valueOf(random.nextInt(UPPER_BOUND));
            Employee employee = new Employee(Long.valueOf(randomId), salary);
            EmployeeDatabase.addEmployee(employee);
        }
    }
}