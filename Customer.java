import java.time.LocalTime;
import java.util.ArrayList;

public class Customer {
	static ArrayList <Customer> customers = new ArrayList <Customer>();
	private String fname;
	private String lname; 
	private String phone;
	private String email;
	private String c_id;
	private String password;
	private boolean isMember;
	private boolean templicense;
	private String license;
	private String creditcard;
	private int ccv;
	private LocalTime login;
	private LocalTime logout;
	static boolean isguest;
	
	static boolean getguest() {
		return isguest;
	}
	private String getID() {
		return c_id;
	}
	private String getPass() {
		return password;
	}
	public String getFname() {
		return fname;
	}
	public String getLname() {
		return lname;
	}
	public String getPhone() {
		return phone;
	}
	public String getEmail() {
		return email;
	}
	public boolean isMember() {
		return isMember;
	}
	public boolean isTemplicense() {
		return templicense;
	}
	public String getLicense() {
		return license;
	}
	public String getCreditcard() {
		return creditcard;
	}
	public int getCcv() {
		return ccv;
	}
	public LocalTime login() {
		return login;
	}
	public LocalTime logout() {
		return logout;
	}
	
	public static boolean VerifyLogin(String c_id, String password) {
		boolean flag1 = false;
		boolean flag2 = false;
		for(int i = 0; i < customers.size(); i++) {
			if(customers.get(i).getID().compareTo(c_id) == 0) {
				flag1 = true;
				break;
			}
		}
		for(int x = 0; x < customers.size(); x++) {
			if(customers.get(x).getPass().compareTo(password) == 0) {
				flag2 = true;
				break;
		    }
		}
		if(flag1 == true && flag2 == true)
			return true;
		else
			return false;
	}
	public static void recordlogintime(LocalTime x, String userID) {
		for(int i = 0; i < customers.size(); i++) {
			if(customers.get(i).getID().compareTo(userID) == 0) {
				customers.set(i, new Customer(customers.get(i).getFname(), customers.get(i).getLname(), customers.get(i).getPhone(),
							  customers.get(i).getEmail(), userID, customers.get(i).getPass(), customers.get(i).isMember(), customers.get(i).isTemplicense(),
							  customers.get(i).getLicense(), customers.get(i).getCreditcard(), customers.get(i).getCcv(), x, null));	
				break;
			}
		}
	}
	public static void recordlogouttime(LocalTime y, String userID) {
		for(int i = 0; i < customers.size(); i++) {
			if(customers.get(i).getID().compareTo(userID) == 0) {
				customers.set(i, new Customer(customers.get(i).getFname(), customers.get(i).getLname(), customers.get(i).getPhone(),
							  customers.get(i).getEmail(), userID, customers.get(i).getPass(), customers.get(i).isMember(), customers.get(i).isTemplicense(),
							  customers.get(i).getLicense(), customers.get(i).getCreditcard(), customers.get(i).getCcv(), customers.get(i).login(), y));	
				break;
			}
		}
	}
	
	public void appendCustomer() {
		customers.add(new Customer(fname, lname, phone, email, c_id, password, isMember, templicense, license, creditcard, ccv, login, logout));
	}
	public static void removelist(int index) {
		if (index >= 0)
			customers.remove(index);
	}
	public String toString() {
		return "Full Name: " + fname + " " + lname + ", Phone #: " + phone + ", Email: " + email + 
				", ID:" + c_id + ", Password: " + password + ", Member Status: " + isMember + ", Temporary License: " + templicense + 
				", License Plate Number: " + license + ", Credit Card #: " + creditcard + ", CCV: " + ccv;
	}
	public static String listallCustomers() {
		for(int i = 0; i < customers.size(); i++) {
			int count = i + 1;
			System.out.println("Customer # " + count + " " + customers.get(i).toString());
		}
		return "";
	}
	public static int numberofMembers() {
		int count = 0;
		for(int i = 0; i < customers.size(); i++) {
			if(customers.get(i).isMember() == true)
				count++;
		}
		return count;
	}
	public static String printcurrcustomer(int index) {
		System.out.println(customers.get(index).toString());
		return "";
	}
	public static void ViewCustomer(String c_id) {
		for(int i = 0; i < customers.size(); i ++) {
			if(customers.get(i).getID().compareTo(c_id) == 0) {
				System.out.println(customers.get(i).toString());
			}
		}
	}
	
