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
	
	public ArrayList getDoctorListByStaff(String staffId){
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
		    stmt = (this.conn).createStatement();

		    rs = stmt.executeQuery("select FirstName, LastName, UserId from Person where UserId in (select DoctorId from Staff where UserId = '" + staffId + "')" );
		    rs = stmt.getResultSet();
		    
		    // Now do something with the ResultSet ....
		    //System.out.println("Finding records from " + beginDate + " to " + endDate + " given ID of " + patientID);
		    int iter = 1;
		    ArrayList doctorList = new ArrayList();
		    while (rs.next()) {
		    	Doctor d = new Doctor();
		    	d.setId(rs.getString("UserId"));
		    	d.setFirstName(rs.getString("FirstName"));
		    	d.setLastName(rs.getString("LastName"));
		    	doctorList.add(d);
			    iter++;
		    }
		    return doctorList;
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
	
	public ArrayList getPatientListByStaff(String staffId){
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
		    stmt = (this.conn).createStatement();

		    rs = stmt.executeQuery("select P.UserId, P.FirstName, P.LastName, "+
"PA.OHIP, PA.SIN, PA.Phone, PA.DoctorId, PA.Current_health, PA.Last_visit_date, PA.Num_of_visits "+
"from Person P inner join Patient PA on P.UserId = PA.UserId "+
"where P.UserId in "+
"(select P.UserId from Patient P inner join Staff S on S.DoctorId = P.DoctorId where '" + staffId + "' = S.UserId) "+
"OR P.UserId in"+
"(select P.PatientId from Permission P inner join Staff S on S.DoctorId = P.GranteeId where '" + staffId + "' = S.UserId)" );
		    rs = stmt.getResultSet();
		    
		    // Now do something with the ResultSet ....
		    //System.out.println("Finding records from " + beginDate + " to " + endDate + " given ID of " + patientID);
		    int iter = 1;
		    ArrayList patientList = new ArrayList();
		    while (rs.next()) {
		    	Patient p = new Patient();
		    	p.setId(rs.getString("UserId"));
		    	p.setFirstName(rs.getString("FirstName"));
		    	p.setLastName(rs.getString("LastName"));
		    	p.setOhip(rs.getString("OHIP"));
		    	p.setSin(rs.getString("SIN"));
		    	p.setDoctorById(rs.getString("DoctorId"));
		    	p.setHealthCondition(rs.getString("Current_health"));
		    	p.setLastVisitedDate(rs.getDate("Last_visit_date"));
		    	p.setNumOfVisits(rs.getInt("Num_of_visits"));
		    	patientList.add(p);
			    iter++;
		    }
		    return patientList;
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