package learn.servlets.app01a;

import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

@WebServlet(name = "GenericServletDemoServlet",
        urlPatterns = {"/generic"},
        initParams = {
                @WebInitParam(name = "admin", value = "Jane Doe"),
                @WebInitParam(name = "email", value = "admin@example.com")
        })
public class GenericServletDemoServlet extends GenericServlet {

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
                "<p>Date/time: " + LocalDateTime.now() + "</p>",
                "</body>",
                "</html>")
        );
    }
}
