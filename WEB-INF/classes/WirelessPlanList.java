import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/WirelessPlanList")

public class WirelessPlanList extends HttpServlet {

    /* Console Page Displays all the Consoles and their Information in Game Speed */

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        String name = null;
        String CategoryName = request.getParameter("maker");

        /* Checks the Tablets type whether it is microsft or sony or nintendo */

        HashMap<String, WirelessPlan> hm = new HashMap<String, WirelessPlan>();
        if (CategoryName == null) {
            hm.putAll(SaxParserDataStore.wirelessplans);
            name = "";
        } else {
            if (CategoryName.equals("basic")) {
                for (Map.Entry<String, WirelessPlan> entry : SaxParserDataStore.wirelessplans.entrySet()) {
                    if (entry.getValue().getRetailer().equals("Basic")) {
                        hm.put(entry.getValue().getId(), entry.getValue());
                    }
                }
                name = "Basic";
            } else if (CategoryName.equals("premium")) {
                for (Map.Entry<String, WirelessPlan> entry : SaxParserDataStore.wirelessplans.entrySet()) {
                    if (entry.getValue().getRetailer().equals("Premium")) {
                        hm.put(entry.getValue().getId(), entry.getValue());
                    }
                }
                name = "Premium";
            } else if (CategoryName.equals("ultimate")) {
                for (Map.Entry<String, WirelessPlan> entry : SaxParserDataStore.wirelessplans.entrySet()) {
                    if (entry.getValue().getRetailer().equals("Ultimate")) {
                        hm.put(entry.getValue().getId(), entry.getValue());
                    }
                }
                name = "Ultimate";
            }
        }

        /*
         * Header, Left Navigation Bar are Printed.
         * 
         * All the Console and Console information are dispalyed in the Content Section
         * 
         * and then Footer is Printed
         */

        Utilities utility = new Utilities(request, pw);
        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");
        pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
        pw.print("<a style='font-size: 24px;'>" + name + " Wireless Plans's</a>");
        pw.print("</h2><div class='entry'><table id='bestseller'>");
        int i = 1;
        int size = hm.size();
        for (Map.Entry<String, WirelessPlan> entry : hm.entrySet()) {
            WirelessPlan wirelessplan = entry.getValue();
            if (i % 3 == 1)
                pw.print("<tr>");
            pw.print("<td><div id='shop_item'>");
            pw.print("<h3>" + wirelessplan.getName() + "</h3>");
            pw.print("<strong>$" + wirelessplan.getPrice() + "</strong><ul>");
            if (wirelessplan.getDiscount() > 0) {
                pw.print("<h3>Discount: " + wirelessplan.getDiscount() + "</h3>");
            }
            pw.print("<li id='item'><img src='images/wirelessplans/" + wirelessplan.getImage() + "' alt='' /></li>");

            pw.print("<li><form method='post' action='Cart'>" + "<input type='hidden' name='name' value='"
                    + entry.getKey() + "'>" + "<input type='hidden' name='type' value='wirelessplans'>"
                    + "<input type='hidden' name='maker' value='" + CategoryName + "'>"
                    + "<input type='hidden' name='access' value=''>"
                    + "<input type='submit' class='btnbuy' value='Buy Now'></form></li>");
            pw.print("<li><form method='post' action='WriteReview'>" + "<input type='hidden' name='name' value='"
                    + entry.getKey() + "'>" + "<input type='hidden' name='type' value='wirelessplans'>"
                    + "<input type='hidden' name='maker' value='" + CategoryName + "'>"
                    + "<input type='hidden' name='access' value=''>"
                    + "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
            pw.print("<li><form method='post' action='ViewReview'>" + "<input type='hidden' name='name' value='"
                    + entry.getKey() + "'>" + "<input type='hidden' name='type' value='wirelessplans'>"
                    + "<input type='hidden' name='maker' value='" + CategoryName + "'>"
                    + "<input type='hidden' name='access' value=''>"
                    + "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
            pw.print("</ul></div></td>");
            if (i % 3 == 0 || i == size)
                pw.print("</tr>");
            i++;
        }
        pw.print("</table></div></div></div><div class='clear'></div>");

        utility.printHtml("Footer.html");

    }
}