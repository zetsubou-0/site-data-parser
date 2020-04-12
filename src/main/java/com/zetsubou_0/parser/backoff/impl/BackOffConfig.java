package com.zetsubou_0.parser.backoff.impl;

import com.zetsubou_0.parser.backoff.ThrowableConsumer;

import java.util.function.Consumer;

public class BackOffConfig {
    private double delay = 100;
    private double multiplier = 1.5;
    private int max = 60_000;
    private ThrowableConsumer executor = () -> {};
    private Consumer<Exception> exceptionLogger = e -> {};

    public double getDelay() {
        return delay;
    }

    public void setDelay(double delay) {
        if (delay < 100 || delay > max) {
            return;
        }
        this.delay = delay;
    }

    public double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(double multiplier) {
        if (multiplier < 1) {
            return;
        }
        this.multiplier = multiplier;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        if (max < 100 || max < delay) {
            return;
        }
        this.max = max;
    }

    public ThrowableConsumer getExecutor() {
        return executor;
    }

    public void setExecutor(ThrowableConsumer executor) {
        if (executor == null) {
            return;
        }
        this.executor = executor;
    }

    public Consumer<Exception> getExceptionLogger() {
        return exceptionLogger;
    }

    public void setExceptionLogger(Consumer<Exception> exceptionLogger) {
        this.exceptionLogger = exceptionLogger;
    }
}
