package com.zetsubou_0.parser.impl;

import com.zetsubou_0.parser.TaskExecutor;
import com.zetsubou_0.parser.model.ApplicationConfiguration;

import javax.inject.Inject;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class FixedTaskExecutor implements TaskExecutor {

    private final Semaphore semaphore;

    private boolean submitted;

    @Inject
    public FixedTaskExecutor(ApplicationConfiguration configuration) {
        semaphore = new Semaphore(configuration.getThreadPoolSize());
    }

    @Override
    public void execute(Runnable runnable) {
        new Thread(() -> {
            try {
                semaphore.tryAcquire(30, TimeUnit.MINUTES);
                runnable.run();
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
