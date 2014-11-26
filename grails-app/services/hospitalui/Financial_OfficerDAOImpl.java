package hospitalui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Financial_OfficerDAOImpl {
	
	Connection conn = null;
	
	public Financial_OfficerDAOImpl(){
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
	
	public ArrayList getDoctorFromInput(String doctorID, String firstName, String lastName) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
		    stmt = (this.conn).createStatement();
		    
		   StringBuffer query = new StringBuffer();
		   
		   boolean input = false;
		   
		   query.append("Select D.UserId, P.FirstName, P.LastName, P.Password, IFNULL(sum(V.Cost_of_visit),0) AS Revenue from Person P INNER JOIN Doctor D on P.UserId = D.UserId LEFT JOIN Visitation_Record V on D.UserId = V.DoctorId WHERE ");
		   if (doctorID != "") {
			   query.append("D.UserId = '" + doctorID + "'");
			   input = true;
		   }
		   if (firstName != "") {
			   if (input == true) {
				   query.append(" AND ");
			   }
			   query.append("P.FirstName = '" + firstName + "'");
			   input = true;
		   }
		   if (lastName != "") {
			   if (input == true) {
				   query.append(" AND ");
			   }
			   query.append("P.LastName = '" + lastName + "'");
			   input = true;
		   }
		   query.append(" Group by D.UserId");
		   
		   if (input == true) {
			   rs = stmt.executeQuery(query.toString()); 
		   }
		   else {
			   rs = stmt.executeQuery("Select D.UserId, P.FirstName, P.LastName, P.Password, IFNULL(sum(V.Cost_of_visit),0) AS Revenue from Person P INNER JOIN Doctor D on P.UserId = D.UserId LEFT JOIN Visitation_Record V on D.UserId = V.DoctorId Group by D.UserId;");
		   }
		   rs = stmt.getResultSet();
		    
		    // Now do something with the ResultSet ....
		   ArrayList doctors = new ArrayList();
		    while (rs.next()) {
		    	Doctor d = new Doctor();
		    	d.setId(rs.getString("UserId"));
		    	d.setFirstName(rs.getString("FirstName"));
		    	d.setLastName(rs.getString("LastName"));
		    	d.setPassword(rs.getString("Password"));
		    	d.setRevenue(rs.getDouble("Revenue"));
		    	doctors.add(d);
		    }
		    return doctors;
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
		    	rs = stmt.executeQuery("SELECT P.FirstName, P.LastName, V.PatientId, V.Date_of_visit FROM Person P INNER JOIN Visitation_Record V ON P.UserId = V.PatientId WHERE P.UserId IN (SELECT UserId FROM Patient WHERE DoctorId = '" + doctorID + "') AND '" + beginDate +" 00:00:00' <= V.Date_of_visit AND V.Date_of_visit <= '"+ endDate +" 23:59:59' ORDER BY V.Date_of_visit DESC");
			    rs = stmt.getResultSet();
		    }
		    else {
		    	rs = stmt.executeQuery("SELECT P.FirstName, P.LastName, V.PatientId, V.Date_of_visit FROM Person P INNER JOIN Visitation_Record V ON P.UserId = V.PatientId WHERE P.UserId IN (SELECT UserId FROM Patient WHERE DoctorId = '" + doctorID + "') ORDER BY V.Date_of_visit DESC");
			    rs = stmt.getResultSet();
		    }
		    
		    // Now do something with the ResultSet ....
		    int iter = 1;
		    while (rs.next()) {
		    	System.out.println("Patient [" + iter + "] : " + rs.getString("FirstName") + " " + rs.getString("LastName") + " " + rs.getString("patientId") + " - Visit time is " + rs.getString("Date_of_visit"));
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