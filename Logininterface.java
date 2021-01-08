package Project;

import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;

public class Logininterface {
	private static String userID;
	private static String password;
	private static LocalTime loggedin;
	private static LocalTime loggedout;
	
	//Login Time
	public static String login() {
		LocalTime logintime = LocalTime.now();
		loggedin = logintime;
		return "Logged in at.. " + logintime;
	}
	//Logout Time
	public static String logout() {
		LocalTime logouttime = LocalTime.now();
		loggedout = logouttime;
		return "Logged out at .. " + logouttime;
	}
	
	public static void main(String[] args) throws SQLException {
		Connection myConn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "1234");
		Admin.setup();
		Customer.createcustomers();
		Parkingspots.populateParking();
		Employee.populate();
		boolean run = false;
		String start = "";
		Scanner input = new Scanner(System.in);
		System.out.println("RUN? (Y/N)");
		start = input.next().toLowerCase();
		if(start.compareTo("y") == 0) {
			run = true;
			while (run == true) {
				System.out.println("Greetings! Please type in your USER type as: 'admin', 'employee', or 'user'.");
				String userType = input.next().toLowerCase();
//ADMIN
		if (userType.compareTo("admin") == 0) {
			String choice1 = "";
			System.out.println("ENTER USERNAME:");
			choice1 = input.next();
			userID = choice1;
			System.out.println("ENTER PASSWORD:");
			choice1 = input.next();
			password = choice1;
			
			boolean loginCheck = false;
			if(Admin.VerifyAdminLogin(userID, password) == true) {
				loginCheck = true;
				System.out.println(login());	
				boolean exit = false;
				while (exit == false) {
					System.out.println("Would you like to add a new Employee? (Y/N)");
					choice1 = input.next().toLowerCase();	
					if(choice1.compareTo("y") == 0) {
						String fname = "";
						String lname = "";
						String e_SSN = "";
						String e_id = "";
						String password = "";
						String salary = "";
						System.out.println("Enter the Employee's first name:");
						fname = input.next();
						System.out.println("Enter the Employee's last name:");
						lname = input.next();
						System.out.println("Enter the Employee's SSN:");
						e_SSN = input.next();
						System.out.println("Create Employee's sign in ID:");
						e_id = input.next();
						System.out.println("Create Employee's password:");
						password = input.next();
						System.out.println("Set Employee's salary:");
						salary = input.next();
						int SSN = Integer.parseInt(e_SSN);
						double sal = Double.parseDouble(salary);
						
						Employee newemployee = new Employee(fname, lname, SSN, e_id, password, sal, null, null);
						
						String create="INSERT INTO emp VALUES('"+fname+"','"+lname+"',"+e_SSN+",'"+e_id+"','"+password+"','"+salary+"')";
						Statement stmt1=myConn.createStatement();
						stmt1.executeUpdate(create);
						System.out.println("Customer Created");
						newemployee.appendlist();
						System.out.println("Above is the list of all employees: " +"\n" + Employee.listofEmployees());
					}
					System.out.println("Would you like to edit an existing Employee? (Y/N)");
					choice1 = input.next().toLowerCase();
					if(choice1.compareTo("y") == 0) {
						String e_numint;
						System.out.printf("Below is the list of all employees: " +"\n");
						String select="SELECT * FROM emp";
						Statement stmt2 = myConn.createStatement () ;
						ResultSet rs = stmt2.executeQuery(select);
						System.out.println("\nFull Name\t\tSSN\t\tEmp ID\t\tSalary\n");
						while(rs.next()) {
							String fname=rs.getString("firstname");
							String lname=rs.getString("lastname");
							int ssn=rs.getInt("ssn");
							String eid=rs.getString("eid");
							int salary=rs.getInt("salary");
							System.out.println(fname+" "+lname+"\t\t\t"+ssn+"\t\t"+eid+"\t\t"+salary);	
						}
						System.out.println();
						System.out.println("Enter the Employee ID that corresponds to the one you want to change shown above:");
						e_numint = input.next();
						boolean override = false; 
						int e_num=0;
						ArrayList <Employee> e = Employee.ret();
						int i=0;
						for(Employee f:e) {
							if(f.getID().compareTo(e_numint)==0)
								e_num=i;
							i++;
						}

						String id="";
						
						System.out.println("Would you like to remove this employee? (Y/N)");
						choice1 = input.next();
						if(choice1.compareTo("y")==0) {
							override = true;
							Employee.removelist(e_num);
							String delete1="DELETE from emp WHERE eid='"+e_numint+"'";
							Statement stmt1=myConn.createStatement();
							stmt1.execute(delete1);
							System.out.println("Removed.. " + "\n");
							String select1="SELECT * FROM emp";
							Statement stmt21 = myConn.createStatement () ;
							ResultSet rs1 = stmt21.executeQuery(select1);
							System.out.println("\nFull Name\t\tSSN\t\t\tEmp ID\t\tSalary");
							while(rs1.next()) {
								String fname=rs1.getString("firstname");
								String lname=rs1.getString("lastname");
								int ssn=rs1.getInt("ssn");
								String eid=rs1.getString("eid");
								int salary=rs1.getInt("salary");
								System.out.println(fname+" "+lname+"\t\t\t"+ssn+"\t\t"+eid+"\t\t"+salary);	
							}
						}
						boolean flag = false;
						while (flag == false && override == false) {
							System.out.println("What would you like to change: 'NAME', 'SSN', 'ID', 'PASSWORD', 'SALARY'?:");
							choice1 = input.next().toLowerCase();
							if(choice1.compareTo("name") == 0) {
								String newfname = "";
								String newlname = "";
								System.out.println("Enter in the new first name:");
								newfname = input.next();
								System.out.println("Enter in the new last name:");
								newlname = input.next();
								Employee.editdata_name(e_num, newfname, newlname);
								String update1="UPDATE emp SET firstname='"+newfname+"',lastname='"+newlname+"' where eid='"+e_numint+"'";
								Statement stmt1=myConn.createStatement();
								stmt1.executeUpdate(update1);
								String select1="SELECT * FROM emp";
								Statement stmt21 = myConn.createStatement () ;
								ResultSet rs1 = stmt21.executeQuery(select1);
								System.out.println("\nFull Name\t\tSSN\t\t\tEmp ID\t\tSalary");
								while(rs1.next()) {
									String fname=rs1.getString("firstname");
									String lname=rs1.getString("lastname");
									int ssn=rs1.getInt("ssn");
									String eid=rs1.getString("eid");
									int salary=rs1.getInt("salary");
									System.out.println(fname+" "+lname+"\t\t\t"+ssn+"\t\t"+eid+"\t\t"+salary);	
								}
								
							}
							if(choice1.compareTo("ssn") == 0) {
								String newSSN = "";
								System.out.println("Enter in the new SSN:");
								newSSN = input.next();
								Employee.editdata_SSN(e_num, Integer.parseInt(newSSN));
								int new_SSN = Integer.parseInt(newSSN);
								String update1="UPDATE emp SET ssn="+new_SSN+" where eid='"+e_numint+"'";
								Statement stmt1=myConn.createStatement();
								stmt1.executeUpdate(update1);
								String select1="SELECT * FROM emp";
								Statement stmt21 = myConn.createStatement () ;
								ResultSet rs1 = stmt21.executeQuery(select1);
								System.out.println("\nFull Name\t\tSSN\t\t\tEmp ID\t\tSalary");
								while(rs1.next()) {
									String fname=rs1.getString("firstname");
									String lname=rs1.getString("lastname");
									int ssn=rs1.getInt("ssn");
									String eid=rs1.getString("eid");
									int salary=rs1.getInt("salary");
									System.out.println(fname+" "+lname+"\t\t\t"+ssn+"\t\t"+eid+"\t\t"+salary);	
								}
							}
							if(choice1.compareTo("id") == 0) {
								String newid = "";
								System.out.println("Enter in the new ID:");
								newid = input.next();
								Employee.editdata_ID(e_num, newid);
								String update1="UPDATE emp SET eid="+newid+" where eid='"+e_numint+"'";
								Statement stmt1=myConn.createStatement();
								stmt1.executeUpdate(update1);
								String select1="SELECT * FROM emp";
								Statement stmt21 = myConn.createStatement () ;
								ResultSet rs1 = stmt21.executeQuery(select1);
								System.out.println("\nFull Name\t\tSSN\t\t\tEmp ID\t\tSalary");
								while(rs1.next()) {
									String fname=rs1.getString("firstname");
									String lname=rs1.getString("lastname");
									int ssn=rs1.getInt("ssn");
									String eid=rs1.getString("eid");
									int salary=rs1.getInt("salary");
									System.out.println(fname+" "+lname+"\t\t\t"+ssn+"\t\t"+eid+"\t\t"+salary);	
								}
							}
							if(choice1.compareTo("password") == 0) {
								String newpassword = "";
								System.out.println("Enter in the new password:");
								newpassword = input.next();
								Employee.editdata_pass(e_num, newpassword);
								String update1="UPDATE emp SET password="+newpassword+" where eid='"+e_numint+"'";
								Statement stmt1=myConn.createStatement();
								stmt1.executeUpdate(update1);
								String select1="SELECT * FROM emp";
								Statement stmt21 = myConn.createStatement () ;
								ResultSet rs1 = stmt21.executeQuery(select1);
								System.out.println("\nFull Name\t\tSSN\t\t\tEmp ID\t\tSalary");
								while(rs1.next()) {
									String fname=rs1.getString("firstname");
									String lname=rs1.getString("lastname");
									int ssn=rs1.getInt("ssn");
									String eid=rs1.getString("eid");
									int salary=rs1.getInt("salary");
									System.out.println(fname+" "+lname+"\t\t\t"+ssn+"\t\t"+eid+"\t\t"+salary);	
								}
							}
							if(choice1.compareTo("salary") == 0) {
								String newsalary = "";
								System.out.println("Enter in the new salary:");
								newsalary = input.next();
								double newsal = Double.parseDouble(newsalary);
								Employee.editdata_salary(e_num, newsal);
								String update1="UPDATE emp SET salary="+newsal+" where eid='"+e_numint+"'";
								Statement stmt1=myConn.createStatement();
								stmt1.executeUpdate(update1);
								String select1="SELECT * FROM emp";
								Statement stmt21 = myConn.createStatement () ;
								ResultSet rs1 = stmt21.executeQuery(select1);
								System.out.println("\nFull Name\t\tSSN\t\t\tEmp ID\t\tSalary");
								while(rs1.next()) {
									String fname=rs1.getString("firstname");
									String lname=rs1.getString("lastname");
									int ssn=rs1.getInt("ssn");
									String eid=rs1.getString("eid");
									int salary=rs1.getInt("salary");
									System.out.println(fname+" "+lname+"\t\t\t"+ssn+"\t\t"+eid+"\t\t"+salary);	
								}
							}
							System.out.println("Would you like to exit the editor? (Y/N)");
							choice1 = input.next().toLowerCase();
							if(choice1.compareTo("y") == 0)
								flag = true;
						}
					}
					System.out.println("Would you like to add a new Member? (Y/N)");
					choice1 = input.next().toLowerCase();	
					if(choice1.compareTo("y") == 0) {
						System.out.println("Enter the first name:");
						String fname = input.next();
						System.out.println("Enter the last name:");
						String lname = input.next();
						System.out.println("Enter the phone #:");
						String phone = input.next();
						System.out.println("Enter the full email:");
						String email = input.next();;
						System.out.println("Create the members ID:");
						String c_id = input.next();;
						System.out.println("Create the members password:");
						String password = input.next();
						boolean isMember = true;
						System.out.println("Do you have a temporary license plate? (Y/N)");
						choice1 = input.next().toLowerCase();
						boolean templicense = false;
						if(choice1.compareTo("y") == 0) {
							templicense = true;
						}
						System.out.println("Enter the license plate #:");
						String license = input.next();
						System.out.println("Please enter credit card #:");
						String creditcard = input.next();
						System.out.println("Please enter the CCV for the credit card:");
						int ccv = Integer.parseInt(input.next());
						Customer newcustomer = new Customer(fname, lname, phone, email, c_id, password, isMember, 
								                              templicense, license, creditcard, ccv, null, null);
						newcustomer.appendCustomer();
						
						String create="INSERT INTO cust VALUES('"+fname+"','"+lname+"','"+phone+"','"+
						email+"','"+c_id+"','"+password+"',"+isMember+","+templicense+",'"+license+"','"+creditcard+
						"',"+ccv+")";
						Statement stmt1=myConn.createStatement();
						stmt1.executeUpdate(create);
						System.out.println("Member Created");
						
						System.out.println("Below is the list of all Members: " + "\n");
						
						String select1="SELECT * FROM cust";
						Statement stmt21 = myConn.createStatement () ;
						ResultSet rs1 = stmt21.executeQuery(select1);
						System.out.println("E ID\t\tFull Name\t\tPhone Number\t\t\tEmail ID\t\tTemp License\t\tLicense\t\tCredit Card\t\tCVV");
						while(rs1.next()) {
							String firstname=rs1.getString("firstname");
							String lastname=rs1.getString("lastname");
							String phonenum=rs1.getString("phone");
							String emailid=rs1.getString("email");
							String cid=rs1.getString("cid");
							boolean temp=rs1.getBoolean("templicense");
							String license1=rs1.getString("license");
							String credit=rs1.getString("creditcard");
							int cvv=rs1.getInt("cvv");
							
							System.out.println(cid+"\t\t"+firstname+" "+lastname+"\t\t\t"+phonenum+"\t\t"+emailid+"\t\t"+temp+"\t\t"+license1+"\t\t"+credit+"\t\t"+cvv);	
						}
					}
					System.out.println("Would you like to edit an existing Customer? (Y/N)");
					choice1 = input.next().toLowerCase();	
					if(choice1.compareTo("y") == 0) {
						String c_num = "";
						System.out.printf("Below is the list of all customers: " +"\n");
						String select1="SELECT * FROM cust";
						Statement stmt21 = myConn.createStatement () ;
						ResultSet rs2 = stmt21.executeQuery(select1);
						System.out.println("E ID\t\tFull Name\t\tPhone Number\t\t\tEmail ID\t\tTemp License\t\tLicense\t\tCredit Card\t\tCVV");
						while(rs2.next()) {
							String firstname=rs2.getString("firstname");
							String lastname=rs2.getString("lastname");
							String phonenum=rs2.getString("phone");
							String emailid=rs2.getString("email");
							String cid=rs2.getString("cid");
							boolean temp=rs2.getBoolean("templicense");
							String license1=rs2.getString("license");
							String credit=rs2.getString("creditcard");
							int cvv=rs2.getInt("cvv");
							System.out.println(cid+"\t\t"+firstname+" "+lastname+"\t\t\t"+phonenum+"\t\t"+emailid+"\t\t"+temp+"\t\t"+license1+"\t\t"+credit+"\t\t"+cvv);	
						}
						System.out.println();
						System.out.println("Enter the Customer ID that corresponds to the one you want to change shown above:");
						c_num = input.next();
						boolean override = false; 
						int c_numint=0;
						ArrayList <Customer> e = Customer.ret();
						int i=0;
						for(Customer f:e) {
							if(f.getID().compareTo(c_num)==0)
								c_numint=i;
							i++;
						}
						System.out.println("Would you like to remove this Customer? (Y/N)");
						choice1 = input.next().toLowerCase();
						if(choice1.compareTo("y") == 0) {
							override = true;
							Customer.removelist(c_numint);
							String delete1="DELETE from cust WHERE cid='"+c_num+"'";
							Statement stmt1=myConn.createStatement();
							stmt1.execute(delete1);
							System.out.println("Removed.. " + "\n");
							ResultSet rs3 = stmt21.executeQuery(select1);
							System.out.println("E ID\t\tFull Name\t\tPhone Number\t\t\tEmail ID\t\tTemp License\t\tLicense\t\tCredit Card\t\tCVV");
							while(rs3.next()) {
								String firstname=rs3.getString("firstname");
								String lastname=rs3.getString("lastname");
								String phonenum=rs3.getString("phone");
								String emailid=rs3.getString("email");
								String cid=rs3.getString("cid");
								boolean temp=rs3.getBoolean("templicense");
								String license1=rs3.getString("license");
								String credit=rs3.getString("creditcard");
								int cvv=rs3.getInt("cvv");
								System.out.println(cid+"\t\t"+firstname+" "+lastname+"\t\t\t"+phonenum+"\t\t"+emailid+"\t\t"+temp+"\t\t"+license1+"\t\t"+credit+"\t\t"+cvv);	
							}
						}
						boolean flag = false;
						while (flag == false && override == false) {
							System.out.println("What would you like to change: 'NAME', 'PHONE', 'EMAIL', 'ID', 'PASSWORD', 'MEMBERSHIP', 'LICENSE', 'CREDITCARD'");
							choice1 = input.next().toLowerCase();
							if(choice1.compareTo("name") == 0) {
								String newfname = "";
								String newlname = "";
								System.out.println("Enter in the new first name:");
								newfname = input.next();
								System.out.println("Enter in the new last name:");
								newlname = input.next();
								Customer.editcustomer_name(c_numint, newfname, newlname);
								String update1="UPDATE cust SET firstname='"+newfname+"',lastname='"+newlname+"' where cid='"+c_num+"'";
								Statement stmt1=myConn.createStatement();
								stmt1.executeUpdate(update1);
								String select3="SELECT * FROM cust";
								Statement stmt23 = myConn.createStatement () ;
								ResultSet rs3 = stmt23.executeQuery(select3);
								System.out.println("E ID\t\tFull Name\t\tPhone Number\t\t\tEmail ID\t\tTemp License\t\tLicense\t\tCredit Card\t\tCVV");
								while(rs3.next()) {
									String firstname=rs3.getString("firstname");
									String lastname=rs3.getString("lastname");
									String phonenum=rs3.getString("phone");
									String emailid=rs3.getString("email");
									String cid=rs3.getString("cid");
									boolean temp=rs3.getBoolean("templicense");
									String license1=rs3.getString("license");
									String credit=rs3.getString("creditcard");
									int cvv=rs3.getInt("cvv");
									System.out.println(cid+"\t\t"+firstname+" "+lastname+"\t\t\t"+phonenum+"\t\t"+emailid+"\t\t"+temp+"\t\t"+license1+"\t\t"+credit+"\t\t"+cvv);	
								}
							}
							if(choice1.compareTo("phone") == 0) {
								String phone = "";
								System.out.println("Enter in the new phone #:");
								phone = input.next();
								Customer.editcustomer_phone(c_numint, phone);
								String update1="UPDATE cust SET phone='"+phone+"' where cid='"+c_num+"'";
								Statement stmt1=myConn.createStatement();
								stmt1.executeUpdate(update1);
								String select3="SELECT * FROM cust";
								Statement stmt23 = myConn.createStatement () ;
								ResultSet rs3 = stmt23.executeQuery(select3);
								System.out.println("E ID\t\tFull Name\t\tPhone Number\t\t\tEmail ID\t\tTemp License\t\tLicense\t\tCredit Card\t\tCVV");
								while(rs3.next()) {
									String firstname=rs3.getString("firstname");
									String lastname=rs3.getString("lastname");
									String phonenum=rs3.getString("phone");
									String emailid=rs3.getString("email");
									String cid=rs3.getString("cid");
									boolean temp=rs3.getBoolean("templicense");
									String license1=rs3.getString("license");
									String credit=rs3.getString("creditcard");
									int cvv=rs3.getInt("cvv");
									System.out.println(cid+"\t\t"+firstname+" "+lastname+"\t\t\t"+phonenum+"\t\t"+emailid+"\t\t"+temp+"\t\t"+license1+"\t\t"+credit+"\t\t"+cvv);	
								}
							}
							if(choice1.compareTo("email") == 0) {
								String email = "";
								System.out.println("Enter in the new email:");
								email = input.next();
								Customer.editcustomer_email(c_numint, email);
								String update1="UPDATE cust SET email='"+email+"' where cid='"+c_num+"'";
								Statement stmt1=myConn.createStatement();
								stmt1.executeUpdate(update1);
								String select3="SELECT * FROM cust";
								Statement stmt23 = myConn.createStatement () ;
								ResultSet rs3 = stmt23.executeQuery(select3);
								System.out.println("E ID\t\tFull Name\t\tPhone Number\t\t\tEmail ID\t\tTemp License\t\tLicense\t\tCredit Card\t\tCVV");
								while(rs3.next()) {
									String firstname=rs3.getString("firstname");
									String lastname=rs3.getString("lastname");
									String phonenum=rs3.getString("phone");
									String emailid=rs3.getString("email");
									String cid=rs3.getString("cid");
									boolean temp=rs3.getBoolean("templicense");
									String license1=rs3.getString("license");
									String credit=rs3.getString("creditcard");
									int cvv=rs3.getInt("cvv");
									System.out.println(cid+"\t\t"+firstname+" "+lastname+"\t\t\t"+phonenum+"\t\t"+emailid+"\t\t"+temp+"\t\t"+license1+"\t\t"+credit+"\t\t"+cvv);	
								}
							}
							if(choice1.compareTo("id") == 0) {
								String id = "";
								System.out.println("Enter in the new ID:");
								id = input.next();
								Customer.editcustomer_ID(c_numint, id);
								String update1="UPDATE cust SET cid='"+id+"' where cid='"+c_num+"'";
								Statement stmt1=myConn.createStatement();
								stmt1.executeUpdate(update1);
								String select3="SELECT * FROM cust";
								Statement stmt23 = myConn.createStatement () ;
								ResultSet rs3 = stmt23.executeQuery(select3);
								System.out.println("E ID\t\tFull Name\t\tPhone Number\t\t\tEmail ID\t\tTemp License\t\tLicense\t\tCredit Card\t\tCVV");
								while(rs3.next()) {
									String firstname=rs3.getString("firstname");
									String lastname=rs3.getString("lastname");
									String phonenum=rs3.getString("phone");
									String emailid=rs3.getString("email");
									String cid=rs3.getString("cid");
									boolean temp=rs3.getBoolean("templicense");
									String license1=rs3.getString("license");
									String credit=rs3.getString("creditcard");
									int cvv=rs3.getInt("cvv");
									System.out.println(cid+"\t\t"+firstname+" "+lastname+"\t\t\t"+phonenum+"\t\t"+emailid+"\t\t"+temp+"\t\t"+license1+"\t\t"+credit+"\t\t"+cvv);	
								}
							}
							if(choice1.compareTo("password") == 0) {
								String pass = "";
								System.out.println("Enter in the new password:");
								pass = input.next();
								Customer.editcustomer_pass(c_numint, pass);
								String update1="UPDATE cust SET password='"+pass+"' where cid='"+c_num+"'";
								Statement stmt1=myConn.createStatement();
								stmt1.executeUpdate(update1);
								String select3="SELECT * FROM cust";
								Statement stmt23 = myConn.createStatement () ;
								ResultSet rs3 = stmt23.executeQuery(select3);
								System.out.println("E ID\t\tFull Name\t\tPhone Number\t\t\tEmail ID\t\tTemp License\t\tLicense\t\tCredit Card\t\tCVV");
								while(rs3.next()) {
									String firstname=rs3.getString("firstname");
									String lastname=rs3.getString("lastname");
									String phonenum=rs3.getString("phone");
									String emailid=rs3.getString("email");
									String cid=rs3.getString("cid");
									boolean temp=rs3.getBoolean("templicense");
									String license1=rs3.getString("license");
									String credit=rs3.getString("creditcard");
									int cvv=rs3.getInt("cvv");
									System.out.println(cid+"\t\t"+firstname+" "+lastname+"\t\t\t"+phonenum+"\t\t"+emailid+"\t\t"+temp+"\t\t"+license1+"\t\t"+credit+"\t\t"+cvv);	
								}
							}
							if(choice1.compareTo("membership") == 0) {
								boolean mem = false;
								System.out.println("Keep this customer as a member? (Y/N)");
								choice1 = input.next().toLowerCase();
								if(choice1.compareTo("y") == 0) {
									mem = true;
									Customer.editcustomer_member(c_numint, mem);
									String update1="UPDATE cust SET ismember='"+true+"' where cid='"+c_num+"'";
									Statement stmt1=myConn.createStatement();
									stmt1.executeUpdate(update1);
									String select3="SELECT * FROM cust";
									Statement stmt23 = myConn.createStatement () ;
									ResultSet rs3 = stmt23.executeQuery(select3);
									System.out.println("E ID\t\tFull Name\t\tPhone Number\t\t\tEmail ID\t\tTemp License\t\tLicense\t\tCredit Card\t\tCVV");
									while(rs3.next()) {
										String firstname=rs3.getString("firstname");
										String lastname=rs3.getString("lastname");
										String phonenum=rs3.getString("phone");
										String emailid=rs3.getString("email");
										String cid=rs3.getString("cid");
										boolean temp=rs3.getBoolean("templicense");
										String license1=rs3.getString("license");
										String credit=rs3.getString("creditcard");
										int cvv=rs3.getInt("cvv");
										System.out.println(cid+"\t\t"+firstname+" "+lastname+"\t\t\t"+phonenum+"\t\t"+emailid+"\t\t"+temp+"\t\t"+license1+"\t\t"+credit+"\t\t"+cvv);	
									}
								}
								else {
									Customer.editcustomer_member(c_numint, mem);
									String update1="UPDATE cust SET ismember='"+false+"' where cid='"+c_num+"'";
									Statement stmt1=myConn.createStatement();
									stmt1.executeUpdate(update1);
									String select3="SELECT * FROM cust";
									Statement stmt23 = myConn.createStatement () ;
									ResultSet rs3 = stmt23.executeQuery(select3);
									System.out.println("E ID\t\tFull Name\t\tPhone Number\t\t\tEmail ID\t\tTemp License\t\tLicense\t\tCredit Card\t\tCVV");
									while(rs3.next()) {
										String firstname=rs3.getString("firstname");
										String lastname=rs3.getString("lastname");
										String phonenum=rs3.getString("phone");
										String emailid=rs3.getString("email");
										String cid=rs3.getString("cid");
										boolean temp=rs3.getBoolean("templicense");
										String license1=rs3.getString("license");
										String credit=rs3.getString("creditcard");
										int cvv=rs3.getInt("cvv");
										System.out.println(cid+"\t\t"+firstname+" "+lastname+"\t\t\t"+phonenum+"\t\t"+emailid+"\t\t"+temp+"\t\t"+license1+"\t\t"+credit+"\t\t"+cvv);	
									}
									//Customer.editcustomer_member(c_numint, mem);
								}
							}
							if(choice1.compareTo("license") == 0) {
								boolean temp = false;
								String license = "";
								System.out.println("Would you like to change temporary license status? (Y/N)");
								choice1 = input.next().toLowerCase();
								if(choice1.compareTo("y") == 0) {
									System.out.println("Is your license temporary? (Y/N)");
									choice1 = input.next().toLowerCase();
									if(choice1.compareTo("y") == 0) {
										temp = true;
										Customer.editcustomer_templicense(c_numint, temp);
										String update1="UPDATE cust SET templicense='"+temp+"' where cid='"+c_num+"'";
										Statement stmt1=myConn.createStatement();
										stmt1.executeUpdate(update1);
										String select3="SELECT * FROM cust";
										Statement stmt23 = myConn.createStatement () ;
										ResultSet rs3 = stmt23.executeQuery(select3);
										System.out.println("E ID\t\tFull Name\t\tPhone Number\t\t\tEmail ID\t\tTemp License\t\tLicense\t\tCredit Card\t\tCVV");
										while(rs3.next()) {
											String firstname=rs3.getString("firstname");
											String lastname=rs3.getString("lastname");
											String phonenum=rs3.getString("phone");
											String emailid=rs3.getString("email");
											String cid=rs3.getString("cid");
											boolean temp1=rs3.getBoolean("templicense");
											String license1=rs3.getString("license");
											String credit=rs3.getString("creditcard");
											int cvv=rs3.getInt("cvv");
											System.out.println(cid+"\t\t"+firstname+" "+lastname+"\t\t\t"+phonenum+"\t\t"+emailid+"\t\t"+temp1+"\t\t"+license1+"\t\t"+credit+"\t\t"+cvv);	
										}
									}
									else
									{
										Customer.editcustomer_templicense(c_numint, temp);
										String update1="UPDATE cust SET templicense='"+temp+"' where cid='"+c_num+"'";
										Statement stmt1=myConn.createStatement();
										stmt1.executeUpdate(update1);
										String select3="SELECT * FROM cust";
										Statement stmt23 = myConn.createStatement () ;
										ResultSet rs3 = stmt23.executeQuery(select3);
										System.out.println("E ID\t\tFull Name\t\tPhone Number\t\t\tEmail ID\t\tTemp License\t\tLicense\t\tCredit Card\t\tCVV");
										while(rs3.next()) {
											String firstname=rs3.getString("firstname");
											String lastname=rs3.getString("lastname");
											String phonenum=rs3.getString("phone");
											String emailid=rs3.getString("email");
											String cid=rs3.getString("cid");
											boolean temp1=rs3.getBoolean("templicense");
											String license1=rs3.getString("license");
											String credit=rs3.getString("creditcard");
											int cvv=rs3.getInt("cvv");
											System.out.println(cid+"\t\t"+firstname+" "+lastname+"\t\t\t"+phonenum+"\t\t"+emailid+"\t\t"+temp1+"\t\t"+license1+"\t\t"+credit+"\t\t"+cvv);	
										}
									}
								}
								System.out.println("Would you like to change your license plate number? (Y/N)");
								choice1 = input.next().toLowerCase();
								if(choice1.compareTo("y") == 0) {
									System.out.println("Enter the new license plate number:");
									license = input.next();
									Customer.editcustomer_license(c_numint, license);
									
									String update1="UPDATE cust SET license='"+license+"' where cid='"+c_num+"'";
									Statement stmt1=myConn.createStatement();
									stmt1.executeUpdate(update1);
									String select3="SELECT * FROM cust";
									Statement stmt23 = myConn.createStatement () ;
									ResultSet rs3 = stmt23.executeQuery(select3);
									System.out.println("E ID\t\tFull Name\t\tPhone Number\t\t\tEmail ID\t\tTemp License\t\tLicense\t\tCredit Card\t\tCVV");
									while(rs3.next()) {
										String firstname=rs3.getString("firstname");
										String lastname=rs3.getString("lastname");
										String phonenum=rs3.getString("phone");
										String emailid=rs3.getString("email");
										String cid=rs3.getString("cid");
										boolean temp1=rs3.getBoolean("templicense");
										String license1=rs3.getString("license");
										String credit=rs3.getString("creditcard");
										int cvv=rs3.getInt("cvv");
										System.out.println(cid+"\t\t"+firstname+" "+lastname+"\t\t\t"+phonenum+"\t\t"+emailid+"\t\t"+temp1+"\t\t"+license1+"\t\t"+credit+"\t\t"+cvv);	
									}
								}
							}
							if(choice1.compareTo("creditcard") == 0) {
								String creditcard = "";
								int ccv = 0;
								System.out.println("Change Credit Card #? (Y/N)");
								choice1 = input.next().toLowerCase();
								if(choice1.compareTo("y") == 0) {
									System.out.println("Enter the New Credit Card #:");
									creditcard = input.next();
									Customer.editcustomer_creditcard(c_numint, creditcard);
									
									String update1="UPDATE cust SET creditcard='"+creditcard+"' where cid='"+c_num+"'";
									Statement stmt1=myConn.createStatement();
									stmt1.executeUpdate(update1);
									String select3="SELECT * FROM cust";
									Statement stmt23 = myConn.createStatement () ;
									ResultSet rs3 = stmt23.executeQuery(select3);
									System.out.println("E ID\t\tFull Name\t\tPhone Number\t\t\tEmail ID\t\tTemp License\t\tLicense\t\tCredit Card\t\tCVV");
									while(rs3.next()) {
										String firstname=rs3.getString("firstname");
										String lastname=rs3.getString("lastname");
										String phonenum=rs3.getString("phone");
										String emailid=rs3.getString("email");
										String cid=rs3.getString("cid");
										boolean temp=rs3.getBoolean("templicense");
										String license1=rs3.getString("license");
										String credit=rs3.getString("creditcard");
										int cvv=rs3.getInt("cvv");
										System.out.println(cid+"\t\t"+firstname+" "+lastname+"\t\t\t"+phonenum+"\t\t"+emailid+"\t\t"+temp+"\t\t"+license1+"\t\t"+credit+"\t\t"+cvv);	
									}
								}
								System.out.println("Change CCV? (Y/N)");
								choice1 = input.next().toLowerCase();
								if(choice1.compareTo("y") == 0) {
									ccv = Integer.parseInt(input.next());
									Customer.editcustomer_ccv(c_numint, ccv);
									String update1="UPDATE cust SET cvv='"+ccv+"' where cid='"+c_num+"'";
									Statement stmt1=myConn.createStatement();
									stmt1.executeUpdate(update1);
									String select3="SELECT * FROM cust";
									Statement stmt23 = myConn.createStatement () ;
									ResultSet rs3 = stmt23.executeQuery(select3);
									System.out.println("E ID\t\tFull Name\t\tPhone Number\t\t\tEmail ID\t\tTemp License\t\tLicense\t\tCredit Card\t\tCVV");
									while(rs3.next()) {
										String firstname=rs3.getString("firstname");
										String lastname=rs3.getString("lastname");
										String phonenum=rs3.getString("phone");
										String emailid=rs3.getString("email");
										String cid=rs3.getString("cid");
										boolean temp=rs3.getBoolean("templicense");
										String license1=rs3.getString("license");
										String credit=rs3.getString("creditcard");
										int cvv=rs3.getInt("cvv");
										System.out.println(cid+"\t\t"+firstname+" "+lastname+"\t\t\t"+phonenum+"\t\t"+emailid+"\t\t"+temp+"\t\t"+license1+"\t\t"+credit+"\t\t"+cvv);	
									}
								}
							}
							System.out.println("Changes Made.. " + Customer.printcurrcustomer(c_numint));
							System.out.println("Would you like to exit the editor? (Y/N)");
						    choice1 = input.next().toLowerCase();
							if(choice1.compareTo("y") == 0)
								flag = true;
						}
					}
					System.out.println("Would you like to create a reservation? (Y/N)");
					choice1 = input.next().toLowerCase();
					if(choice1.compareTo("y") == 0) {
						System.out.println("What is your reservation type: 'ONLINE' or 'DRIVE-IN'");
						choice1 = input.next().toLowerCase();
						if(choice1.compareTo("online") == 0) {
							String location = "";
							String day = "";
							String time = "";
							System.out.println("Specify the location: LOT 'A', 'B', 'C'");
							location = input.next().toLowerCase();
							System.out.println("Specify the day: 'MON', 'TUES', 'WED', 'THURS', 'FRI', 'SAT', 'SUN'");
							day = input.next().toLowerCase();
							if(day.compareTo("sat") == 0 || day.compareTo("sun") == 0) {
								System.out.println("Specify the time: '4PM', '6PM', '8PM'");
								time = input.next().toLowerCase();
								if(Parkingspots.confirmSpot(location, day, time, userID) == true) {
									System.out.println("Succesful..");
								}
								else
									System.out.println("The Reservation you want is full. Refer to our report.\n " + Parkingspots.reportParkingLotINFO());
							}
							else {
								System.out.println("Specify the time: '8AM', '10AM', '12PM'");
								time = input.next().toLowerCase();
								if(Parkingspots.confirmSpot(location, day, time, userID) == true) {
									System.out.println("Succesful.." + time + location + day);
								}
								else
									System.out.println("The Reservation you want is full. Refer to our report.\n " + Parkingspots.reportParkingLotINFO());
							}
						}
						if(choice1.compareTo("drive-in") == 0) {
							System.out.println("Here's the current availbility: " + Parkingspots.reportParkingLotINFO());
						}	
					}
					
					System.out.println("Do you want to view REPORTS? (Y/N)");
					choice1 = input.next().toLowerCase();
					if(choice1.compareTo("y") == 0) {
						System.out.println("Do you wish to view available parking spots for all lots? (Y/N)");
						choice1 = input.next().toLowerCase();
						if(choice1.compareTo("y") == 0) {
							Parkingspots.reportParkingLotINFO();
						}
						System.out.println("Do you want to view monthly revenue? (Y/N)");
						choice1 = input.next().toLowerCase();
						if(choice1.compareTo("y") == 0) {
							Parkingspots.trackMonthlyRev();
						}
						System.out.println("Do you want to view parking lot usage? (Y/N)");
						choice1 = input.next().toLowerCase();
						if(choice1.compareTo("y") == 0) {
							Parkingspots.parkingusage();
						}
						System.out.println("Do you want to view login data? (Y/N)");
						choice1 = input.next().toLowerCase();
						if(choice1.compareTo("y") == 0) {
							Customer.customerlogininfo();
							Employee.Employeelogininfo();
						}
						
					}
					System.out.println("Do you wish to exit the ADMINSTRATIVE TOOLS? (Y/N)");
					choice1 = input.next().toLowerCase();
					if(choice1.compareTo("y") == 0) {
						System.out.println(logout());
						exit = true;
					}
					if(choice1.compareTo("n") == 0) {
						exit = false;
					}
				}
			}
		else 
			System.out.println("WRONG LOGIN INFORMATION.. Exiting");
	}
		
//EMPLOYEE	
		if (userType.compareTo("employee") == 0) {
			String choice3 = "";
			boolean workdone = false;
			while(workdone == false) {
				System.out.println("Enter ID:");
				userID = input.next();
				System.out.println("ENTER PASSWORD:");
				password = input.next();
				if(Employee.VerifyLogin(userID, password) == true) {
					System.out.println(login());
					Employee.recordLogin(loggedin, userID);	
					
					System.out.println("Would you like to view your Salary? (Y/N)");
					choice3 = input.next().toLowerCase();
					if(choice3.compareTo("y") == 0) {
						String view="SELECT salary from emp where firstname='"+userID+"'";
						Statement stmt23 = myConn.createStatement () ;
						ResultSet rs3 = stmt23.executeQuery(view);
						System.out.println("Salary : "+rs3.getInt("salary"));
					//	Employee.viewSalary(userID);
					}
					System.out.println("Make Reservation? (Y/N)");
					choice3 = input.next().toLowerCase();
					if(choice3.compareTo("y") == 0) {
						System.out.println("What is the reservation type: 'ONLINE' or 'DRIVE-IN'");
						choice3 = input.next().toLowerCase();
						if(choice3.compareTo("online") == 0) {
							String location = "";
							String day = "";
							String time = "";
							System.out.println("Specify the location: LOT 'A', 'B', 'C'");
							location = input.next().toLowerCase();
							System.out.println("Specify the day: 'MON', 'TUES', 'WED', 'THURS', 'FRI', 'SAT', 'SUN'");
							day = input.next().toLowerCase();
							if(day.compareTo("sat") == 0 || day.compareTo("sun") == 0) {
								System.out.println("Specify the time: '4PM', '6PM', '8PM'");
								time = input.next().toLowerCase();
								if(Parkingspots.confirmSpot(location, day, time, userID) == true) {
									System.out.println("Succesful..");
								}
								else
									System.out.println("The Reservation the Customer wants is full. Refer to our report.\n " + Parkingspots.reportParkingLotINFO());
							}
							else {
								System.out.println("Specify the time: '8AM', '10AM', '12PM'");
								time = input.next().toLowerCase();
								if(Parkingspots.confirmSpot(location, day, time, userID) == true) {
									System.out.println("Succesful.." + time + location + day);
								}
								else
									System.out.println("The Reservation the CUstomer wants is full. Refer to our report.\n " + Parkingspots.reportParkingLotINFO());
							}
						}
						if(choice3.compareTo("drive-in") == 0) {
							System.out.println("Here's the current availbility: " + Parkingspots.reportParkingLotINFO());
						}	
					}
					System.out.println("Edit a Members information? (Y/N)");
					choice3 = input.next().toLowerCase();	
					if(choice3.compareTo("y") == 0) {
						String c_num = "";
						System.out.printf(":Above is the list of all customers: " +"\n" + Customer.listallCustomers());
						System.out.println();
						System.out.println("Enter the Customer # that corresponds to the one you want to change shown above:");
						c_num = input.next();
						int c_numint = Integer.parseInt(c_num) - 1;
						boolean override = false; 
						System.out.println("Would you like to remove this Customer? (Y/N)");
						choice3 = input.next().toLowerCase();
						if(choice3.compareTo("y") == 0) {
							override = true;
							Customer.removelist(c_numint);
							System.out.println("Removed.. " + "\n" + Customer.listallCustomers());
						}
						boolean flag = false;
						while (flag == false && override == false) {
							System.out.println("What would you like to change: 'NAME', 'PHONE', 'EMAIL', 'ID', 'PASSWORD', 'MEMBERSHIP', 'LICENSE', 'CREDITCARD'");
							choice3 = input.next().toLowerCase();
							if(choice3.compareTo("name") == 0) {
								String newfname = "";
								String newlname = "";
								System.out.println("Enter in the new first name:");
								newfname = input.next();
								System.out.println("Enter in the new last name:");
								newlname = input.next();
								Customer.editcustomer_name(c_numint, newfname, newlname);
							}
							if(choice3.compareTo("phone") == 0) {
								String phone = "";
								System.out.println("Enter in the new phone #:");
								phone = input.next();
								Customer.editcustomer_phone(c_numint, phone);
							}
							if(choice3.compareTo("email") == 0) {
								String email = "";
								System.out.println("Enter in the new email:");
								email = input.next();
								Customer.editcustomer_email(c_numint, email);
							}
							if(choice3.compareTo("id") == 0) {
								String id = "";
								System.out.println("Enter in the new ID:");
								id = input.next();
								Customer.editcustomer_ID(c_numint, id);
							}
							if(choice3.compareTo("password") == 0) {
								String pass = "";
								System.out.println("Enter in the new password:");
								pass = input.next();
								Customer.editcustomer_pass(c_numint, pass);
							}
							if(choice3.compareTo("membership") == 0) {
								boolean mem = false;
								System.out.println("Keep this customer as a member? (Y/N)");
								choice3 = input.next().toLowerCase();
								if(choice3.compareTo("y") == 0) {
									mem = true;
									Customer.editcustomer_member(c_numint, mem);
								}
								else {
									Customer.editcustomer_member(c_numint, mem);
								}
							}
							if(choice3.compareTo("license") == 0) {
								boolean temp = false;
								String license = "";
								System.out.println("Would you like to change temporary license status? (Y/N)");
								choice3 = input.next().toLowerCase();
								if(choice3.compareTo("y") == 0) {
									System.out.println("Is your license temporary? (Y/N)");
									choice3 = input.next().toLowerCase();
									if(choice3.compareTo("y") == 0) {
										temp = true;
										Customer.editcustomer_templicense(c_numint, temp);
									}
									else
										Customer.editcustomer_templicense(c_numint, temp);
								}
								System.out.println("Would you like to change your license plate number? (Y/N)");
								choice3 = input.next().toLowerCase();
								if(choice3.compareTo("y") == 0) {
									System.out.println("Enter the new license plate number:");
									license = input.next();
									Customer.editcustomer_license(c_numint, license);
								}
							}
							if(choice3.compareTo("creditcard") == 0) {
								String creditcard = "";
								int ccv = 0;
								System.out.println("Change Credit Card #? (Y/N)");
								choice3 = input.next().toLowerCase();
								if(choice3.compareTo("y") == 0) {
									System.out.println("Enter the New Credit Card #:");
									creditcard = input.next();
									Customer.editcustomer_creditcard(c_numint, creditcard);
								}
								System.out.println("Change CCV? (Y/N)");
								choice3 = input.next().toLowerCase();
								if(choice3.compareTo("y") == 0) {
									ccv = Integer.parseInt(input.next());
									Customer.editcustomer_ccv(c_numint, ccv);
								}
							}
							System.out.println("Changes Made.. " + Customer.printcurrcustomer(c_numint));
							System.out.println("Would you like to exit the editor? (Y/N)");
						    choice3 = input.next().toLowerCase();
							if(choice3.compareTo("y") == 0)
								flag = true;
						}
					}
					System.out.println("Retrive a Members information? (Y/N)");
					choice3 = input.next().toLowerCase();
					if(choice3.compareTo("y") == 0) {
						System.out.println("Enter in the Customers ID:");
						choice3 = input.next();
						Customer.ViewCustomer(choice3);
					}
					
				}
				System.out.println("Do you wish to exit the EMPLOYEE menus? (Y/N)");
				choice3 = input.next().toLowerCase();
				if(choice3.compareTo("y") == 0) {
					System.out.println(logout());
					Employee.recordLogout(loggedout, userID);
					workdone= true;
				}
			}
		}
		
//USER	
		if(userType.compareTo("user") == 0) {
			String choice2 = "";
			boolean reservation = false;
			boolean alreadyloggedin = false;
			while(reservation == false) {
			System.out.println("Are you a member? (Y/N)");
			choice2 = input.next().toLowerCase();
			if(choice2.compareTo("y") == 0) {
				System.out.println("Enter ID:");
				userID = input.next();
				System.out.println("ENTER PASSWORD:");
				password = input.next();
			
				if(Customer.VerifyLogin(userID, password) == true) {
					System.out.println(login());
					Customer.recordlogintime(loggedin, userID);	
					
					System.out.println("What is your reservation type: 'ONLINE' or 'DRIVE-IN'");
					choice2 = input.next().toLowerCase();
					if(choice2.compareTo("online") == 0) {
						String location = "";
						String day = "";
						String time = "";
						System.out.println("Specify the location: LOT 'A', 'B', 'C'");
						location = input.next().toLowerCase();
						System.out.println("Specify the day: 'MON', 'TUES', 'WED', 'THURS', 'FRI', 'SAT', 'SUN'");
						day = input.next().toLowerCase();
						if(day.compareTo("sat") == 0 || day.compareTo("sun") == 0) {
							System.out.println("Specify the time: '4PM', '6PM', '8PM'");
							time = input.next().toLowerCase();
							if(Parkingspots.confirmSpot(location, day, time, userID) == true) {
								System.out.println("Succesful..");
							}
							else
								System.out.println("The Reservation you want is full. Refer to our report.\n " + Parkingspots.reportParkingLotINFO());
						}
						else {
							System.out.println("Specify the time: '8AM', '10AM', '12PM'");
							time = input.next().toLowerCase();
							if(Parkingspots.confirmSpot(location, day, time, userID) == true) {
								System.out.println("Succesful..");
							}
							else
								System.out.println("The Reservation you want is full. Refer to our report.\n " + Parkingspots.reportParkingLotINFO());
						}
					}
					if(choice2.compareTo("drive-in") == 0) {
						System.out.println("Here's the current availbility: " + Parkingspots.reportParkingLotINFO());
					}	
					alreadyloggedin = true;
				}
				else {
					System.out.println("WRONG LOGIN INFORMATION.. Exiting");
				}
			}	
			if(alreadyloggedin != true) {
			System.out.println("Continue as guest? (Y/N)");
			choice2 = input.next().toLowerCase();
			if(choice2.compareTo("y") == 0) {
				userID = "guest";
				System.out.println(login());
				Customer.recordlogintime(loggedin, userID);
				System.out.println("What is your reservation type: 'ONLINE' or 'DRIVE-IN'");
				choice2 = input.next().toLowerCase();
				if(choice2.compareTo("online") == 0) {
					String location = "";
					String day = "";
					String time = "";
					System.out.println("Specify the location: LOT 'A', 'B', 'C'");
					location = input.next().toLowerCase();
					System.out.println("Specify the day: 'MON', 'TUES', 'WED', 'THURS', 'FRI', 'SAT', 'SUN'");
					day = input.next().toLowerCase();
					if(day.compareTo("sat") == 0 || day.compareTo("sun") == 0) {
						System.out.println("Specify the time: '4PM', '6PM', '8PM'");
						time = input.next().toLowerCase();
						if(Parkingspots.confirmSpot(location, day, time, userID) == true) {
							String creditcard = "";
							int ccv = 0;
							System.out.println("Succesful.. You'll be charged $25. Enter Credit Card Information:");
							creditcard = input.next();
							System.out.println("Finalize Payment, Enter CCV:");
							ccv = Integer.parseInt(input.next());
							Customer.addguestcreditcard(creditcard, ccv);
						}
						else
							System.out.println("The Reservation you want is full. Refer to our report.\n " + Parkingspots.reportParkingLotINFO());
 					}
					else {
						System.out.println("Specify the time: '8AM', '10AM', '12PM'");
						time = input.next().toLowerCase();
						if(Parkingspots.confirmSpot(location, day, time, userID) == true) {
							String creditcard = "";
							int ccv = 0;
							System.out.println("Succesful.. You'll be charged $25. Enter Credit Card Information:");
							creditcard = input.next();
							System.out.println("Finalize Payment, Enter CCV:");
							ccv = Integer.parseInt(input.next());
							Customer.addguestcreditcard(creditcard, ccv);
						}
						else
							System.out.println("The Reservation you want is full. Refer to our report.\n " + Parkingspots.reportParkingLotINFO());
					}
				}
				if(choice2.compareTo("drive-in") == 0) {
					System.out.println("Here's the current availbility: " + Parkingspots.reportParkingLotINFO());
				}		
				System.out.println(logout());
				Customer.recordlogintime(loggedout, userID);
		}
			else {
				System.out.println("WELCOME TO SIGN-UP MENUS");
				System.out.println("Enter your first name:");
				String fname = input.next();
				System.out.println("Enter your last name:");
				String lname = input.next();
				System.out.println("Enter your phone #:");
				String phone = input.next();
				System.out.println("Enter your full email:");
				String email = input.next();;
				System.out.println("Create your member ID:");
				String c_id = input.next();;
				System.out.println("Create your password:");
				String password = input.next();
				boolean isMember = true;
				System.out.println("Do you have a temporary license plate? (Y/N)");
				choice2 = input.next().toLowerCase();
				boolean templicense = false;
				if(choice2.compareTo("y") == 0) {
					templicense = true;
				}
				System.out.println("Enter the license plate #:");
				String license = input.next();
				System.out.println("Please enter your credit card #:");
				String creditcard = input.next();
				System.out.println("Please enter the CCV for your credit card:");
				int ccv = Integer.parseInt(input.next());
				Customer newcustomer = new Customer(fname, lname, phone, email, c_id, password, isMember, 
						                              templicense, license, creditcard, ccv, null, null);
				newcustomer.appendCustomer();
				
			}
			}
			System.out.println("Do you exit the USER menus? (Y/N)");
			choice2 = input.next().toLowerCase();
			if(choice2.compareTo("y") == 0) {
				System.out.println(logout());
				Customer.recordlogouttime(loggedout, userID);
				reservation = true;
			}
		}
	}			
		System.out.println("Do you wish to terminate? (Y/N)");
		start = input.next();
		start = start.toLowerCase();
		if(start.compareTo("y") == 0)
			run = false;
}
}
}
}
