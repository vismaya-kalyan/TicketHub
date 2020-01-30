import java.io.IOException;
import java.io.*;


/* 
	Review class contains class variables username,productname,reviewtext,reviewdate,reviewrating

	Review class has a constructor with Arguments username,productname,reviewtext,reviewdate,reviewrating
	  
	Review class contains getters and setters for username,productname,reviewtext,reviewdate,reviewrating
*/

public class Review implements Serializable{
	// private String productName;
	private String userName;
	// private String productType;
	// private String productMaker;
	private String reviewRating;
	private String reviewDate;
	private String reviewText;
	private String retailerpin;
	// private String price;
	private String retailercity;
	
	public Review (String userName,String reviewRating,String reviewDate,String reviewText,String retailerpin,String retailercity){
		
		this.userName=userName;
	 	this.reviewRating=reviewRating;
		this.reviewDate=reviewDate;
	 	this.reviewText=reviewText;
		this.retailerpin=retailerpin;
		this.retailercity= retailercity;
	}

	public Review(String productName, String retailerpin, String reviewRating, String reviewText) {

       this.retailerpin = retailerpin;
       this.reviewRating = reviewRating;
       this.reviewText = reviewText;
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getReviewRating() {
		return reviewRating;
	}

	public void setReviewRating(String reviewRating) {
		this.reviewRating = reviewRating;
	}

	public String getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	public String getRetailerpin() {
		return retailerpin;
	}

	public void setRetailerpin(String retailerpin) {
		this.retailerpin = retailerpin;
	}

	public String getRetailercity() {
		return retailercity;
	}

	public void setRetailercity(String retailercity) {
		this.retailercity = retailercity;
	}



}
