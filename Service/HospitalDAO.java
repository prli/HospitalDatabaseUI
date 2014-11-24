package hospitalui;

import java.util.List;

public interface PersonDAO {
	public void updateProfile(String ID);
}

public interface Financial_OfficerDAO extends PersonDAO {
	public List<Patient> getAllPatients();
	public Patient getPatient(String ID);
	
}

public interface StaffDAO extends PersonDAO {
	public List<Patient> getAllPatients();
	public Patient getPatient(String ID)
	public List<Doctor> getAllDoctors();
	public Doctor getDoctor(String ID)
	public void updatePatient(Patient patient);
	public void deletePatient(Patient patient);
}

public interface DoctorDAO extends PersonDAO {
	public List<Patient> getAllPatients();
	public List<Patient> getAllCurrentPatients(String ID); // find doctor's patients
	public Patient getPatient(String ID)
}

public interface PatientDAO extends PersonDAO {
	public List<Record> getAllRecords();
	public Patient getRecord(String date, String ID);
	public void updateRecord(String date, String ID);
}

/*public interface AppointmentDAO {
	public List<Record> getAllRecords();
	public Patient getRecord(String date, String ID);
}

public interface Appointment_HistoryDAO extends AppointmentDAO {

}*/