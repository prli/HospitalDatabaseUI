package hospitalui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.LinkedList;

public class DoctorDAOImpl {
	
	Connection conn = null;
	
	public DoctorDAOImpl(){
		try {
			//conn = DriverManager.getConnection("jdbc:mysql://eceweb.uwaterloo.ca:3306/ece356db_prli", "user_prli", "user_prli");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ece356db_prli", "root", "root");

		    // Do something with the Connection
		    System.out.println("Connection to MYSQL Database Sucessful!");
		    
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	
	public boolean ConnectToDB() {

		try {
			//conn = DriverManager.getConnection("jdbc:mysql://eceweb.uwaterloo.ca:3306/ece356db_prli", "user_prli", "user_prli");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ece356_hospital", "root", "root");

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
	
	public LinkedList getPatient(String doctorID, String Phone, String Last_Visited_Date, String SIN, String NumOfVisits, String OHIP, String Current_Condition) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
		    stmt = (this.conn).createStatement();
		    
		   StringBuffer query = new StringBuffer(); 
		   
		   query.append("SELECT FirstName, LastName FROM Person WHERE UserId IN (SELECT UserId FROM Patient WHERE DoctorId = '" + doctorID + "'");
		   if (Phone != null || Last_Visited_Date != null || SIN != null || NumOfVisits != null || OHIP != null || Current_Condition != null) {
			   if (Phone != null) {
				   query.append("AND Phone = '" + Phone + "' ");
			   }
			   if (Last_Visited_Date != null) {
				   query.append("AND Last_visit_date = '" + Last_Visited_Date + "' ");
			   }
			   if (SIN != null) {
				   query.append("AND SIN = '" + SIN + "' ");
			   }
			   if (NumOfVisits != null) {
				   query.append("AND Num_of_visits = '" + NumOfVisits + "' ");
			   }
			   if (OHIP != null) {
				   query.append("AND OHIP = '" + OHIP + "' ");
			   }
			   if (Current_Condition != null) {
				   query.append("AND Current_health = '" + Current_Condition + "' ");
			   }
			   query.append(")"); // no error checking for all fields null
		   }
		   else {
			   query.append(")");
		   }
		   
		   rs = stmt.executeQuery(query.toString());
		   rs = stmt.getResultSet();
		    
		    // Now do something with the ResultSet ....
		   LinkedList patients = new LinkedList();
		    while (rs.next()) {
		    	Patient p = new Patient();
		    	p.setId(rs.getString("UserId"));
		    	p.setOhip(rs.getString("OHIP"));
		    	p.setSin(rs.getString("SIN"));
		    	p.setOhip(rs.getString("OHIP"));
		    	p.setHomePhone(rs.getString("Phone"));
		    	p.setDoctorById(rs.getString("DoctorId"));
		    	p.setHealthCondition(rs.getString("Current_health"));
		    	p.setLastVisitedDate(rs.getDate("Last_visit_date"));
		    	p.setNumOfVisits(rs.getInt("Num_of_visits"));
		    	patients.add(p);
		    }
		    return patients;
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