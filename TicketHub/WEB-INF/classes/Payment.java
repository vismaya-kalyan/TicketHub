import java.io.IOException;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.mail.Session;
import javax.mail.Transport;

import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Random;

@WebServlet("/Payment")

public class Payment extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		Utilities utility = new Utilities(request, pw);
		if (!utility.isLoggedin()) {
			HttpSession session = request.getSession(true);
			session.setAttribute("login_msg", "Please Login to Pay");
			response.sendRedirect("Login");
			return;
		}
		// get the payment details like credit card no address processed from cart
		// servlet

		String userAddress = request.getParameter("userAddress");
		String creditCardNo = request.getParameter("creditCardNo");
		String email = request.getParameter("email");

		System.out.print("the user address is" + userAddress);
		System.out.print(creditCardNo);
		if (!userAddress.isEmpty() && !creditCardNo.isEmpty()) {
			mail(email, utility);
			// Random rand = new Random();
			// int orderId = rand.nextInt(100);
			int orderId = utility.getOrderPaymentSize() + 1;

			// iterate through each order

			for (OrderItem oi : utility.getCustomerOrders()) {

				// set the parameter for each column and execute the prepared statement
				System.out.println("Saving these orders to the database " + orderId + oi.getMatchName() + oi.getPrice()
						+ userAddress + creditCardNo);
				utility.storePayment(orderId, oi.getMatchName(), oi.getPrice(), userAddress, creditCardNo);

			}

			// remove the order details from cart after processing

			OrdersHashMap.orders.remove(utility.username());
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Order</a>");
			pw.print("</h2><div class='entry'>");

			pw.print("<h2>Your Order");
			pw.print("&nbsp&nbsp");
			pw.print("is stored ");
			pw.print("<br>Your Order No is " + (orderId) + "</h2>");
			pw.print("<form action='WriteReview' method='post'><input type='submit' class='btnreview' value='WriteReview'>"+"</form>");
			pw.print("</div></div></div>");
			
			//Adding code to redirect to write review
			utility.printHtml("Footer.html");
		} else {
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Order</a>");
			pw.print("</h2><div class='entry'>");

			pw.print("<h4 style='color:red'>Please enter valid address and creditcard number</h4>");
			pw.print("</h2></div></div></div>");
			utility.printHtml("Footer.html");
		}
	}

	private static void mail(String email, Utilities utility) {
		String USER_NAME = "hubticket12"; // GMail user name (just the part before "@gmail.com")
		String PASSWORD = "tickethub123"; // GMail password
		String RECIPIENT = email;

		String from = USER_NAME;
		String pass = PASSWORD;
		String[] to = { RECIPIENT }; // list of recipient email addresses
		String subject = "Have Fun!";

		StringBuilder body = new StringBuilder("Enjoy your tickets to ");

		for (OrderItem oi : utility.getCustomerOrders()) {
			System.out.println("oi.getMatchName()" + oi.getMatchName());
			body.append(oi.getMatchName());
		}

		sendFromGMail(from, pass, to, subject, body.toString());

	}

	private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
		Properties props = System.getProperties();
		String host = "smtp.gmail.com";
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", pass);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(from));
			InternetAddress[] toAddress = new InternetAddress[to.length];

			// To get the array of addresses
			for (int i = 0; i < to.length; i++) {
				toAddress[i] = new InternetAddress(to[i]);
			}

			for (int i = 0; i < toAddress.length; i++) {
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}

			message.setSubject(subject);
			message.setText(body);
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (AddressException ae) {
			ae.printStackTrace();
		} catch (MessagingException me) {
			me.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);

	}
}
