package org.skywalking.apm.testcase.opentracing;

import io.opentracing.ActiveSpan;
import io.opentracing.Span;
import io.opentracing.Tracer;
import io.opentracing.tag.Tags;
import org.skywalking.apm.toolkit.opentracing.SkywalkingTracer;

/**
 * Created by xin on 2017/7/18.
 */
public class OpentracingService {
    public void testLocalSpan(String name, ActiveSpan span) {
        Tracer.SpanBuilder spanBuilder = new SkywalkingTracer().buildSpan("OpentracingCase/testLocalSpan");
        Span localSpan = spanBuilder.withTag("name", name).asChildOf(span).startManual();
        Tags.COMPONENT.set(localSpan, "OpentracingService");
        testContinuation(localSpan);
        localSpan.finish();
    }

    private void testContinuation(Span localSpan) {
        Tracer.SpanBuilder spanBuilder = new SkywalkingTracer().buildSpan("OpentracingCase/testContinuation");
        ActiveSpan span = spanBuilder.withTag("name", "test").asChildOf(localSpan).startActive();
        new AsyncThread(span).start();
        span.deactivate();
    }

    class AsyncThread extends Thread {
        private ActiveSpan.Continuation continuation;

        public AsyncThread(ActiveSpan span) {
            continuation = span.capture();
        }

        @Override public void run() {
            ActiveSpan activeSpan = continuation.activate();
            activeSpan.setOperationName("OpentracingCase/run");
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("query data");
            activeSpan.deactivate();
        }
    }

}
