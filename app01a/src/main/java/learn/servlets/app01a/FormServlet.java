package learn.servlets.app01a;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(name = "FormServlet", urlPatterns = {"/form"})
public class FormServlet extends HttpServlet {

    private static final String NL = System.lineSeparator();
    private static final String TITLE = "Order Form";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println(String.join(NL,
                "<html>",
                "<head>",
                "<title>" + TITLE + "</title>",
                "<body>",
                "<h1>" + TITLE + "</h1>",
                "<form method='post'>",
                "<table>",
                "<tr>",
                "<td>Name:</td>",
                "<td><input name='name'/></td>",
                "</tr>",
                "<tr>",
                "<td>Address</td>",
                "<td><textarea name='address' cols='40' rows='5'></textarea></td>",
                "</tr>",
                "<tr>",
                "<td>Country:</td>",
                "<td><select name='country'>",
                "<option>United States</option>",
                "<option>Canada</option>",
                "</select></td>",
                "</tr>",
                "<tr>",
                "<td>Delivery Method:</td>",
                "<td><input type='radio' name='deliveryMethod' value='First Class'/>First Class",
                "<input type='radio' name='deliveryMethod' value='Second Class'/>Second Class</td>",
                "</tr>",
                "<tr>",
                "<td>Shipping Instructions:</td>",
                "<td><textarea name='instruction' cols='40' rows='5'></textarea></td>",
                "</tr>",
                "<tr>",
                "<td>&nbsp;</td>",
                "<td><textarea name='instruction' cols='40' rows='5'></textarea></td>",
                "</tr>",
                "<tr>",
                "<td>Please send me the latest product catalog:</td>",
                "<td><input type='checkbox' name='catalogRequest'/></td>",
                "</tr>",
                "<tr>",
                "<td>&nbsp;</td>",
                "<td><input type='reset'/><input type='submit'/></td>",
                "</tr>",
                "</table>",
                "</form>",
                "</body>",
                "</head>",
                "</html>",
                ""));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.println(String.join(NL,
                "<html>",
                "<head>",
                "<title>" + TITLE + "</title></head>",
                "</head>",
                "<body><h1>" + TITLE + "</h1>",
                "<table>",
                "<tr>",
                "<td>Name:</td>",
                "<td>" + req.getParameter("name") + "</td>",
                "</tr>",
                "<tr>",
                "<td>Address:</td>",
                "<td>" + req.getParameter("address") + "</td>",
                "</tr>",
                "<tr>",
                "<td>Country:</td>",
                "<td>" + req.getParameter("country") + "</td>",
                "</tr>",
                "<tr>",
                "<td>Shipping Instructions:</td>",
                "<td>",
                ""));
        String[] instructions = req.getParameterValues("instruction");
        if (instructions != null) {
            for (String instruction : instructions) {
                writer.println(instruction + "<br/>");
            }
        }
        writer.println(String.join(NL,
                "</td>",
                "</tr>",
                "<tr>",
                "<td>Delivery Method:</td>",
                "<td>" + req.getParameter("deliveryMethod") + "</td>",
                "</tr>",
                "<tr>",
                "<td>Catalog Request:</td>",
                "<td>",
                ""));
        if (req.getParameter("catalogRequest") == null) {
            writer.print("No");
        } else {
            writer.print("Yes");
        }
        writer.println(String.join(NL,
                "</td>",
                "</tr>",
                "</table>",
                "<div style='border:1px solid #ddd;margin-top:40px;font-size:90%'>",
                "Debug Info<br/>",
                ""));
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            writer.println(paramName + ": ");
            String[] paramValues = req.getParameterValues(paramName);
            for (String paramValue : paramValues) {
                writer.println(paramValue + "<br/>");
            }
        }
        writer.println(String.join(NL,
                "</div>",
                "</body>",
                "</html>",
                ""));
    }
}
