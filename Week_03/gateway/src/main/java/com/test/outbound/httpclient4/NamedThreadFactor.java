package com.test.outbound.httpclient4;

import java.util.Objects;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NamedThreadFactor implements ThreadFactory {
    private final ThreadGroup threadGroup;
    private final AtomicInteger threadNumber = new AtomicInteger();
    private final String namePrefix;
    private final boolean daemon;

    public NamedThreadFactor(String namePrefix) {
        this(namePrefix, false);
    }

    public NamedThreadFactor(String namePrefix, boolean daemon) {
        this.daemon = daemon;
        SecurityManager s = System.getSecurityManager();
        this.threadGroup = Objects.nonNull(s) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        this.namePrefix = namePrefix;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(threadGroup, r, namePrefix + "-thread-" + threadNumber.getAndIncrement(), 0);
        t.setDaemon(daemon);

        return t;
    }
}
