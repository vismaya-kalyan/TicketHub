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
import java.util.TreeMap;


@WebServlet("/SpecificCity")

public class SpecificCity extends HttpServlet {

    /* Console Page Displays all the Consoles and their Information in Game Speed */

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        String name = null;
        String matchcity = request.getParameter("matchcity");
        
        HashMap<Date, Matches> hm = new HashMap<Date, Matches>();
        HashMap<Date, Matches> allMatch = new HashMap<Date, Matches>();
        SimpleDateFormat formatter= new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        Date dateOne = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(dateOne));

        Utilities utility = new Utilities(request, pw);
        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");
        pw.print("<div id='content' class='two-column'><div class='post'><h2 class='title meta'>");
        pw.print("<a style='font-size: 24px;'>" + matchcity + "</a>");
        pw.print("</h2><h2 style='color:#337ab7'>NFL</h2><div class='entry'><table id='bestseller'>");

        try {
            allMatch = MySqlDataStoreUtilities.getCityMatches(matchcity,"NFL");
            hm.putAll(allMatch);
            System.out.println(hm);

        } catch (Exception e) {
            System.out.println(e);
        }

        Map<Date, Matches> sortedMap = new TreeMap<Date, Matches>(hm);
        for (Map.Entry<Date, Matches> entry : sortedMap.entrySet()) {
            if (entry.getKey().after(dateOne)) {
                Matches nfl = entry.getValue();
                // Adding code for date
    
                Date dateInString = nfl.getMatchDate();
    
                pw.print("<tr>");
                pw.print("<td width='15%'>");
                pw.print("<h5>" + dateInString + "</h5>");
                pw.print("</td>");
                pw.print("<td><div id='shop_item'>");
                pw.print("<h3>" + nfl.getMatchName() + "</h3>");
                pw.print("<h5>" + nfl.getMatchStadium() + ", " + nfl.getMatchCity() + ", " + nfl.getMatchState() + ", US"
                        + "</h5>");
    
                pw.print("</ul></div></td>");
                pw.print("<td><h5>From<br>" + nfl.getMinPrice() + "</h5></td>");
    
                pw.print("<td style='padding:15px;'><form method='get' action='NflTicketList'>"
                        + "<input type='hidden' name='type' value='nlf'>" + "<input type='hidden' name='nflid' value='"
                        + nfl.getMatchId() + "'>" + "<input type='hidden' name='date' value='" + dateInString
                        + "'>" + "<input type='hidden' name='time' value='" + dateInString + "'>"
                        + "<input type='hidden' name='matchname' value='" + nfl.getMatchName() + "'>"
                        + "<input type='hidden' name='matchstadium' value='" + nfl.getMatchStadium() + "'>"
                        + "<input type='hidden' name='matchcity' value='" + nfl.getMatchCity() + "'>"
                        + "<input type='hidden' name='matchstate' value='" + nfl.getMatchState() + "'>"
                        + "<input type='hidden' name='matchcountry' value='US'>"
                        + "<input type='submit' class='btnbuy' value='Book tickets'></form></td>");
    
                pw.print("</tr>");
            }}
        pw.print("</table></div>");
        
        pw.print("<h2 style='color:#337ab7'>NBA</h2><div class='entry'><table id='bestseller'>");       

        try {
            allMatch = MySqlDataStoreUtilities.getCityMatches(matchcity,"NBA");
            hm.putAll(allMatch);
            System.out.println(hm);

        } catch (Exception e) {
            System.out.println(e);
        }
        Map<Date, Matches> sortedMap1 = new TreeMap<Date, Matches>(hm);
        for (Map.Entry<Date, Matches> entry : sortedMap1.entrySet()) {
            if (entry.getKey().after(dateOne)) {
                Matches nfl = entry.getValue();
                // Adding code for date
    
                Date dateInString = nfl.getMatchDate();
    
                pw.print("<tr>");
                pw.print("<td width='15%'>");
                pw.print("<h5>" + dateInString + "</h5>");
                pw.print("</td>");
                pw.print("<td><div id='shop_item'>");
                pw.print("<h3>" + nfl.getMatchName() + "</h3>");
                pw.print("<h5>" + nfl.getMatchStadium() + ", " + nfl.getMatchCity() + ", " + nfl.getMatchState() + ", US"
                        + "</h5>");
    
                pw.print("</ul></div></td>");
                pw.print("<td><h5>From<br>" + nfl.getMinPrice() + "</h5></td>");
    
                pw.print("<td style='padding:15px;'><form method='get' action='NflTicketList'>"
                        + "<input type='hidden' name='type' value='nlf'>" + "<input type='hidden' name='nflid' value='"
                        + nfl.getMatchId() + "'>" + "<input type='hidden' name='date' value='" + dateInString
                        + "'>" + "<input type='hidden' name='time' value='" + dateInString + "'>"
                        + "<input type='hidden' name='matchname' value='" + nfl.getMatchName() + "'>"
                        + "<input type='hidden' name='matchstadium' value='" + nfl.getMatchStadium() + "'>"
                        + "<input type='hidden' name='matchcity' value='" + nfl.getMatchCity() + "'>"
                        + "<input type='hidden' name='matchstate' value='" + nfl.getMatchState() + "'>"
                        + "<input type='hidden' name='matchcountry' value='US'>"
                        + "<input type='submit' class='btnbuy' value='Book tickets'></form></td>");
    
                pw.print("</tr>");
            }}
        pw.print("</table></div>");
        
        pw.print("<h2 style='color:#337ab7'>NHL</h2><div class='entry'><table id='bestseller'>");      

        try {
            allMatch = MySqlDataStoreUtilities.getCityMatches(matchcity,"NHL");
            hm.putAll(allMatch);
            System.out.println(hm);

        } catch (Exception e) {
            System.out.println(e);
        }
        Map<Date, Matches> sortedMap2 = new TreeMap<Date, Matches>(hm);
        
        for (Map.Entry<Date, Matches> entry : sortedMap2.entrySet()) {
            if (entry.getKey().after(dateOne)) {
                Matches nfl = entry.getValue();
                // Adding code for date
    
                Date dateInString = nfl.getMatchDate();
    
                pw.print("<tr>");
                pw.print("<td width='15%'>");
                pw.print("<h5>" + dateInString + "</h5>");
                pw.print("</td>");
                pw.print("<td><div id='shop_item'>");
                pw.print("<h3>" + nfl.getMatchName() + "</h3>");
                pw.print("<h5>" + nfl.getMatchStadium() + ", " + nfl.getMatchCity() + ", " + nfl.getMatchState() + ", US"
                        + "</h5>");
    
                pw.print("</ul></div></td>");
                pw.print("<td><h5>From<br>" + nfl.getMinPrice() + "</h5></td>");
    
                pw.print("<td style='padding:15px;'><form method='get' action='NflTicketList'>"
                        + "<input type='hidden' name='type' value='nlf'>" + "<input type='hidden' name='nflid' value='"
                        + nfl.getMatchId() + "'>" + "<input type='hidden' name='date' value='" + dateInString
                        + "'>" + "<input type='hidden' name='time' value='" + dateInString + "'>"
                        + "<input type='hidden' name='matchname' value='" + nfl.getMatchName() + "'>"
                        + "<input type='hidden' name='matchstadium' value='" + nfl.getMatchStadium() + "'>"
                        + "<input type='hidden' name='matchcity' value='" + nfl.getMatchCity() + "'>"
                        + "<input type='hidden' name='matchstate' value='" + nfl.getMatchState() + "'>"
                        + "<input type='hidden' name='matchcountry' value='US'>"
                        + "<input type='submit' class='btnbuy' value='Book tickets'></form></td>");
    
                pw.print("</tr>");
            }}
        pw.print("</table></div>");
        
        pw.print("<h2 style='color:#337ab7'>NCAA</h2><div class='entry'><table id='bestseller'>");      

        try {
            allMatch = MySqlDataStoreUtilities.getCityMatches(matchcity,"NCAA");
            hm.putAll(allMatch);
            System.out.println(hm);

        } catch (Exception e) {
            System.out.println(e);
        }
        Map<Date, Matches> sortedMap3 = new TreeMap<Date, Matches>(hm);
        
        for (Map.Entry<Date, Matches> entry : sortedMap3.entrySet()) {
            if (entry.getKey().after(dateOne)) {
                Matches nfl = entry.getValue();
                // Adding code for date
    
                Date dateInString = nfl.getMatchDate();
    
                pw.print("<tr>");
                pw.print("<td width='15%'>");
                pw.print("<h5>" + dateInString + "</h5>");
                pw.print("</td>");
                pw.print("<td><div id='shop_item'>");
                pw.print("<h3>" + nfl.getMatchName() + "</h3>");
                pw.print("<h5>" + nfl.getMatchStadium() + ", " + nfl.getMatchCity() + ", " + nfl.getMatchState() + ", US"
                        + "</h5>");
    
                pw.print("</ul></div></td>");
                pw.print("<td><h5>From<br>" + nfl.getMinPrice() + "</h5></td>");
    
                pw.print("<td style='padding:15px;'><form method='get' action='NflTicketList'>"
                        + "<input type='hidden' name='type' value='nlf'>" + "<input type='hidden' name='nflid' value='"
                        + nfl.getMatchId() + "'>" + "<input type='hidden' name='date' value='" + dateInString
                        + "'>" + "<input type='hidden' name='time' value='" + dateInString + "'>"
                        + "<input type='hidden' name='matchname' value='" + nfl.getMatchName() + "'>"
                        + "<input type='hidden' name='matchstadium' value='" + nfl.getMatchStadium() + "'>"
                        + "<input type='hidden' name='matchcity' value='" + nfl.getMatchCity() + "'>"
                        + "<input type='hidden' name='matchstate' value='" + nfl.getMatchState() + "'>"
                        + "<input type='hidden' name='matchcountry' value='US'>"
                        + "<input type='submit' class='btnbuy' value='Book tickets'></form></td>");
    
                pw.print("</tr>");
            }}
        pw.print("</table></div></div></div><div class='clear'></div>");


        utility.printHtml("Footer.html");

    }
}