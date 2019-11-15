import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/NcaaList")

public class NcaaList extends HttpServlet {

    /* Console Page Displays all the Consoles and their Information in Game Speed */

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        String name = null;
        String CategoryName = request.getParameter("maker");

        HashMap<String, Ncaa> hm = new HashMap<String, Ncaa>();
        HashMap<String, Ncaa> allNcaa = new HashMap<String, Ncaa>();
        /* Checks the Tablets type whether it is microsft or sony or nintendo */

        try {
            allNcaa = MySqlDataStoreUtilities.getNcaa();
            System.out.println("allNfl" + allNcaa);
        } catch (Exception e) {
            System.out.println(e);
        }

        if (CategoryName == null) {
            hm.putAll(allNcaa);
            name = "";
        }

        Utilities utility = new Utilities(request, pw);
        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");
        pw.print("<div id='content' class='two-column'><div class='post'><h2 class='title meta'>");
        pw.print("<a style='font-size: 24px;'>NCAA</a>");
        pw.print("</h2><div class='entry'><table id='bestseller'>");

        for (Map.Entry<String, Ncaa> entry : hm.entrySet()) {
            Ncaa ncaa = entry.getValue();

            String date = ncaa.getMatchDate();

            String time = date.substring(11, 16);

            String result = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"))
                    .format(DateTimeFormatter.ofPattern("hh:mm a"));

            pw.print("<tr>");
            pw.print("<td width='15%'>");
            pw.print("<h5>" + date.substring(0, 10) + "</h5>");
            pw.print("<h5>" + result + "</h5>");
            pw.print("</td>");
            pw.print("<td><div id='shop_item'>");
            pw.print("<h3>" + ncaa.getMatchName() + "</h3>");
            pw.print("<h5>" + ncaa.getMatchStadium() + ", " + ncaa.getMatchCity() + ", " + ncaa.getMatchState() + ", US"
                    + "</h5>");

            pw.print("</ul></div></td>");
            pw.print("<td><h5>From<br>" + ncaa.getMinPrice() + "</h5></td>");

            pw.print("<td style='padding:15px;><form method='get' action='NcaaTicketList'>"
                    + "<input type='hidden' name='type' value='Ncaa'>" + "<input type='hidden' name='ncaaid' value='"
                    + ncaa.getMatchId() + "'>" + "<input type='hidden' name='date' value='" + date.substring(0, 10)
                    + "'>" + "<input type='hidden' name='time' value='" + result + "'>"
                    + "<input type='hidden' name='matchname' value='" + ncaa.getMatchName() + "'>"
                    + "<input type='hidden' name='matchstadium' value='" + ncaa.getMatchStadium() + "'>"
                    + "<input type='hidden' name='matchcity' value='" + ncaa.getMatchCity() + "'>"
                    + "<input type='hidden' name='matchstate' value='" + ncaa.getMatchState() + "'>"
                    + "<input type='hidden' name='matchcountry' value='US'>"
                    + "<input type='submit' class='btnbuy' value='Buy Now'></form></td>");

            pw.print("</tr>");
        }
        pw.print("</table></div></div></div><div class='clear'></div>");

        utility.printHtml("Footer.html");

    }
}