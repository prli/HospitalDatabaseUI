package hospitalui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
	
	public ArrayList getPatient(String doctorID, String Phone, String Last_Visited_Date, String SIN, String NumOfVisits, String OHIP, String Current_Condition) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
		    stmt = (this.conn).createStatement();
		    
		   StringBuffer query = new StringBuffer();
		   
		   boolean input = false;
		   
		   query.append("SELECT OHIP, SIN, Phone, Current_health, Last_visit_date, Num_of_visits FROM Patient where DoctorId = '" + doctorID + "'");
		   if (Phone != "") {
			   query.append(" AND Phone = '" + Phone + "'");
			   input = true;
		   }
		   if (Last_Visited_Date != "") {
			   query.append(" AND Last_visit_date = '" + Last_Visited_Date + "'");
			   input = true;
		   }
		   if (SIN != "") {
			   query.append(" AND SIN = '" + SIN + "'");
			   input = true;
		   }
		   if (NumOfVisits != "") {
			   query.append(" AND Num_of_visits = '" + NumOfVisits + "'");
			   input = true;
		   }
		   if (OHIP != "") {
			   query.append(" AND OHIP = '" + OHIP + "'");
			   input = true;
		   }
		   if (Current_Condition != "") {
			   query.append(" AND Current_health = '" + Current_Condition + "'");
			   input = true;
		   }
		   
		   if (input == true) {
			   rs = stmt.executeQuery(query.toString());
		   }
		   else {
			   rs = stmt.executeQuery("SELECT OHIP, SIN, Phone, Current_health, Last_visit_date, Num_of_visits FROM Patient where DoctorId = '" + doctorID + "'");
		   }
		   rs = stmt.getResultSet();
		    
		    // Now do something with the ResultSet ....
		   ArrayList patients = new ArrayList();
		    while (rs.next()) {
		    	Patient p = new Patient();
		    	p.setHomePhone(rs.getString("Phone"));
		    	p.setLastVisitedDate(rs.getDate("Last_visit_date"));
		    	p.setSin(rs.getString("SIN"));
		    	p.setNumOfVisits(rs.getInt("Num_of_visits"));
		    	p.setOhip(rs.getString("OHIP"));
		    	p.setHealthCondition(rs.getString("Current_health"));
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

	public ArrayList getFutureAppointments(String doctorId){
		
		Statement stmt = null;
		ResultSet rs = null;
		try {
		    stmt = (this.conn).createStatement();
		    
		    // need to handle if user put 
	    	rs = stmt.executeQuery("Select A.PatientId, A.Date_Time, A.Duration, A.Status from Appointment as A left Join Patient as P on A.PatientId = P.UserId where P.DoctorId = '" + doctorId + "' AND A.Date_Time >= current_time");
		    rs = stmt.getResultSet();
		    
		    // Now do something with the ResultSet ....
		    int iter = 1;
		    ArrayList appointments = new ArrayList();
		    while (rs.next()) {
		    	Appointment a = new Appointment();
		    	a.setPatientById(rs.getString("PatientId"));
		    	a.setStartTime(rs.getDate("Date_Time"));
		    	a.setDuration(rs.getInt("Duration"));
		    	a.setStatus(rs.getString("Status"));
		    	appointments.add(a);
		    }
		    return appointments;
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
}