package hospitalui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class PatientDAOImpl {
	
	Connection conn = null;
	
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
	
	public ResultSet getAllVisitationRecords() {
		Statement stmt = null;
		ResultSet rs = null;
		try {
		    stmt = (this.conn).createStatement();
		    rs = stmt.executeQuery("SELECT * FROM Visitation_Record");
		    rs = stmt.getResultSet();
		    
		    // Now do something with the ResultSet ....
		    int iter = 1;
		    while (rs.next()) {
		    	System.out.println("--- Visitation Record " + iter + " ---");
		    	System.out.println("Patient ID: " + rs.getString("PatientId"));
			    System.out.println("Doctor ID: " + rs.getString("DoctorId"));
			    System.out.println("Procedure: " + rs.getString("Procedures"));
			    System.out.println("Diagnosis: " + rs.getString("Diagnosis"));
			    System.out.println("Prescription: " + rs.getString("Prescription"));
			    System.out.println("Schedule of Treatment: " + rs.getString("Schedule_of_treatment"));
			    System.out.println("Cost of Visit: " + rs.getFloat("Cost_of_visit"));
			    System.out.println("Length of Visit: " + rs.getFloat("Length_of_visit"));
			    System.out.println("Comments: " + rs.getString("Comments"));
			    System.out.println("Date of Visit: " + rs.getString("Date_of_visit"));
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
	
	public ResultSet getVisitationRecord(String beginDate, String endDate, String patientID) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
		    stmt = (this.conn).createStatement();
		    rs = stmt.executeQuery("SELECT * FROM Visitation_Record WHERE PatientId ='" + patientID + "' AND '" + beginDate +" 00:00:00' <= Date_of_visit AND Date_of_visit <= '"+ endDate +" 23:59:59'" );
		    rs = stmt.getResultSet();
		    
		    // Now do something with the ResultSet ....
		    System.out.println("Finding records from " + beginDate + " to " + endDate + " given ID of " + patientID);
		    int iter = 1;
		    while (rs.next()) {
		    	System.out.println("--- Visitation Record " + iter + " ---");
		    	System.out.println("Patient ID: " + rs.getString("PatientId"));
			    System.out.println("Doctor ID: " + rs.getString("DoctorId"));
			    System.out.println("Procedure: " + rs.getString("Procedures"));
			    System.out.println("Diagnosis: " + rs.getString("Diagnosis"));
			    System.out.println("Prescription: " + rs.getString("Prescription"));
			    System.out.println("Schedule of Treatment: " + rs.getString("Schedule_of_treatment"));
			    System.out.println("Cost of Visit: " + rs.getFloat("Cost_of_visit"));
			    System.out.println("Length of Visit: " + rs.getFloat("Length_of_visit"));
			    System.out.println("Comments: " + rs.getString("Comments"));
			    System.out.println("Date of Visit: " + rs.getString("Date_of_visit"));
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
	
	/*public void updateRecord(String date, String ID) {
		
	}*/
}