package org.skywalking.apm.testcase.opentracing;

import io.opentracing.ActiveSpan;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.skywalking.apm.toolkit.opentracing.SkywalkingTracer;

public class CasesServlet extends HttpServlet {

    private OpentracingService service = new OpentracingService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ActiveSpan span = new SkywalkingTracer().buildSpan("OpentracingCase/doGet").startActive();
        service.testLocalSpan("Test", span);
        try {
            Thread.sleep(2 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        span.deactivate();
        PrintWriter printWriter = resp.getWriter();
        printWriter.write("success");
        printWriter.flush();
        printWriter.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
