package com.zetsubou_0.parser.backoff.impl;

import com.zetsubou_0.parser.backoff.BackOff;
import com.zetsubou_0.parser.ThrowableConsumer;
import com.zetsubou_0.parser.ThrowableSupplier;

public class BackOffImpl implements BackOff {

    @Override
    public void execute(BackOffConfig<?> config) throws Exception {
        execute(config, config.getConsumer(), config.getDelay());
    }

    @Override
    public <T> T produce(BackOffConfig<T> config) throws Exception {
        return executeWithReturn(config, config.getSupplier(), config.getDelay());
    }

    private void execute(BackOffConfig<?> config, ThrowableConsumer executor, double currentDelay) throws Exception {
        try {
            executor.execute();
        } catch (Exception e) {
            handleException(config, executor, currentDelay, e);
        }
    }

    private <T> T executeWithReturn(BackOffConfig<T> config, ThrowableSupplier<T> executor, double currentDelay) throws Exception {
        try {
            return executor.execute();
        } catch (Exception e) {
            return handleExceptionWithReturn(config, executor, currentDelay, e);
        }
    }

    private void handleException(BackOffConfig<?> config, ThrowableConsumer executor, double currentDelay, Exception e) throws Exception {
        handle(config, currentDelay, e);
        execute(config, executor, currentDelay * config.getMultiplier());
    }

    private <T> T handleExceptionWithReturn(BackOffConfig<T> config, ThrowableSupplier<T> executor, double currentDelay, Exception e) throws Exception {
        handle(config, currentDelay, e);
        return executeWithReturn(config, executor, currentDelay * config.getMultiplier());
    }

    private void handle(BackOffConfig<?> config, double currentDelay, Exception e) throws Exception {
        if (currentDelay >= config.getMax()) {
            throw new Exception("Maximum number of attempts reached. ", e);
        }
        System.out.println(String.format("Performing action with %.2f ms delay. %s", currentDelay, config.getMessage()));
        Thread.sleep((long) currentDelay);
    }
}
