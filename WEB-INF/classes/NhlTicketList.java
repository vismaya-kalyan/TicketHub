import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/NhlTicketList")

public class NhlTicketList extends HttpServlet {

    /* Console Page Displays all the Consoles and their Information in Game Speed */

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        String name = null;
        String CategoryName = request.getParameter("type");
        int id = Integer.parseInt(request.getParameter("nhlid"));
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        String matchname = request.getParameter("matchname");
        String matchstadium = request.getParameter("matchstadium");
        String matchcity = request.getParameter("matchcity");
        String matchstate = request.getParameter("matchstate");
        String matchcountry = request.getParameter("matchcountry");
        String del;

        HashMap<String, Listings> hm = new HashMap<String, Listings>();
        HashMap<String, Listings> allNflTicketList = new HashMap<String, Listings>();
        /* Checks the Tablets type whether it is microsft or sony or nintendo */

        try {
            allNflTicketList = MySqlDataStoreUtilities.getNflTickets(id);
            hm.putAll(allNflTicketList);
        } catch (Exception e) {
            System.out.println(e);
        }

        if (CategoryName == null) {
            hm.putAll(allNflTicketList);
            name = "";
        }

        Utilities utility = new Utilities(request, pw);
        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");
        pw.print("<div id='content' class='two-column'><div class='post'><h2 class='title meta'>");
        pw.print("<a style='font-size: 24px;'>NHL Listings</a>");
        pw.print("</h2><div class='entry'><table id='bestseller'>");

        for (Map.Entry<String, Listings> entry : hm.entrySet()) {
            Listings nfl = entry.getValue();
            del = nfl.getDeliveryMethodList();
            del = del.replace("['", "");
            del = del.replace("']", "");
            pw.print("<tr>");
            pw.print("<td><div id='shop_item'>");
            pw.print("<h3>" + nfl.getSectionName() + "<h5>Row " + nfl.getRowInfo() + "</h5><h5>Seat "
                    + nfl.getSeatNumber() + "</h5></h3>");
            pw.print("<h5>" + nfl.getZoneName() + " </h5><h5>" + del + "</h5>");

            pw.print("</ul></div></td>");

            pw.print("<td><h5>Price<br>" + nfl.getCurrentPrice() + "</h5></td>");

            pw.print("</tr>");
        }
        pw.print("</table></div></div></div><div class='clear'></div>");

        utility.printHtml("Footer.html");

    }
}