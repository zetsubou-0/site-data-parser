package com.zetsubou_0.parser.model;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class ApplicationConfiguration {

    private static final int DEFAULT_THREAD_POOL_SIZE = 10;

    private final String fileRootPath;
    private final int threadPoolSize;
    private final boolean valid;

    public ApplicationConfiguration(String[] args) {
        this.valid = !ArrayUtils.isEmpty(args);
        this.fileRootPath = args[0];
        this.threadPoolSize = args.length >= 2
                ? NumberUtils.toInt(args[1], DEFAULT_THREAD_POOL_SIZE)
                : DEFAULT_THREAD_POOL_SIZE;
    }

    public String getFileRootPath() {
        return fileRootPath;
    }

    public int getThreadPoolSize() {
        return threadPoolSize;
    }

    public boolean isValid() {
        return valid;
    }
}
