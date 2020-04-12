package com.zetsubou_0.parser.backoff;

import com.zetsubou_0.parser.backoff.impl.BackOffConfig;

public interface BackOff {
    void execute(BackOffConfig<?> config) throws Exception;
    <T> T produce(BackOffConfig<T> config) throws Exception;
}
