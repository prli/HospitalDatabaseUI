package hospitalui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PatientDAOImpl {
	
	Connection conn = null;
	
	public PatientDAOImpl(){
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
	
	public ArrayList getVisitationRecord(String beginDate, String endDate, String patientID) {
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
		    stmt = (this.conn).createStatement();
		    
		    if (beginDate != "" && endDate != "") {
		    	rs = stmt.executeQuery("SELECT * FROM Visitation_Record WHERE PatientId ='" + patientID + "' AND '" + beginDate +" 00:00:00' <= Date_of_visit AND Date_of_visit <= '"+ endDate +" 23:59:59'" ); 
		    	//System.out.println("SELECT * FROM Visitation_Record WHERE PatientId ='" + patientID + "' AND '" + beginDate +" 00:00:00' <= Date_of_visit AND Date_of_visit <= '"+ endDate +" 23:59:59'");
		    }
		    else {
		    	rs = stmt.executeQuery("SELECT * FROM Visitation_Record WHERE PatientId ='" + patientID + "'");
		    }
		    rs = stmt.getResultSet();
		    
		    // Now do something with the ResultSet ....
		    //System.out.println("Finding records from " + beginDate + " to " + endDate + " given ID of " + patientID);
		    int iter = 1;
		    ArrayList patientRecords = new ArrayList();
		    while (rs.next()) {
		    	Visitation_record vr = new Visitation_record();
		    	vr.setPatientById(rs.getString("PatientId"));
		    	vr.setDoctorById(rs.getString("DoctorId"));
		    	vr.setProcedures(rs.getString("Procedures"));
		    	vr.setDiagnosis(rs.getString("Diagnosis"));
		    	vr.setPrescription(rs.getString("Prescription"));
		    	vr.setScheduleOfTreatment(rs.getString("Schedule_of_treatment"));
		    	vr.setCostOfVisit(rs.getDouble("Cost_of_visit"));
		    	vr.setLengthOfVisit(rs.getDouble("Length_of_visit"));
		    	vr.setComments(rs.getString("Comments"));
		    	vr.setDateOfVisit(rs.getDate("Date_of_visit"));
		    	patientRecords.add(vr);
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
		    return patientRecords;
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