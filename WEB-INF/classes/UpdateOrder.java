import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/UpdateOrder")
public class UpdateOrder extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        Utilities utility = new Utilities(request, pw);

        int orderId = Integer.parseInt(request.getParameter("orderId"));
        String customerName = request.getParameter("customerName");
        String productName = request.getParameter("productName");
        double price = Double.parseDouble(request.getParameter("price"));
        String address = request.getParameter("address");
        String creditCard = request.getParameter("creditCard");

        utility.removeOldOrder(orderId, productName, customerName);

        // Create a new order id
        SimpleDateFormat df = new SimpleDateFormat("HHmmss");
        int newOrderId = Integer.parseInt(df.format(new Date()));
        utility.storeNewOrder(newOrderId, customerName, productName, price, address, creditCard);
        response.sendRedirect("SalesmanHome");
    }
}
