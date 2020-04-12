package com.zetsubou_0.parser.backoff.impl;

import com.zetsubou_0.parser.backoff.BackOff;
import com.zetsubou_0.parser.backoff.ThrowableConsumer;

public class BackOffImpl implements BackOff {

    @Override
    public void execute(BackOffConfig config) {
        final ThrowableConsumer executor = config.getExecutor();
        execute(config, executor, config.getDelay());
    }

    private void execute(BackOffConfig config, ThrowableConsumer executor, double currentDelay) {
        try {
            executor.execute();
        } catch (Exception e) {
            try {
                handleException(config, executor, currentDelay, e);
            } catch (Exception ex) {
                config.getExceptionLogger().accept(ex);
            }
        }
    }

    private void handleException(BackOffConfig config, ThrowableConsumer executor, double currentDelay, Exception e) throws Exception {
        if (currentDelay >= config.getMax()) {
            throw new Exception("Maximum number of attempts reached. ", e);
        }
        System.out.println("Performing action with " + currentDelay + " ms delay.");
        log(e);
        Thread.sleep((long) currentDelay);
        execute(config, executor, currentDelay * config.getMultiplier());
    }

    private void log(Exception e) {
        System.err.println("Exception during retry: " + e.getMessage());
        e.printStackTrace(System.err);
    }
}
