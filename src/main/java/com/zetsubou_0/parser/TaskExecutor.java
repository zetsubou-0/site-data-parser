package com.zetsubou_0.parser;

public interface TaskExecutor {
    /**
     * Execute task
     * @param runnable {@link Runnable}
     */
    void execute(Runnable runnable);
}
