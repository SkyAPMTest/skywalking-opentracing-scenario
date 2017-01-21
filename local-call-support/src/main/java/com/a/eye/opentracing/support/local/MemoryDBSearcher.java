package com.a.eye.opentracing.support.local;

import com.a.eye.skywalking.toolkit.opentracing.SkyWalkingTracer;

import io.opentracing.Span;
import io.opentracing.Tracer;

/**
 * Created by xin on 2017/1/21.
 */
public class MemoryDBSearcher {

    private final MemoryDB db;

    public MemoryDBSearcher(MemoryDB db) {
        this.db = db;
    }

    public String search(String search, int loopCount) {
        String result = null;
        Tracer.SpanBuilder spanBuilder = SkyWalkingTracer.INSTANCE.buildSpan("MemoryDB.search");
        Span span = spanBuilder.withStartTimestamp(System.currentTimeMillis()).start();
        for (int i = 0; i < loopCount; i++) {
            result = db.find(search, span);
        }
        span.finish();
        return result;
    }
}
