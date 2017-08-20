package com.jxkj.readapp.util;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by An on 2017/8/18.
 */

public class Acache  {

    public class AcacheManager{
        private AtomicLong cacheSize;
        private AtomicInteger cacheCount;
        private long sizeLimit;
        private int countLimit;
        private Map<File,Long> lastUsageDates = Collections.synchronizedMap(new HashMap<File,Long>());
        protected File cacheDir;

    }
}
