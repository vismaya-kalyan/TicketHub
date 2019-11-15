import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.*;
import java.text.DateFormat;
import java.time.*;
import java.util.ArrayList;

@WebServlet("/City")

public class City extends HttpServlet {

    /* Console Page Displays all the Consoles and their Information in Game Speed */

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        String name = null;
        ArrayList<String> cities = new ArrayList<String>();

        try {
            cities = MySqlDataStoreUtilities.getCities();
            System.out.println(cities);

        } catch (Exception e) {
            System.out.println(e);
        }

        Utilities utility = new Utilities(request, pw);
        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");
        pw.print("<div id='content' class='two-column'><div class='post'><h2 class='title meta'>");
        pw.print("<a style='font-size: 24px;'>Cities</a>");
        pw.print("</h2><div class='entry'>");

        for (int i = 0; i < cities.size(); i++) {
            // pw.print("<h4>" + cities.get(i) + "</h2>");

            pw.print("<form method='get' action='SpecificCity'>" + "<input type='hidden' name='matchcity' value='"
                    + cities.get(i) + "'>" + "<input type='submit' value='" + cities.get(i) + "'></form>");
        }
        pw.print("</div></div></div><div class='clear'></div>");
        utility.printHtml("Footer.html");

    }
}