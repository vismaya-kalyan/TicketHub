import java.io.*;

import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import java.util.*;
import java.text.*;

import java.sql.*;

import java.io.IOException;
import java.io.*;



public class AjaxUtility {
	StringBuffer sb = new StringBuffer();
	boolean namesAdded = false;
	static Connection conn = null;
    static String message;
	public static String getConnection()
	{

		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tickethub","root","root");							
			message="Successfull";
			// System.out.println("Connecting to databse from AJAX + " + message);
			return message;
		}
		catch(SQLException e)
		{
			 message="unsuccessful";
			//  System.out.println("Connecting to databse from AJAX + " + message);
		     return message;
		}
		catch(Exception e)
		{
			 message="unsuccessful";
			//  System.out.println("Connecting to databse from AJAX + " + message);
		     return message;
		}

		
	}
	
	public  StringBuffer readdata(String searchId)
	{	
		HashMap<String,String> data;
		
		data=getData();
		System.out.println("Printing from read data ajax= "+data);
 	    Iterator it = data.entrySet().iterator();	
        while (it.hasNext()) 
	    {
                    Map.Entry pi = (Map.Entry)it.next();
			if(pi!=null)
			{
				// Matches p=(Matches)pi.getValue();  
				String p = (String) pi.getValue();
                if (p.toLowerCase().startsWith(searchId))
                {
                        sb.append("<product>");
                        sb.append("<id>" + p + "</id>");
                        sb.append("<productName>" + p + "</productName>");
                        sb.append("</product>");
                }
			}
       }
	  
	   return sb;
	}
	
	public static HashMap<String,String> getData()
	{
		HashMap<String,String> hm=new HashMap<String,String>();
		// ArrayList<String> arr = new ArrayList<String>();
		try
		{
			getConnection();
			
		    String selectproduct="select distinct(teamOne) from matchlist";
		    PreparedStatement pst = conn.prepareStatement(selectproduct);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{	
				// Matches p = new Matches(rs.getString("matchCategory"),rs.getString("matchName"),rs.getString("matchStadium"),rs.getString("matchCity"),rs.getString("matchState"),rs.getString("teamOne"),rs.getString("teamTwo"),rs.getString("matchDate"),rs.getDouble("minPrice"),rs.getDouble("maxPrice"));
				// hm.put(rs.getString("matchId"), p);
				hm.put(rs.getString("teamOne"),rs.getString("teamOne"));
				System.out.println("hm from ajax = "+ hm);
				// arr.add(rs.getString("teamOne"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return hm;			
	}
	// public static void storeData(HashMap<String,Product> productdata)
	// {
	// 	try
	// 	{
		
	// 		getConnection();
				
	// 		String insertIntoProductQuery = "INSERT INTO product(productId,productName,image,retailer,productCondition,productPrice,productType,discount) "
	// 		+ "VALUES (?,?,?,?,?,?,?,?);";	
	// 		for(Map.Entry<String, Product> entry : productdata.entrySet())
	// 		{	

	// 			PreparedStatement pst = conn.prepareStatement(insertIntoProductQuery);
	// 			//set the parameter for each column and execute the prepared statement
	// 			pst.setString(1,entry.getValue().getId());
	// 			pst.setString(2,entry.getValue().getName());
	// 			pst.setString(3,entry.getValue().getImage());
	// 			pst.setString(4,entry.getValue().getRetailer());
	// 			pst.setString(5,entry.getValue().getCondition());
	// 			pst.setDouble(6,entry.getValue().getPrice());
	// 			pst.setString(7,entry.getValue().getType());
	// 			pst.setDouble(8,entry.getValue().getDiscount());
	// 			pst.execute();
	// 		}
	// 	}
	// 	catch(Exception e)
	// 	{	
	
	// 	}		
	// }

}
