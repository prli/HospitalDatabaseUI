package hospitalui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class HospitalDAOImpl {
	
	public void testConnection() {
		Connection conn = null;

		try {
			conn = DriverManager.getConnection("jdbc:mysql://eceweb.uwaterloo.ca:3306/ece356db_prli", "user_prli", "user_prli");

		    // Do something with the Connection
		    System.out.println("Connection Sucessful!");
		    
		    Statement stmt = null;
		    ResultSet rs = null;
		    
		    try {
		        stmt = conn.createStatement();
		        rs = stmt.executeQuery("SELECT * FROM Person");

		        // or alternatively, if you don't know ahead of time that
		        // the query will be a SELECT...

		        if (stmt.execute("SELECT * FROM Person")) {
		            rs = stmt.getResultSet();
		        }
		       
		        while (rs.next()) {
		        	// Now do something with the ResultSet ....
			        System.out.println("Result Set: " + rs.getString("UserId"));
		        }
		        rs.close();
		        stmt.close();

		    }
		    catch (SQLException ex){
		        // handle any errors
		        System.out.println("SQLException: " + ex.getMessage());
		        System.out.println("SQLState: " + ex.getSQLState());
		        System.out.println("VendorError: " + ex.getErrorCode());
		    }
		    finally {
		        // it is a good idea to release
		        // resources in a finally{} block
		        // in reverse-order of their creation
		        // if they are no-longer needed

		        if (rs != null) {
		            try {
		                rs.close();
		            } catch (SQLException sqlEx) { } // ignore

		            rs = null;
		        }

		        if (stmt != null) {
		            try {
		                stmt.close();
		            } catch (SQLException sqlEx) { } // ignore

		            stmt = null;
		        }
		    }


		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	
}