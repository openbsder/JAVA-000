package com.test.thread.demo;

import java.util.function.Supplier;

public class WorkerSupply implements Supplier<String> {
    @Override
    public String get() {
        return "worker";
    }
}
