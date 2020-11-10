package com.test.thread.demo;

import java.util.concurrent.CountDownLatch;

public class WorkerCountDownLatch implements Runnable {
    private final CountDownLatch countDownLatch;
    private volatile String res;

    public WorkerCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        res = "worker";
        countDownLatch.countDown();
    }

    public String getRes() {
        return res;
    }
}
