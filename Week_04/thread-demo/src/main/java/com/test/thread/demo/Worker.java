package com.test.thread.demo;

public class Worker implements Runnable {
    private volatile String result;

    public void run() {
        result = "worker";
    }

    public String getResult() {
        return result;
    }
}
