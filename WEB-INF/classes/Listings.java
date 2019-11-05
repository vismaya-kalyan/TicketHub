import java.util.*;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Listings")

public class Listings extends HttpServlet {

    private int matchIdRef;
    private double currentPrice;
    private String deliveryMethodList;
    private int listingId;
    private int quantity;
    private String rowInfo;
    private String seatNumber;
    private String sectionName;
    private String zoneName;
    private String sellerSectionName;

    public Listings(int matchIdRef, double currentPrice, String deliveryMethodList, int quantity, String rowInfo,
            String seatNumber, String sectionName, String zoneName, String sellerSectionName) {
        this.matchIdRef = matchIdRef;
        this.currentPrice = currentPrice;
        this.deliveryMethodList = deliveryMethodList;
        // this.listingId = listingId;
        this.quantity = quantity;
        this.rowInfo = rowInfo;
        this.seatNumber = seatNumber;
        this.sectionName = sectionName;
        this.zoneName = zoneName;
        this.sellerSectionName = sellerSectionName;

    }

    public int getMatchIdRef() {
        return matchIdRef;
    }

    public void setMatchIdRef(int matchIdRef) {
        this.matchIdRef = matchIdRef;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getDeliveryMethodList() {
        return deliveryMethodList;
    }

    public void setDeliveryMethodList(String deliveryMethodList) {
        this.deliveryMethodList = deliveryMethodList;
    }

    public int getListingId() {
        return listingId;
    }

    public void setListingId(int listingId) {
        this.listingId = listingId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getRowInfo() {
        return rowInfo;
    }

    public void setRowInfo(String rowInfo) {
        this.rowInfo = rowInfo;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getSellerSectionName() {
        return sellerSectionName;
    }

    public void setSellerSectionName(String sellerSectionName) {
        this.sellerSectionName = sellerSectionName;
    }

}