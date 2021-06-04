package learn.servlets.app01a;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.time.LocalDateTime;

@WebServlet(name = "MyServlet", urlPatterns = {"/my"})
public class MyServlet implements Servlet {

    private transient ServletConfig servletConfig;

    @Override
    public void init(ServletConfig config) {
        this.servletConfig = config;
    }

    @Override
    public ServletConfig getServletConfig() {
        return servletConfig;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws IOException {
        String servletName = servletConfig.getServletName();
        res.setContentType("text/html");
        PrintWriter writer = res.getWriter();
        writer.print(String.join(System.lineSeparator(),
                "<html>",
                "<head>",
                "</head>",
                "<body>",
                "Hello from " + servletName + "<br>",
                "Current date/time: " + LocalDateTime.now().toString() + "<br>",
                "Current path: " + Path.of(".").toRealPath(LinkOption.NOFOLLOW_LINKS) + "<br>",
                "Current working dir: " + System.getProperty("user.dir") + "<br>",
                "</body>",
                "</html>")
        );
    }

    @Override
    public String getServletInfo() {
        return "My Servlet";
    }

    @Override
    public void destroy() {

    }
}
