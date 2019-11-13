import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ProductCrud")

public class ProductCrud extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request, pw);
        String action = request.getParameter("button");

        String msg = "good";
        int matchId = 0;
        String matchCategory = "", matchName = "", matchStadium = "", matchCity = "", matchState = "", teamOne = "",
                teamTwo = "", matchDate = "", category = "";
        Double minPrice = 0.0, maxPrice = 0.0;

        int matchIdRef = 0, quanity = 0, listingId = 0;
        Double currentPrice = 0.0;
        String deliveryMethod = "", rowInfo = "", seatNumber = "", sectionName = "", zoneName = "",
                sellerSectionName = "";

        HashMap<String, Nfl> allNfl = new HashMap<String, Nfl>();
        HashMap<String, Nba> allNba = new HashMap<String, Nba>();
        HashMap<String, Nhl> allNhl = new HashMap<String, Nhl>();
        HashMap<String, Ncaa> allNcaa = new HashMap<String, Ncaa>();

        if (action.equals("addMatch")) {
            category = request.getParameter("category");
            matchId = Integer.parseInt(request.getParameter("matchId"));
            matchCategory = request.getParameter("matchCategory");
            matchName = request.getParameter("matchName");
            matchStadium = request.getParameter("matchStadium");
            matchCity = request.getParameter("matchCity");
            matchState = request.getParameter("matchState");
            teamOne = request.getParameter("teamOne");
            teamTwo = request.getParameter("teamTwo");
            matchDate = request.getParameter("matchDate");
            minPrice = Double.parseDouble(request.getParameter("minPrice"));
            maxPrice = Double.parseDouble(request.getParameter("maxPrice"));

        } else if (action.equals("addListing")) {
            matchCategory = request.getParameter("matchCategory");
            matchIdRef = Integer.parseInt(request.getParameter("matchIdRef"));
            currentPrice = Double.parseDouble(request.getParameter("currentPrice"));
            deliveryMethod = request.getParameter("deliveryMethod");
            listingId = Integer.parseInt(request.getParameter("listingId"));
            quanity = Integer.parseInt(request.getParameter("quanity"));
            rowInfo = request.getParameter("rowInfo");
            seatNumber = request.getParameter("seatNumber");
            sectionName = request.getParameter("sectionName");
            zoneName = request.getParameter("zoneName");
            sellerSectionName = request.getParameter("sellerSectionName");

        }

        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");

        if (action.equals("addMatch")) {

            if (matchCategory.equals("NFL")) {
                allNfl = MySqlDataStoreUtilities.getNfls();
                if (allNfl.containsKey(matchId)) {
                    msg = "Product already available";
                }

            }
            if (matchCategory.equals("NCAA")) {
                allNba = MySqlDataStoreUtilities.getNbas();
                if (allNba.containsKey(matchId)) {
                    msg = "Product already available";
                }

            }
            if (matchCategory.equals("NBA")) {
                allNhl = MySqlDataStoreUtilities.getNhl();
                if (allNhl.containsKey(matchId)) {
                    msg = "Product already available";
                }

            }
            if (matchCategory.equals("NHL")) {
                allNcaa = MySqlDataStoreUtilities.getNcaa();
                if (allNcaa.containsKey(matchId)) {
                    msg = "Product already available";
                }

            }

            if (msg.equals("good")) {
                try {
                    msg = MySqlDataStoreUtilities.addMatch(matchId, matchName, matchCategory, matchStadium, teamOne,
                            teamTwo, matchCity, matchState, matchDate, maxPrice, minPrice);
                } catch (Exception e) {
                    msg = "Product cannot be inserted";
                }
                msg = "Product has been successfully added";
            }

        }

        if (action.equals("addListing")) {

            if (matchCategory.equals("NFL")) {
                allNfl = MySqlDataStoreUtilities.getNfls();
                if (allNfl.containsKey(matchIdRef)) {
                    msg = "good";
                }

            }
            if (matchCategory.equals("NCAA")) {
                allNba = MySqlDataStoreUtilities.getNbas();
                if (allNba.containsKey(matchIdRef)) {
                    msg = "good";
                }

            }
            if (matchCategory.equals("NBA")) {
                allNhl = MySqlDataStoreUtilities.getNhl();
                if (allNhl.containsKey(matchIdRef)) {
                    msg = "good";
                }

            }
            if (matchCategory.equals("NHL")) {
                allNcaa = MySqlDataStoreUtilities.getNcaa();
                if (allNcaa.containsKey(matchIdRef)) {
                    msg = "good";
                }

            }

            if (msg.equals("good")) {
                try {
                    msg = MySqlDataStoreUtilities.addListing(matchIdRef, currentPrice, deliveryMethod, listingId,
                            quanity, rowInfo, seatNumber, sectionName, zoneName, sellerSectionName);
                } catch (Exception e) {
                    msg = "Listing cannot be inserted";
                }
                msg = "Product has been successfully added";
            }

        }
    }
}