package learn.servlets.app01a;

import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletConfigDemoServlet",
        urlPatterns = {"/servletConfigDemo"},
        initParams = {
                @WebInitParam(name = "admin", value = "Jane Doe"),
                @WebInitParam(name = "email", value = "admin@example.com")
        })
public class ServletConfigDemoServlet implements Servlet {

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
        ServletConfig servletConfig = getServletConfig();
        String admin = servletConfig.getInitParameter("admin");
        String email = servletConfig.getInitParameter("email");
        res.setContentType("text/html");
        PrintWriter writer = res.getWriter();
        writer.print(String.join(System.lineSeparator(),
                "<html>",
                "<head>",
                "</head>",
                "<body>",
                "<p>Admin: " + admin + "</p>",
                "<p>Email: " + email + "</p>",
                "</body>",
                "</html>")
        );
    }

    @Override
    public String getServletInfo() {
        return "ServletConfig demo";
    }

    @Override
    public void destroy() {
    }
}
