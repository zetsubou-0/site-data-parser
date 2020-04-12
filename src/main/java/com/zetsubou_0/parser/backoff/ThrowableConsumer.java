package com.zetsubou_0.parser.backoff;

@FunctionalInterface
public interface ThrowableConsumer {
    void execute() throws Exception;
}
