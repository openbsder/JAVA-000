package com.test.thread.demo;

import java.util.concurrent.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Exception {
        // 1.通过join
        Worker worker = new Worker();
        Thread t1 = new Thread(worker);
        t1.start();
        t1.join();
        System.out.println("Thread return (join): " + worker.getResult());

        // 2.通过CountDownLatch
        CountDownLatch countDownLatch = new CountDownLatch(1);
        WorkerCountDownLatch workerCountDownLatch = new WorkerCountDownLatch(countDownLatch);
        Thread t2 = new Thread(workerCountDownLatch);
        t2.start();
        countDownLatch.await();
        System.out.println("Thread return (CountDownLatch):" + workerCountDownLatch.getRes());

        // 3. 通过FutureTask
        FutureTask<String> futureTask = new FutureTask<>(new WorkerCallable());
        new Thread(futureTask).start();
        System.out.println("Thread return (FutureTask): " + futureTask.get());

        // 4. 通过Future - thead pool
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        try {
            Future<?> future = executorService.submit(new WorkerCallable());
            System.out.println("Thread return (Future with thread pool): " + future.get());
        } finally {
            executorService.shutdown();
        }

        // 5. 通过CompletableFuture
        CompletableFuture<String> completedFuture = CompletableFuture.supplyAsync(new WorkerSupply());
        System.out.println("Thread return (CompletableFuture): " + completedFuture.get());
    }
}
