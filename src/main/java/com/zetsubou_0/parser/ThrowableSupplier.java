package com.zetsubou_0.parser;

public interface ThrowableSupplier<T> {
    T execute() throws Exception;
}
