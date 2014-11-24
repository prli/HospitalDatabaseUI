package hospitalui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Financial_OfficerDAOImpl {
	
	Connection conn = null;
	
	public boolean ConnectToDB() {

		try {
			conn = DriverManager.getConnection("jdbc:mysql://eceweb.uwaterloo.ca:3306/ece356db_prli", "user_prli", "user_prli");

		    // Do something with the Connection
		    System.out.println("Connection to MYSQL Database Sucessful!");
		    
		    return true;

		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
		return false;
	}
	
	public ResultSet getProfile(String UserId) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
		    stmt = (this.conn).createStatement();
		    rs = stmt.executeQuery("SELECT * FROM Person WHERE UserId ='" + UserId + "'");
		    rs = stmt.getResultSet();
		    
		    // Now do something with the ResultSet ....
		    while (rs.next()) {
		    	System.out.println("Welcome " + rs.getString("FirstName") + " " + rs.getString("LastName"));
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
	
	public ResultSet getDoctorFromID(String doctorID) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
		    stmt = (this.conn).createStatement();
		    rs = stmt.executeQuery("SELECT * FROM Person WHERE PatientId ='" + doctorID + "'");
		    rs = stmt.getResultSet();
		    
		    // Now do something with the ResultSet ....
		    while (rs.next()) {
		    	System.out.println("Doctor name is "  + rs.getString("FirstName") + " " + rs.getString("LastName"));
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
	
	public ResultSet getAssignedPatientsList(String beginDate, String endDate, String doctorID) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
		    stmt = (this.conn).createStatement();
		    
		    
		    if (beginDate != null && endDate != null) {
		    	rs = stmt.executeQuery("SELECT P.FirstName, P.LastName, V.Date_of_visit FROM Person P, Visitation_Record V WHERE UserId IN (SELECT UserId FROM Patient WHERE DoctorId = '" + doctorID + "') AND '" + beginDate +" 00:00:00' <= V.Date_of_visit AND V.Date_of_visit <= '"+ endDate +" 23:59:59 ORDER BY V.Date_of_visit DESC");
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
		    	//System.out.println("Doctor name is "  + rs.getString("FirstName") + " " + rs.getString("LastName"));
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