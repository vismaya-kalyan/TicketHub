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
import java.util.Date;
import java.time.*;
@WebServlet("/NflList")

public class NflList extends HttpServlet {

    /* Console Page Displays all the Consoles and their Information in Game Speed */

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        String name = null;
        String CategoryName = request.getParameter("maker");

        HashMap<String, Nfl> hm = new HashMap<String, Nfl>();
        HashMap<String, Nfl> allNfl = new HashMap<String, Nfl>();
        /* Checks the Tablets type whether it is microsft or sony or nintendo */


        try {
            allNfl = MySqlDataStoreUtilities.getNfls();
            System.out.println("allNfl" + allNfl);
        } catch (Exception e) {
            System.out.println(e);
        }

        if (CategoryName == null) {
            hm.putAll(allNfl);
            name = "";
        }

        Utilities utility = new Utilities(request, pw);
        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");
        pw.print("<div id='content' class='two-column'><div class='post'><h2 class='title meta'>");
        pw.print("<a style='font-size: 24px;'>NFL</a>");
        pw.print("</h2><div class='entry'><table id='bestseller'>");

        for (Map.Entry<String, Nfl> entry : hm.entrySet()) {
            Nfl nfl = entry.getValue();
            //Adding code for date
            
            String date = nfl.getMatchDate();
                
            String time = date.substring(11,16);

            String result = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm")).format(DateTimeFormatter.ofPattern("hh:mm a"));
            System.out.println("Date = "+ date.substring(0,10)+" Time = "+ result);

            
            // try{
            // SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", java.util.Locale.ENGLISH);
            // Date myDate = sdf.parse(date.substring(0,10));
            // sdf.applyPattern("EEE, d MMM yyyy");
            // String sMyDate = sdf.format(myDate);
            // System.out.print("My day = "+sMyDate);
            // }
            // catch(Exception e){
            //     System.out.println(e);
            // }
            pw.print("<tr>");
            pw.print("<td>SUN </td>");
            pw.print("<td><div id='shop_item'>");
            pw.print("<h3>" + nfl.getMatchName() + "</h3>");
            pw.print("<h5>" + nfl.getMatchStadium() + ", " + nfl.getMatchCity() + ", " + nfl.getMatchState() + ", US"
                    + "</h5>");

            pw.print("</ul></div></td>");
            pw.print("<td><h5>From<br>" + nfl.getMinPrice() + "</h5></td>");

            pw.print("<td><form method='get' action='NflTicketList'>" + "<input type='hidden' name='name' value='"
                    + entry.getKey() + "'>" + "<input type='hidden' name='type' value='nlf'>"
                    + "<input type='hidden' name='nflid' value='" + nfl.getMatchId() + "'>"
                    + "<input type='submit' class='btnbuy' value='Buy Now'></form></td>");

            pw.print("</tr>");
        }
        pw.print("</table></div></div></div><div class='clear'></div>");

        utility.printHtml("Footer.html");

    }
}