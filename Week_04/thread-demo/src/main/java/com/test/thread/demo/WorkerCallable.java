package com.test.thread.demo;

import java.util.concurrent.Callable;

public class WorkerCallable implements Callable<String> {
    public String call() {
        return "worker";
    }
}
