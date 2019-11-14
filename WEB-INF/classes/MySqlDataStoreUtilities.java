import java.sql.*;
import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class MySqlDataStoreUtilities {
    static Connection conn = null;

    public static void getConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tickethub", "root", "root");
        } catch (Exception e) {

        }
    }

    public static void deleteOrder(int orderId, String orderName) {
        try {

            getConnection();
            String deleteOrderQuery = "Delete from customerorders where OrderId=? and orderName=?";
            PreparedStatement pst = conn.prepareStatement(deleteOrderQuery);
            pst.setInt(1, orderId);
            pst.setString(2, orderName);
            pst.executeUpdate();
        } catch (Exception e) {

        }
    }

    public static void updateOrder(int orderId, String customerName, String orderName, double orderPrice,
            String userAddress, String creditCardNo) {
        try {

            // String updateProductQurey = "UPDATE Productdetails SET
            // productName=?,productPrice=?,productImage=?,productManufacturer=?,productCondition=?,productDiscount=?
            // where Id =?;";

            getConnection();
            String updateOrderQuery = "Update customerorders set userAddress=?, creditCardNo=? where OrderId=? and userName=? and orderName=?;";
            PreparedStatement pst = conn.prepareStatement(updateOrderQuery);
            pst.setString(1, userAddress);
            pst.setString(2, creditCardNo);
            pst.setInt(3, orderId);
            pst.setString(4, customerName);
            pst.setString(5, orderName);
            pst.executeUpdate();

        } catch (Exception e) {
            System.out.println("Mysql data store failed update " + e);
        }
    }

    public static void insertOrder(int orderId, String userName, String orderName, double orderPrice,
            String userAddress, String creditCardNo) {
        try {

            // Date current_date = new Date();

            // SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

            getConnection();
            String insertIntoCustomerOrderQuery = "INSERT INTO customerOrders(OrderId,UserName,OrderName,OrderPrice,userAddress,creditCardNo) "
                    + "VALUES (?,?,?,?,?,?);";

            PreparedStatement pst = conn.prepareStatement(insertIntoCustomerOrderQuery);
            // set the parameter for each column and execute the prepared statement
            pst.setInt(1, orderId);
            pst.setString(2, userName);
            pst.setString(3, orderName);
            pst.setDouble(4, orderPrice);
            pst.setString(5, userAddress);
            pst.setString(6, creditCardNo);
            // pst.setString(7, SimpleDateFormat.format(current_date.getTime()));
            pst.execute();
        } catch (Exception e) {

            System.out.println("Ecxeption from storing the order = "+e);

        }
    }

    public static HashMap<Integer, ArrayList<OrderPayment>> selectOrder() {

        HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();

        try {

            getConnection();
            // select the table
            String selectOrderQuery = "select * from customerorders";
            PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
            ResultSet rs = pst.executeQuery();
            ArrayList<OrderPayment> orderList = new ArrayList<OrderPayment>();
            while (rs.next()) {
                if (!orderPayments.containsKey(rs.getInt("OrderId"))) {
                    ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
                    orderPayments.put(rs.getInt("orderId"), arr);
                }
                ArrayList<OrderPayment> listOrderPayment = orderPayments.get(rs.getInt("OrderId"));
                System.out.println("data is" + rs.getInt("OrderId") + orderPayments.get(rs.getInt("OrderId")));

                // add to orderpayment hashmap
                OrderPayment order = new OrderPayment(rs.getInt("OrderId"), rs.getString("userName"),
                        rs.getString("orderName"), rs.getDouble("orderPrice"), rs.getString("userAddress"),
                        rs.getString("creditCardNo"));
                listOrderPayment.add(order);

            }

        } catch (Exception e) {

        }
        return orderPayments;
    }

    public static void insertUser(String username, String password, String repassword, String usertype) {
        try {

            getConnection();
            String insertIntoCustomerRegisterQuery = "INSERT INTO Registration(username,password,repassword,usertype) "
                    + "VALUES (?,?,?,?);";

            PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, repassword);
            pst.setString(4, usertype);
            pst.execute();
        } catch (Exception e) {

        }
    }

    public static HashMap<String, User> selectUser() {
        HashMap<String, User> hm = new HashMap<String, User>();
        try {
            getConnection();
            Statement stmt = conn.createStatement();
            String selectCustomerQuery = "select * from  Registration";
            ResultSet rs = stmt.executeQuery(selectCustomerQuery);
            while (rs.next()) {
                User user = new User(rs.getString("username"), rs.getString("password"), rs.getString("usertype"));
                hm.put(rs.getString("username"), user);
            }
        } catch (Exception e) {
        }
        return hm;
    }

    public static HashMap<String, Nfl> getNfls() {
        HashMap<String, Nfl> hm = new HashMap<String, Nfl>();
        try {
            getConnection();

            String selectNfl = "select * from  matchlist where matchCategory=?";
            PreparedStatement pst = conn.prepareStatement(selectNfl);
            pst.setString(1, "NFL");
            ResultSet rs = pst.executeQuery();
            // System.out.println("rs" + rs);
            while (rs.next()) {
                Nfl nfl = new Nfl(rs.getString("matchCategory"), rs.getString("matchName"),
                        rs.getString("matchStadium"), rs.getString("matchCity"), rs.getString("matchState"),
                        rs.getString("teamOne"), rs.getString("teamTwo"), rs.getString("matchDate"),
                        rs.getDouble("minPrice"), rs.getDouble("maxPrice"));
                hm.put(rs.getString("matchId"), nfl);
                nfl.setMatchId(rs.getInt("matchId"));

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return hm;
    }

    public static HashMap<String, Nba> getNbas() {
        HashMap<String, Nba> hm = new HashMap<String, Nba>();
        try {
            getConnection();

            String selectNfl = "select * from  matchlist where matchCategory=?";
            PreparedStatement pst = conn.prepareStatement(selectNfl);
            pst.setString(1, "NBA");
            ResultSet rs = pst.executeQuery();
            // System.out.println("rs" + rs);
            while (rs.next()) {
                Nba nba = new Nba(rs.getString("matchCategory"), rs.getString("matchName"),
                        rs.getString("matchStadium"), rs.getString("matchCity"), rs.getString("matchState"),
                        rs.getString("teamOne"), rs.getString("teamTwo"), rs.getString("matchDate"),
                        rs.getDouble("minPrice"), rs.getDouble("maxPrice"));
                hm.put(rs.getString("matchId"), nba);
                nba.setMatchId(rs.getInt("matchId"));

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return hm;
    }

    // Getting NCAA events
    public static HashMap<String, Ncaa> getNcaa() {
        HashMap<String, Ncaa> hm = new HashMap<String, Ncaa>();
        try {
            getConnection();

            String selectNfl = "select * from  matchlist where matchCategory=?";
            PreparedStatement pst = conn.prepareStatement(selectNfl);
            pst.setString(1, "NCAA");
            ResultSet rs = pst.executeQuery();
            // System.out.println("rs" + rs);
            while (rs.next()) {
                Ncaa nba = new Ncaa(rs.getString("matchCategory"), rs.getString("matchName"),
                        rs.getString("matchStadium"), rs.getString("matchCity"), rs.getString("matchState"),
                        rs.getString("teamOne"), rs.getString("teamTwo"), rs.getString("matchDate"),
                        rs.getDouble("minPrice"), rs.getDouble("maxPrice"));
                hm.put(rs.getString("matchId"), nba);
                nba.setMatchId(rs.getInt("matchId"));

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return hm;
    }

    // Getting Nhl events
    public static HashMap<String, Nhl> getNhl() {
        HashMap<String, Nhl> hm = new HashMap<String, Nhl>();
        try {
            getConnection();

            String selectNfl = "select * from  matchlist where matchCategory=?";
            PreparedStatement pst = conn.prepareStatement(selectNfl);
            pst.setString(1, "NHL");
            ResultSet rs = pst.executeQuery();
            // System.out.println("rs" + rs);
            while (rs.next()) {
                Nhl nba = new Nhl(rs.getString("matchCategory"), rs.getString("matchName"),
                        rs.getString("matchStadium"), rs.getString("matchCity"), rs.getString("matchState"),
                        rs.getString("teamOne"), rs.getString("teamTwo"), rs.getString("matchDate"),
                        rs.getDouble("minPrice"), rs.getDouble("maxPrice"));
                hm.put(rs.getString("matchId"), nba);
                nba.setMatchId(rs.getInt("matchId"));

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return hm;
    }

    public static HashMap<String, Listings> getNflTickets(int id) {
        HashMap<String, Listings> hm = new HashMap<String, Listings>();
        try {
            getConnection();

            String selectNfl = "select * from  listings where matchIdRef=?";
            PreparedStatement pst = conn.prepareStatement(selectNfl);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            System.out.println("rs" + rs);
            while (rs.next()) {
                Listings nfl = new Listings(rs.getInt("matchIdRef"), rs.getDouble("currentPrice"),
                        rs.getString("deliveryMethodList"), rs.getInt("quantity"), rs.getString("rowInfo"),
                        rs.getString("seatNumbers"), rs.getString("sectionName"), rs.getString("zoneName"),
                        rs.getString("sellerSectionName"));
                hm.put(rs.getString("listingId"), nfl);
                nfl.setListingId(rs.getInt("listingId"));

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return hm;
    }

    public static HashMap<String, Listings> getNbaTickets(int id) {
        HashMap<String, Listings> hm = new HashMap<String, Listings>();
        try {
            getConnection();

            String selectNfl = "select * from  listings where matchIdRef=?";
            PreparedStatement pst = conn.prepareStatement(selectNfl);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            System.out.println("rs" + rs);
            while (rs.next()) {
                Listings nfl = new Listings(rs.getInt("matchIdRef"), rs.getDouble("currentPrice"),
                        rs.getString("deliveryMethodList"), rs.getInt("quantity"), rs.getString("rowInfo"),
                        rs.getString("seatNumbers"), rs.getString("sectionName"), rs.getString("zoneName"),
                        rs.getString("sellerSectionName"));
                hm.put(rs.getString("listingId"), nfl);
                nfl.setListingId(rs.getInt("listingId"));

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return hm;
    }

    // Getting Nhl tickets
    public static HashMap<String, Listings> getNhlTickets(int id) {
        HashMap<String, Listings> hm = new HashMap<String, Listings>();
        try {
            getConnection();

            String selectNfl = "select * from  listings where matchIdRef=?";
            PreparedStatement pst = conn.prepareStatement(selectNfl);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            System.out.println("rs" + rs);
            while (rs.next()) {
                Listings nfl = new Listings(rs.getInt("matchIdRef"), rs.getDouble("currentPrice"),
                        rs.getString("deliveryMethodList"), rs.getInt("quantity"), rs.getString("rowInfo"),
                        rs.getString("seatNumbers"), rs.getString("sectionName"), rs.getString("zoneName"),
                        rs.getString("sellerSectionName"));
                hm.put(rs.getString("listingId"), nfl);
                nfl.setListingId(rs.getInt("listingId"));

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return hm;
    }

    public static String addMatch(int matchId, String matchName, String matchCategory, String matchStadium,
            String teamOne, String teamTwo, String matchCity, String matchState, String matchDate, Double maxPrice,
            Double minPrice) {

        String msg = "Product is added successfully";
        try {

            getConnection();
            String addProductQurey = "INSERT INTO  matchlist(matchId, matchCategory, matchName, matchStadium, matchCity,matchState,teamOne,teamTwo,matchDate, minPrice, maxPrice)"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?);";

            PreparedStatement pst = conn.prepareStatement(addProductQurey);
            pst.setInt(1, matchId);
            pst.setString(2, matchCategory);
            pst.setString(3, matchName);
            pst.setString(4, matchStadium);
            pst.setString(5, matchCity);
            pst.setString(6, matchState);
            pst.setString(7, teamOne);
            pst.setString(8, teamTwo);
            pst.setString(9, matchDate);
            pst.setDouble(10, minPrice);
            pst.setDouble(11, maxPrice);

            pst.executeUpdate();
        } catch (Exception e) {
            msg = "Erro while adding the product";
            e.printStackTrace();

        }
        return msg;
    }

    public static String addListing(int matchIdRef, Double currentPrice, String deliveryMethod, int listingId,
            int quanity, String rowInfo, String seatNumber, String sectionName, String zoneName,
            String sellerSectionName) {
        String msg = "Product is added successfully";
        try {

            getConnection();
            String addProductQurey = "INSERT INTO  listings(matchIdRef, currentPrice, deliveryMethodList, listingId, quantity,rowInfo,seatNumbers,sectionName,zoneName,sellerSectionName)"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?);";

            PreparedStatement pst = conn.prepareStatement(addProductQurey);
            pst.setInt(1, matchIdRef);
            pst.setDouble(2, currentPrice);
            pst.setString(3, deliveryMethod);
            pst.setInt(4, listingId);
            pst.setInt(5, quanity);
            pst.setString(6, rowInfo);
            pst.setString(7, seatNumber);
            pst.setString(8, sectionName);
            pst.setString(9, zoneName);
            pst.setString(10, sellerSectionName);

            pst.executeUpdate();
        } catch (Exception e) {
            msg = "Erro while adding the product";
            e.printStackTrace();

        }

        return msg;
    }

}