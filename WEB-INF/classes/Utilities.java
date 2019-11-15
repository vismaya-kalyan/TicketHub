import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@WebServlet("/Utilities")

/*
 * Utilities class contains class variables of type HttpServletRequest,
 * PrintWriter,String and HttpSession.
 * 
 * Utilities class has a constructor with HttpServletRequest, PrintWriter
 * variables.
 * 
 */

public class Utilities extends HttpServlet {
	HttpServletRequest req;
	PrintWriter pw;
	String url;
	HttpSession session;

	public Utilities(HttpServletRequest req, PrintWriter pw) {
		this.req = req;
		this.pw = pw;
		this.url = this.getFullURL();
		this.session = req.getSession(true);
	}

	/*
	 * Printhtml Function gets the html file name as function Argument, If the html
	 * file name is Header.html then It gets Username from session variables.
	 * Account ,Cart Information ang Logout Options are Displayed
	 */

	public void printHtml(String file) {
		String result = HtmlToString(file);
		// to print the right navigation in header of username cart and logout etc
		if (file == "Header.html") {
			result = result + "<div id='menu' style='float: right;'><ul>";
			if (session.getAttribute("username") != null) {
				String username = session.getAttribute("username").toString();
				username = Character.toUpperCase(username.charAt(0)) + username.substring(1);
				String userType = session.getAttribute("usertype").toString();
				switch (userType) {
				case "customer":
					result = result + "<li><a href='ViewOrder'><span class='glyphicon'>ViewOrder</span></a></li>"
							+ "<li><a><span class='glyphicon'>Hello, " + username + "</span></a></li>"
							+ "<li><a href='Account'><span class='glyphicon'>Account</span></a></li>"
							+ "<li><a href='Logout'><span class='glyphicon'>Logout</span></a></li>";
					break;
				case "manager":
					result = result
							+ "<li><a href='StoreManagerHome'><span class='glyphicon'>ViewProduct</span></a></li>"
							+ "<li><a><span class='glyphicon'>Hello, " + username + "</span></a></li>"
							+ "<li><a href='Logout'><span class='glyphicon'>Logout</span></a></li>";
					break;
				}
			} else {
				result = result + "<li><a href='ViewOrder'><span class='glyphicon'>View Order</span></a></li>"
						+ "<li><a href='Login'><span class='glyphicon'>Login</span></a></li>";
			}
			result = result + "<li><a href='Cart'><span class='glyphicon'>Cart(" + CartCount()
					+ ")</span></a></li></ul></div></div><div id='page'>" + "</nav>";
			pw.print(result);

		} else
			pw.print(result);
	}

	/* getFullURL Function - Reconstructs the URL user request */

	public String getFullURL() {
		String scheme = req.getScheme();
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
		String contextPath = req.getContextPath();
		StringBuffer url = new StringBuffer();
		url.append(scheme).append("://").append(serverName);

		if ((serverPort != 80) && (serverPort != 443)) {
			url.append(":").append(serverPort);
		}
		url.append(contextPath);
		url.append("/");
		return url.toString();
	}

	/*
	 * HtmlToString - Gets the Html file and Converts into String and returns the
	 * String.
	 */
	public String HtmlToString(String file) {
		String result = null;
		try {
			String webPage = url + file;
			URL url = new URL(webPage);
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			result = sb.toString();
		} catch (Exception e) {
		}
		return result;
	}

	/*
	 * logout Function removes the username , usertype attributes from the session
	 * variable
	 */

	public void logout() {
		session.removeAttribute("username");
		session.removeAttribute("usertype");
	}

	/* logout Function checks whether the user is loggedIn or Not */

	public boolean isLoggedin() {
		if (session.getAttribute("username") == null)
			return false;
		return true;
	}

	/* username Function returns the username from the session variable. */

	public String username() {
		if (session.getAttribute("username") != null)
			return session.getAttribute("username").toString();
		return null;
	}

	/* usertype Function returns the usertype from the session variable. */
	public String usertype() {
		if (session.getAttribute("usertype") != null)
			return session.getAttribute("usertype").toString();
		return null;
	}

	/*
	 * getUser Function checks the user is a customer or retailer or manager and
	 * returns the user class variable.
	 */
	public User getUser() {

		String usertype = usertype();
		HashMap<String, User> hm = new HashMap<String, User>();
		try {
			hm = MySqlDataStoreUtilities.selectUser();
		} catch (Exception e) {
		}
		User user = hm.get(username());
		return user;
	}

	/* getCustomerOrders Function gets the Orders for the user */
	public ArrayList<OrderItem> getCustomerOrders() {
		ArrayList<OrderItem> order = new ArrayList<OrderItem>();
		if (OrdersHashMap.orders.containsKey(username()))
			order = OrdersHashMap.orders.get(username());
		return order;
	}

	/* getOrdersPaymentSize Function gets the size of OrderPayment */
	public int getOrderPaymentSize() {
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
		String TOMCAT_HOME = System.getProperty("catalina.home");
		try {
			// FileInputStream fileInputStream = new FileInputStream(new
			// File(TOMCAT_HOME+"\\webapps\\assignment_1\\PaymentDetails.txt"));
			// ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			orderPayments = MySqlDataStoreUtilities.selectOrder();
		} catch (Exception e) {

		}
		int size = 0;
		for (Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet()) {
			size = size + 1;

		}
		return size;
	}

	/* CartCount Function gets the size of User Orders */
	public int CartCount() {
		if (isLoggedin())
			return getCustomerOrders().size();
		return 0;
	}

	/*
	 * StoreProduct Function stores the Purchased product in Orders HashMap
	 * according to the User Names.
	 */

