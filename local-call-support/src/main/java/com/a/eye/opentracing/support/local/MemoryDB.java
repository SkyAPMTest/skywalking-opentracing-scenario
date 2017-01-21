package com.a.eye.opentracing.support.local;

import com.a.eye.skywalking.toolkit.opentracing.SkyWalkingTracer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.opentracing.Span;
import io.opentracing.Tracer;

/**
 * {@link MemoryDB} will store some String object when initialize new instance
 * and also support to find method.
 *
 * @author zhangxin
 */
public class MemoryDB {

    private List<String> db = new ArrayList<String>();

    public MemoryDB(String... items) {
        Collections.addAll(db, items);
    }

    public String find(String item, Span parentSpan) {
        Tracer.SpanBuilder spanBuilder = SkyWalkingTracer.INSTANCE.buildSpan("MemoryDB.find");
        Span span = spanBuilder.withStartTimestamp(System.currentTimeMillis()).asChildOf(parentSpan).start();
        int index = db.indexOf(item);
        span.finish();
        return db.get(index);
    }
}