	public static void editcustomer_name(int i, String fname, String lname) {
		customers.set(i, new Customer(fname, lname, customers.get(i).getPhone(),
				  customers.get(i).getEmail(), customers.get(i).getID(), customers.get(i).getPass(), customers.get(i).isMember(), customers.get(i).isTemplicense(),
				  customers.get(i).getLicense(), customers.get(i).getCreditcard(), customers.get(i).getCcv(), null, null));	
	}
	public static void editcustomer_phone(int i, String phone) {
		customers.set(i, new Customer(customers.get(i).getFname(), customers.get(i).getLname(), phone,
				  customers.get(i).getEmail(), customers.get(i).getID(), customers.get(i).getPass(), customers.get(i).isMember(), customers.get(i).isTemplicense(),
				  customers.get(i).getLicense(), customers.get(i).getCreditcard(), customers.get(i).getCcv(), null, null));	
	}
	public static void editcustomer_email(int i, String email) {
		customers.set(i, new Customer(customers.get(i).getFname(), customers.get(i).getLname(), customers.get(i).getPhone(),
				  email , customers.get(i).getID(), customers.get(i).getPass(), customers.get(i).isMember(), customers.get(i).isTemplicense(),
				  customers.get(i).getLicense(), customers.get(i).getCreditcard(), customers.get(i).getCcv(), null, null));	
	}
	public static void editcustomer_ID(int i, String ID) {
		customers.set(i, new Customer(customers.get(i).getFname(), customers.get(i).getLname(), customers.get(i).getPhone(),
				customers.get(i).getEmail(), ID, customers.get(i).getPass(), customers.get(i).isMember(), customers.get(i).isTemplicense(),
				  customers.get(i).getLicense(), customers.get(i).getCreditcard(), customers.get(i).getCcv(), null, null));	
	}
	public static void editcustomer_pass(int i, String pass) {
		customers.set(i, new Customer(customers.get(i).getFname(), customers.get(i).getLname(), customers.get(i).getPhone(),
				customers.get(i).getEmail(), customers.get(i).getID(), pass, customers.get(i).isMember(), customers.get(i).isTemplicense(),
				  customers.get(i).getLicense(), customers.get(i).getCreditcard(), customers.get(i).getCcv(), null, null));	
	}
	public static void editcustomer_member(int i, boolean member) {
		customers.set(i, new Customer(customers.get(i).getFname(), customers.get(i).getLname(), customers.get(i).getPhone(),
				customers.get(i).getEmail(), customers.get(i).getID(),customers.get(i).getPass(), member, customers.get(i).isTemplicense(),
				  customers.get(i).getLicense(), customers.get(i).getCreditcard(), customers.get(i).getCcv(), null, null));	
	}
	public static void editcustomer_templicense(int i, boolean temp) {
		customers.set(i, new Customer(customers.get(i).getFname(), customers.get(i).getLname(), customers.get(i).getPhone(),
				customers.get(i).getEmail(), customers.get(i).getID(),customers.get(i).getPass(),customers.get(i).isMember(), temp,
				  customers.get(i).getLicense(), customers.get(i).getCreditcard(), customers.get(i).getCcv(), null, null));	
	}
	public static void editcustomer_license(int i, String license) {
		customers.set(i, new Customer(customers.get(i).getFname(), customers.get(i).getLname(), customers.get(i).getPhone(),
				customers.get(i).getEmail(), customers.get(i).getID(),customers.get(i).getPass(),customers.get(i).isMember(), customers.get(i).isTemplicense(),
				  license, customers.get(i).getCreditcard(), customers.get(i).getCcv(), null, null));	
	}
	public static void editcustomer_creditcard(int i, String creditcard) {
		customers.set(i, new Customer(customers.get(i).getFname(), customers.get(i).getLname(), customers.get(i).getPhone(),
				customers.get(i).getEmail(), customers.get(i).getID(),customers.get(i).getPass(),customers.get(i).isMember(), customers.get(i).isTemplicense(),
				 customers.get(i).getLicense(), creditcard, customers.get(i).getCcv(), null, null));	
	}
	public static void addguestcreditcard(String creditcard, int ccv) {
		for(int i = 0; i < customers.size(); i++) {
			if(customers.get(i).getID().compareTo("guest") == 0) {
				customers.set(i, new Customer("Guest", "", "", "", "guest", "", false, false, "", creditcard, ccv,
											  customers.get(i).login(), customers.get(i).logout()));
			}
		}
	}
	
	public static void editcustomer_ccv(int i, int ccv) {
		customers.set(i, new Customer(customers.get(i).getFname(), customers.get(i).getLname(), customers.get(i).getPhone(),
				customers.get(i).getEmail(), customers.get(i).getID(),customers.get(i).getPass(),customers.get(i).isMember(), customers.get(i).isTemplicense(),
				 customers.get(i).getLicense(), customers.get(i).getCreditcard(), ccv, null, null));	
	}
	public static void customerlogininfo() {
		for(int i = 0; i < customers.size(); i++) {
			System.out.println("c_id: " + customers.get(i).getID() + " Logged in @: " + customers.get(i).login()
							  + " Logged out @: " + customers.get(i).logout());
		}
	}
	
	public Customer(String fname, String lname, String phone, String email, String c_id, String password, boolean isMember, boolean templicense, 
					String license, String creditcard, int ccv, LocalTime login, LocalTime logout) {
		this.fname = fname;
		this.lname = lname;
		this.phone = phone;
		this.email = email;
		this.c_id = c_id;
		this.password = password;
		this.isMember = isMember;
		this.templicense = templicense;
		this.license = license;
		this.creditcard = creditcard;
		this.ccv = ccv;
		this.login = login;
		this.logout = logout;
	}
	
	public static void createcustomers() {
		LocalTime x = LocalTime.NOON;
		LocalTime y = LocalTime.of(13, 00);
		LocalTime z = LocalTime.of(15, 32);
		LocalTime w = LocalTime.of(15, 55);
		LocalTime u = LocalTime.of(12, 43);
		//TEST CODE
		Customer c1 = new Customer("Ray", "Jones", "230-456-9879", "Ray@gmail.com", "Ray_J", "123", true, false, "XI34NB", "131441411", 330, null, null);
		customers.add(c1);
		Customer c2 = new Customer("Sara", "Hill", "440-241-2354", "Sarahill@yahoo.com", "Sara_H", "random", true, false, "B8EGX09", "103839029", 881, z, w);
		customers.add(c2);
		Customer c3 = new Customer("Timmy", "Mills", "456-321-8876", "Timmy@hotmail.com", "Tim_M", "blahblah", true, false, "XTRA32", "131454523", 532, x, y);
		customers.add(c3);
		Customer c4 = new Customer("Guest", "", "", "", "guest", "", false, false, "", "", 0, null, null);
		customers.add(c4);
	}
}
