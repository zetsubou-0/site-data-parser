package com.zetsubou_0.parser.backoff.impl;

import com.zetsubou_0.parser.ThrowableConsumer;
import com.zetsubou_0.parser.ThrowableSupplier;

public class BackOffConfig<T> {
    private double delay = 100;
    private double multiplier = 1.5;
    private int max = 60_000;
    private ThrowableConsumer consumer = () -> {};
    private ThrowableSupplier<T> supplier = () -> null;
    private String message;

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

    public ThrowableSupplier<T> getSupplier() {
        return supplier;
    }

    public void setSupplier(ThrowableSupplier<T> supplier) {
        this.supplier = supplier;
    }

    public ThrowableConsumer getConsumer() {
        return consumer;
    }

    public void setConsumer(ThrowableConsumer consumer) {
        if (consumer == null) {
            return;
        }
        this.consumer = consumer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
