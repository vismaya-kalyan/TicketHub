import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@WebServlet("/StoreManagerHome")
public class StoreManagerHome extends HttpServlet {

    private String error_msg;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter pw = response.getWriter();

        displayStoreManagerHome(request, response, pw, "");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request, pw);

    }

    private void displayStoreManagerHome(HttpServletRequest request, HttpServletResponse response, PrintWriter pw,
            String flag) {

        Utilities utility = new Utilities(request, pw);
        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");

        pw.print("<div id='content'>");
        pw.print("<div class='post'>");
        pw.print("<h3 class='title'>");
        pw.print("Create New Match");
        pw.print("</h3>");
        pw.print("<div class='entry'>");

        if (flag.equals("newProduct"))
            pw.print("<h4 style='color:red'>" + error_msg + "</h4>");
        //////////////////////////////////////
        pw.print("<h2>Add New Match</h2><form action='ProductCrud' method='get'>");
        pw.print("<table class='gridtable'");
        pw.print("<input type='hidden' name='category' value='match'>");
        pw.print("<tr><td>Match ID:</td>");

        pw.print("<td><input type='text' name='matchId' value=''></td>");
        pw.print("</tr>");

        pw.print(
                "<tr><td>Match Category</td><td><input type='text' name='matchCategory' value='' class='input' required></td></tr>");

        pw.print(
                "<tr><td>Match Name</td><td><input type='text' name='matchName' value='' class='input' required></td></tr>");

        pw.print(
                "<tr><td>Match Stadium</td><td><input type='text' name='matchStadium' value='' class='input' required></td></tr>");

        pw.print(
                "<tr><td>Match City</td><td><input type='text' name='matchCity' value='' class='input' required></td></tr>");

        pw.print(
                "<tr><td>Match State</td><td><input type='text' name='matchState' value='' class='input' required></td></tr>");

        pw.print(
                "<tr><td>Team One</td><td><input type='text' name='teamOne' value='' class='input' required></td></tr>");

        pw.print(
                "<tr><td>Team Two</td><td><input type='text' name='teamTwo' value='' class='input' required></td></tr>");
        pw.print(
                "<tr><td>Match Date</td><td><input type='datetime-local' name='matchDate' value='' class='input' required></td></tr>");

        pw.print(
                "<tr><td>Min Price</td><td><input type='text' name='minPrice' value='' class='input' required></td></tr>");

        pw.print(
                "<tr><td>Max Price</td><td><input type='text' name='maxPrice' value='' class='input' required></td></tr>");

        pw.print(
                "<tr><td></td><td><input type='submit' name='button' class='btnbuy' value='addMatch' style='float: left;height: 20px margin: 20px; margin-right: 10px;'></td></tr>");

        pw.print("</table></form></div></div>");

        pw.print("<h2> Add new listing for a match: </h2><form action='ProductCrud' method='get'>");
        pw.print("<table class='gridtable'");
        pw.print("<input type='hidden' name='category' value='listing'>");
        pw.print("<tr><td>Match ID Ref:</td>");

        pw.print("<td><input type='text' name='matchIdRef' value=''></td>");
        pw.print("</tr>");
        pw.print(
                "<tr><td>Match Category</td><td><input type='text' name='matchCategory' value='' class='input' required></td></tr>");
        pw.print(
                "<tr><td>Current Price</td><td><input type='text' name='currentPrice' value='' class='input' required></td></tr>");

        pw.print(
                "<tr><td>Delivery Method</td><td><input type='text' name='deliveryMethod' value='' class='input' required></td></tr>");

        pw.print(
                "<tr><td>Listing Id</td><td><input type='text' name='listingId' value='' class='input' required></td></tr>");

        pw.print(
                "<tr><td>Quanity</td><td><input type='hidden' name='quanity' value='1' class='input' required></td></tr>");

        pw.print(
                "<tr><td>Row Info</td><td><input type='text' name='rowInfo' value='' class='input' required></td></tr>");

        pw.print(
                "<tr><td>Seat Number</td><td><input type='text' name='seatNumber' value='' class='input' required></td></tr>");

        pw.print(
                "<tr><td>Section Name</td><td><input type='text' name='sectionName' value='' class='input' required></td></tr>");
        pw.print(
                "<tr><td>Zone Name</td><td><input type='text' name='zoneName' value='' class='input' required></td></tr>");

        pw.print(
                "<tr><td>Seller Section Name</td><td><input type='text' name='sellerSectionName' value='' class='input' required></td></tr>");

        pw.print(
                "<tr><td></td><td><input type='submit' name='button' class='btnbuy' value='addListing' style='float: left;height: 20px margin: 20px; margin-right: 10px;'></td></tr>");

        pw.print("</table></form></div></div>");

    }

}