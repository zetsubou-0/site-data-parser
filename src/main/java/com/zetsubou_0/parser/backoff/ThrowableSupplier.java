package com.zetsubou_0.parser.backoff;

public interface ThrowableSupplier<T> {
    T execute() throws Exception;
}
