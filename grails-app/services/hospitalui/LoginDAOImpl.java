package hospitalui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LoginDAOImpl {
	
	Connection conn = null;
	
	public LoginDAOImpl(){
		try {
			//conn = DriverManager.getConnection("jdbc:mysql://eceweb.uwaterloo.ca:3306/ece356db_prli", "user_prli", "user_prli");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ece356_hospital", "root", "root");

		    // Do something with the Connection
		    System.out.println("Connection to MYSQL Database Sucessful!");
		    
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	
	public Person getPersonTypeFromCredentials(String loginID, String Password) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
		    stmt = (this.conn).createStatement();
		   
		   boolean credOK = false;
		   
		   rs = stmt.executeQuery("SELECT UserId, Password FROM Person");
		   rs = stmt.getResultSet();
		   while (rs.next()) {
			   if (rs.getString("UserId").toString().equals(loginID)) {
			   }
			   if (rs.getString("Password").toString().equals(Password)) {
				   
			   }
			   if (rs.getString("UserId").toString().equals(loginID) && rs.getString("Password").toString().equals(Password)) {
				   credOK = true;
			   }
		   }
		   
		   if (credOK == true) {
			   boolean matched = false;
			   
			   if (matched == false) {
				   rs = stmt.executeQuery("SELECT UserId FROM Doctor");
				   rs = stmt.getResultSet();  
				   while (rs.next()) {
					   String userID = rs.getString("UserId");
					   if (rs.getString("UserId").toString().equals(loginID)) {
						   System.out.println("Person is Doctor");
						   matched = true;
						   Doctor doc = new Doctor();
						   doc.setId(userID);
						   return doc;
					   }
				   }
			   }
			   if (matched == false) {
				   rs = stmt.executeQuery("SELECT UserId FROM Financial_Officer");
				   rs = stmt.getResultSet();
				   while (rs.next()) {
					   String userID = rs.getString("UserId");
					   if (rs.getString("UserId").toString().equals(loginID)) {
						   System.out.println("Person is FO");
						   matched = true;
						   Financial_officer fo = new Financial_officer();
						   fo.setId(userID);
						   return fo;
					   }
				   }
			   }
			   if (matched == false) {
				   rs = stmt.executeQuery("SELECT UserId FROM Patient");
				   rs = stmt.getResultSet();
				   while (rs.next()) {
					   String userID = rs.getString("UserId");
					   if (rs.getString("UserId").toString().equals(loginID)) {
						   System.out.println("Person is Patient");
						   matched = true;
						   Patient patient = new Patient();
						   patient.setId(userID);
						   return patient;
					   }
				   }
			   }
			   if (matched == false) {
				   rs = stmt.executeQuery("SELECT UserId FROM Staff");
				   rs = stmt.getResultSet();
				   while (rs.next()) {
					   String userID = rs.getString("UserId");
					   if (rs.getString("UserId").toString().equals(loginID)) {
						   System.out.println("Person is Staff");
						   matched = true;
						   Staff staff = new Staff();
						   staff.setId(userID);
						   return staff;
					   }
				   }
			   } 
		   }
		}
		catch (SQLException ex) {
		    // handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		finally {	
			if (rs != null) {
			    try {
			        rs.close();
			    } catch (SQLException sqlEx) {} // ignore
			
			    rs = null;
			}
			if (stmt != null) {
			    try {
			        stmt.close();
			    } catch (SQLException sqlEx) {} // ignore
			
			        stmt = null;
			    }
		}
		return null;
	}
	
	public ResultSet getAssignedPatientsList(String beginDate, String endDate, String doctorID) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
		    stmt = (this.conn).createStatement();
		    
		    // need to handle if user put 
		    if (beginDate != null && endDate != null) {
		    	rs = stmt.executeQuery("SELECT P.FirstName, P.LastName, V.Date_of_visit FROM Person P, Visitation_Record V WHERE UserId IN (SELECT UserId FROM Patient WHERE DoctorId = '" + doctorID + "') AND '" + beginDate +" 00:00:00' <= V.Date_of_visit AND V.Date_of_visit <= '"+ endDate +" 23:59:59' ORDER BY V.Date_of_visit DESC");
			    rs = stmt.getResultSet();
		    }
		    else {
		    	rs = stmt.executeQuery("SELECT P.FirstName, P.LastName, V.Date_of_visit FROM Person P, Visitation_Record V WHERE UserId IN (SELECT UserId FROM Patient WHERE DoctorId = '" + doctorID + "') ORDER BY V.Date_of_visit DESC");
			    rs = stmt.getResultSet();
		    }
		    
		    // Now do something with the ResultSet ....
		    int iter = 1;
		    while (rs.next()) {
		    	System.out.println("Patient [" + iter + "] : " + rs.getString("FirstName") + " " + rs.getString("LastName") + " Visit time is " + rs.getString("Date_of_visit"));
		    	iter++;
		    }
		}
		catch (SQLException ex) {
		    // handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		finally {	
			if (rs != null) {
			    try {
			        rs.close();
			    } catch (SQLException sqlEx) {} // ignore
			
			    rs = null;
			}
			if (stmt != null) {
			    try {
			        stmt.close();
			    } catch (SQLException sqlEx) {} // ignore
			
			        stmt = null;
			    }
		}
		return rs;
	}
}