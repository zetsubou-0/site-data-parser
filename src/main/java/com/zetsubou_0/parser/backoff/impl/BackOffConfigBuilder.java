package com.zetsubou_0.parser.backoff.impl;

import com.zetsubou_0.parser.backoff.ThrowableConsumer;

import java.util.function.Consumer;

public class BackOffConfigBuilder {
    private final BackOffConfig backOffConfig = new BackOffConfig();

    private BackOffConfigBuilder() {
    }

    public static BackOffConfigBuilder builder() {
        return new BackOffConfigBuilder();
    }

    public static BackOffConfigBuilder defaultBuilder() {
        return new BackOffConfigBuilder()
                .setInitial(100)
                .setMax(10_000)
                .setMultiplier(2)
                .setExceptionLogger(Throwable::printStackTrace)
                .setExecutor(() -> {});
    }

    public BackOffConfigBuilder setInitial(int initial) {
        backOffConfig.setDelay(initial);
        return this;
    }

    public BackOffConfigBuilder setMultiplier(double multiplier) {
        backOffConfig.setMultiplier(multiplier);
        return this;
    }

    public BackOffConfigBuilder setMax(int max) {
        backOffConfig.setMax(max);
        return this;
    }

    public BackOffConfigBuilder setExecutor(ThrowableConsumer executor) {
        backOffConfig.setExecutor(executor);
        return this;
    }

    public BackOffConfigBuilder setExceptionLogger(Consumer<Exception> logger) {
        backOffConfig.setExceptionLogger(logger);
        return this;
    }

    public BackOffConfig build() {
        return backOffConfig;
    }
}
