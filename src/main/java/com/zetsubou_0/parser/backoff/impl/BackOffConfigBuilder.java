package com.zetsubou_0.parser.backoff.impl;

import com.zetsubou_0.parser.ThrowableConsumer;
import com.zetsubou_0.parser.ThrowableSupplier;
import org.apache.commons.lang3.StringUtils;

import java.util.Random;

public class BackOffConfigBuilder<T> {
    private final BackOffConfig<T> backOffConfig = new BackOffConfig<>();

    private BackOffConfigBuilder() {
    }

    public static <T> BackOffConfigBuilder<T> builder() {
        return new BackOffConfigBuilder<>();
    }

    public static <T> BackOffConfigBuilder<T> defaultBuilder() {
        final Random random = new Random();
        return new BackOffConfigBuilder<T>()
                .setInitial(300 + random.nextInt(300))
                .setMax(30_000)
                .setMultiplier(1 + random.nextDouble() * 0.2)
                .setMessage(StringUtils.EMPTY)
                .setSupplier(() -> null)
                .setConsumer(() -> {});
    }

    public BackOffConfigBuilder<T> setInitial(int initial) {
        backOffConfig.setDelay(initial);
        return this;
    }

    public BackOffConfigBuilder<T> setMultiplier(double multiplier) {
        backOffConfig.setMultiplier(multiplier);
        return this;
    }

    public BackOffConfigBuilder<T> setMax(int max) {
        backOffConfig.setMax(max);
        return this;
    }

    public BackOffConfigBuilder<T> setConsumer(ThrowableConsumer executor) {
        backOffConfig.setConsumer(executor);
        return this;
    }

    public BackOffConfigBuilder<T> setSupplier(ThrowableSupplier<T> executor) {
        backOffConfig.setSupplier(executor);
        return this;
    }

    public BackOffConfigBuilder<T> setMessage(String message) {
        backOffConfig.setMessage(message);
        return this;
    }

    public BackOffConfig<T> build() {
        return backOffConfig;
    }
}
