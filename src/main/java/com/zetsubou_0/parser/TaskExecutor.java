package com.zetsubou_0.parser;

import java.util.concurrent.TimeUnit;

public interface TaskExecutor {
    /**
     * Execute task
     * @param runnable {@link Runnable}
     */
    void execute(Runnable runnable);

    /**
     * Await task executor shutdown after time time
     * @param time time after which executor should be closed
     * @param timeUnit {@link TimeUnit}
     */
    void awaitShutdown(int time, TimeUnit timeUnit);

    /**
     * Await task executor shutdown after time default time
     */
    void awaitShutdown();
}
