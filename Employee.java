import java.time.LocalTime;
import java.util.ArrayList;

public class Employee {
//Record of all employees
	static ArrayList <Employee> data = new ArrayList <Employee>();
	private String firstname;
	private String lastname;
	private int SSN;
	private String e_id;
	private String password;
	private double salary;
	private LocalTime login;
	private LocalTime logout;
	
	private String getfirstname() {
		return firstname;
	}
	private String getlastname() {
		return lastname;
	}
	private String getID() {
		return e_id;
	}
	private String getPass() {
		return password;
	}
	private int getSSN() {
		return SSN;
	}
	private double getSalary() {
		return salary;
	}
	private LocalTime login() {
		return login;
	}
	private LocalTime logout() {
		return logout;
	}
	
	public static void editdata_name(int index, String fname, String lname) {
		String ID = data.get(index).getID();
		String pass = data.get(index).getPass();
		int ssn = data.get(index).getSSN();
		double sal = data.get(index).getSalary();
		data.set(index, new Employee(fname, lname, ssn, ID, pass, sal, null, null));
	}
	
	public static void editdata_SSN(int index, int SSN) {
		String fname = data.get(index).getfirstname();
		String lname = data.get(index).getlastname();
		String ID = data.get(index).getID();
		String pass = data.get(index).getPass();
		double sal = data.get(index).getSalary();
		data.set(index, new Employee(fname, lname, SSN, ID, pass, sal, null, null));
	}
	
	public static void editdata_ID(int index, String ID) {
		String fname = data.get(index).getfirstname();
		String lname = data.get(index).getlastname();
		int ssn = data.get(index).getSSN();
		String pass = data.get(index).getPass();
		double sal = data.get(index).getSalary();
		data.set(index, new Employee(fname, lname, ssn, ID, pass, sal, null, null));
	}
	
	public static void editdata_pass(int index, String password) {
		String fname = data.get(index).getfirstname();
		String lname = data.get(index).getlastname();
		int ssn = data.get(index).getSSN();
		String ID = data.get(index).getID();
		double sal = data.get(index).getSalary();
		data.set(index, new Employee(fname, lname, ssn, ID, password, sal, null, null));
	}
	
	public static void editdata_salary(int index, double sal) {
		String fname = data.get(index).getfirstname();
		String lname = data.get(index).getlastname();
		int ssn = data.get(index).getSSN();
		String pass = data.get(index).getPass();
		String ID = data.get(index).getID();
		data.set(index, new Employee(fname, lname, ssn, ID, pass, sal, null, null));
	}
	
//Creates Employee Object and adds to the data array
	public Employee(String fname, String lname, int SSN, String e_id, String password, double salary, LocalTime login, LocalTime logout) {
		firstname = fname;
		lastname = lname;
		this.SSN = SSN;
		this.e_id = e_id;
		this.password = password;
		this.salary = salary;
		this.login = login;
		this.logout = logout;
	}
	
	public void appendlist() {
		data.add(new Employee(firstname, lastname, this.SSN, this.e_id, this.password, this.salary, this.login, this.logout));
	}
	public static void Employeelogininfo() {
		for(int i = 0; i < data.size(); i++) {
			System.out.println("e_id: " + data.get(i).getID() + " Logged in @: " + data.get(i).login()
							  + " Logged out @: " + data.get(i).logout());
		}
	}
	public static void removelist(int index) {
		if (index >= 0)
			data.remove(index);
	}
	
	public static boolean VerifyLogin(String e_id, String password) {
		boolean flag1 = false;
		boolean flag2 = false;
		for(int i = 0; i < data.size(); i++) {
			if(data.get(i).getID().compareTo(e_id) == 0) {
				flag1 = true;
				break;
			}
		}
		for(int x = 0; x < data.size(); x++) {
			if(data.get(x).getPass().compareTo(password) == 0) {
				flag2 = true;
				break;
		    }
		}
		if(flag1 == true && flag2 == true)
			return true;
		else
			return false;
	}
	public static void recordLogin(LocalTime x, String userID) {
		for(int i = 0; i < data.size(); i++) {
			if(data.get(i).getID().compareTo(userID) == 0) {
				data.set(i, new Employee(data.get(i).getfirstname(), data.get(i).getlastname(), data.get(i).getSSN(),
						data.get(i).getID(), data.get(i).getPass(), data.get(i).getSalary(), x, null));	
				break;
			}
		}
	}
	public static void recordLogout(LocalTime y, String userID) {
		for(int i = 0; i < data.size(); i++) {
			if(data.get(i).getID().compareTo(userID) == 0) {
				data.set(i, new Employee(data.get(i).getfirstname(), data.get(i).getlastname(), data.get(i).getSSN(),
						data.get(i).getID(), data.get(i).getPass(), data.get(i).getSalary(), data.get(i).login(), y));	
				break;
			}
		}
	}
	public static void viewSalary(String userID) {
		for(int i = 0; i < data.size(); i++) {
			if(data.get(i).getID().compareTo(userID) == 0) {
				System.out.println(userID + " Salary: $" + data.get(i).getSalary()); 
				break;
			}
		}
		
	}
	
	public String toString() {
		return "Full Name: " + firstname + " " + lastname + ", SSN: " + SSN + ", ID: " + e_id +
			   ", Password: " + password + ", Salary: " + salary + ":\n";
	}
	public static String printcurremployee(int index) {
		System.out.println(data.get(index).toString());
		return "";
	}
	public static String listofEmployees() {
		for(int z = 0; z < data.size(); z++) {
			int count = z + 1;
			System.out.println("Employee # " + count + " " + data.get(z).toString());
		}
		return "";
	}
	public static void populate() {
		LocalTime x = LocalTime.of(6, 0);
		LocalTime y = LocalTime.of(7, 15);
		LocalTime z = LocalTime.of(9, 12);
		LocalTime w = LocalTime.of(12, 44);
		//TEST CODE
		Employee e1 = new Employee("Bob", "Charles", 00011000, "Bob_C", "thisismypassword", 75000, null, null);
		data.add(e1);
		Employee e2 = new Employee("Thomas", "Hettle", 11100111, "Tom_H", "abadpassword", 100000, x, y);
		data.add(e2);
		Employee e3 = new Employee("Eddie", "Brown", 33311333, "Edd_B", "blahblah", 50000, z, w);
		data.add(e3);
	}
	
}
