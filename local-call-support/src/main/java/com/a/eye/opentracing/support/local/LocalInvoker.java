package com.a.eye.opentracing.support.local;

import com.a.eye.skywalking.toolkit.opentracing.SkyWalkingTracer;

import io.opentracing.Tracer;

/**
 * @author zhangxin
 */
public class LocalInvoker {

    private static final String[] MEMORY_DB_DATA = new String[]{"A", "B", "C", "D"};
    private static MemoryDB memoryDB = new MemoryDB(MEMORY_DB_DATA);

    public static void main(String[] args) {
        MemoryDBSearcher searcher = new MemoryDBSearcher(memoryDB);
        System.out.println(searcher.search("B", 2));
    }
}
