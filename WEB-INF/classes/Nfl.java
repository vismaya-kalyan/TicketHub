import java.util.*;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Nfl")

public class Nfl extends HttpServlet {
    private String id;
    private String name;
    private double price;
    private String date;
    private String location;
    private String team1;
    private String team2;
    private String seat;
    HashMap<String, String> accessories;

    public Nfl(String id, String name, double price, String date, String location, String team1, String team2,
            String seat) {
        this.name = name;
        this.price = price;
        this.date = date;
        this.location = location;
        this.team1 = team1;
        this.team2 = team2;
        this.seat = seat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public HashMap<String, String> getAccessories() {
        return accessories;
    }

    public void setAccessories(HashMap<String, String> accessories) {
        this.accessories = accessories;
    }

}