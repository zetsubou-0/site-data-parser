package com.zetsubou_0.parser;

@FunctionalInterface
public interface ThrowableConsumer {
    void execute() throws Exception;
}
