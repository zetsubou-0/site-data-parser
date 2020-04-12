package com.zetsubou_0.parser.impl;

import com.zetsubou_0.parser.TaskExecutor;
import com.zetsubou_0.parser.model.ApplicationConfiguration;

import javax.inject.Inject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FixedTaskExecutor implements TaskExecutor {

    private final ExecutorService executorService;

    @Inject
    public FixedTaskExecutor(ApplicationConfiguration configuration) {
        executorService = Executors.newFixedThreadPool(configuration.getThreadPoolSize());
    }

    @Override
    public void execute(Runnable runnable) {
        executorService.execute(runnable);
    }

    @Override
    public void awaitShutdown(int time, TimeUnit timeUnit) {
        try {
            executorService.awaitTermination(time, timeUnit);
            if (!executorService.isShutdown() && !executorService.isTerminated()) {
                executorService.shutdown();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void awaitShutdown() {
        awaitShutdown(60, TimeUnit.MINUTES);
    }
}