	public void storeProduct(String matchName, String zoneName, double price, String row, String seat) {
		if (!OrdersHashMap.orders.containsKey(username())) {
			ArrayList<OrderItem> arr = new ArrayList<OrderItem>();
			OrdersHashMap.orders.put(username(), arr);
		}
		ArrayList<OrderItem> orderItems = OrdersHashMap.orders.get(username());
		OrderItem orderitem = new OrderItem(matchName, zoneName, price, row, seat);
		orderItems.add(orderitem);
		System.out.println("Printing from store product of the hashmap " + OrdersHashMap.orders);

		// if(type.equals("consoles")){
		// Console console;
		// console = SaxParserDataStore.consoles.get(name);
		// OrderItem orderitem = new OrderItem(console.getName(), console.getPrice(),
		// console.getImage(), console.getRetailer());
		// orderItems.add(orderitem);
		// }
		// if(type.equals("tvs")){
		// Tv tv;
		// tv = SaxParserDataStore.tvs.get(name);
		// OrderItem orderitem = new OrderItem(tv.getName(), tv.getPrice(),
		// tv.getImage(), tv.getRetailer());
		// orderItems.add(orderitem);
		// }
		// if(type.equals("soundSystems")){
		// SoundSystem soundSystem;
		// soundSystem = SaxParserDataStore.soundSystems.get(name);
		// OrderItem orderitem = new OrderItem(soundSystem.getName(),
		// soundSystem.getPrice(), soundSystem.getImage(), soundSystem.getRetailer());
		// orderItems.add(orderitem);
		// }
		// if(type.equals("phones")){
		// Phone phone;
		// phone = SaxParserDataStore.phones.get(name);
		// OrderItem orderitem = new OrderItem(phone.getName(),phone.getPrice(),
		// phone.getImage(), phone.getRetailer());
		// orderItems.add(orderitem);
		// }
		// if(type.equals("headphones")){
		// Headphone headphone;
		// headphone = SaxParserDataStore.headphones.get(name);
		// OrderItem orderitem = new OrderItem(headphone.getName(),headphone.getPrice(),
		// headphone.getImage(), headphone.getRetailer());
		// orderItems.add(orderitem);
		// }
		// if(type.equals("laptops")){
		// Laptop laptop;
		// laptop = SaxParserDataStore.laptops.get(name);
		// OrderItem orderitem = new OrderItem(laptop.getName(),laptop.getPrice(),
		// laptop.getImage(), laptop.getRetailer());
		// orderItems.add(orderitem);
		// }
		// if(type.equals("smartWatches")){
		// SmartWatch smartWatch;
		// smartWatch = SaxParserDataStore.smartWatches.get(name);
		// OrderItem orderitem = new
		// OrderItem(smartWatch.getName(),smartWatch.getPrice(), smartWatch.getImage(),
		// smartWatch.getRetailer());
		// orderItems.add(orderitem);
		// }
		// if(type.equals("vas")){
		// Va va = null;
		// va = SaxParserDataStore.vas.get(name);
		// OrderItem orderitem = new OrderItem(va.getName(), va.getPrice(),
		// va.getImage(), va.getRetailer());
		// orderItems.add(orderitem);
		// }
		// if(type.equals("fitnessWatches")){
		// FitnessWatch fitnessWatch = null;
		// fitnessWatch = SaxParserDataStore.fitnessWatches.get(name);
		// OrderItem orderitem = new OrderItem(fitnessWatch.getName(),
		// fitnessWatch.getPrice(), fitnessWatch.getImage(),
		// fitnessWatch.getRetailer());
		// orderItems.add(orderitem);
		// }
		// if(type.equals("wirelessplan")){
		// WirelessPlan wirelessPlan = null;
		// wirelessPlan = SaxParserDataStore.wirelessPlans.get(name);
		// OrderItem orderitem = new OrderItem(wirelessPlan.getName(),
		// wirelessPlan.getPrice(), wirelessPlan.getImage(),
		// wirelessPlan.getRetailer());
		// orderItems.add(orderitem);
		// }
		// if(type.equals("accessories")){
		// Accessory accessory = SaxParserDataStore.accessories.get(name);
		// OrderItem orderitem = new OrderItem(accessory.getName(),
		// accessory.getPrice(), accessory.getImage(), accessory.getRetailer());
		// orderItems.add(orderitem);
		// }

	}

	// store the payment details for orders
	public void storePayment(int orderId, String orderName, double orderPrice, String userAddress,
			String creditCardNo) {
		// HashMap<Integer, ArrayList<OrderPayment>> orderPayments= new HashMap<Integer,
		// ArrayList<OrderPayment>>();
		// String TOMCAT_HOME = System.getProperty("catalina.home");
		// // get the payment details file
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
		// get the payment details file
		try {
			orderPayments = MySqlDataStoreUtilities.selectOrder();
		} catch (Exception e) {

		}
		if (orderPayments == null) {
			orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
		}
		// if there exist order id already add it into same list for order id or create
		// a new record with order id

		if (!orderPayments.containsKey(orderId)) {
			ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
			orderPayments.put(orderId, arr);
		}
		ArrayList<OrderPayment> listOrderPayment = orderPayments.get(orderId);
		OrderPayment orderpayment = new OrderPayment(orderId, username(), orderName, orderPrice, userAddress,
				creditCardNo);
		listOrderPayment.add(orderpayment);

		// add order details into database
		try {
			MySqlDataStoreUtilities.insertOrder(orderId, username(), orderName, orderPrice, userAddress, creditCardNo);
		} catch (Exception e) {
			System.out.println("inside exception file not written properly");
		}
	}

}
