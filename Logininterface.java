import java.util.Scanner;
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
	
	public static void main(String[] args) {
		Admin.setup();
		Employee.populate();
		Customer.createcustomers();
		Parkingspots.populateParking();
		
		boolean run = false;
		String start = "";
		Scanner input = new Scanner(System.in);
		System.out.println("RUN? (Y/N)");
		start = input.nextLine().toLowerCase();
	if(start.compareTo("y") == 0) {
		run = true;
		while (run == true) {
		System.out.println("Greetings! Please type in your USER type as: 'admin', 'employee', or 'user'.");
		String userType = input.nextLine().toLowerCase();
//ADMIN
		if (userType.compareTo("admin") == 0) {
			String choice1 = "";
			System.out.println("ENTER USERNAME:");
			choice1 = input.nextLine();
			userID = choice1;
			System.out.println("ENTER PASSWORD:");
			choice1 = input.nextLine();
			password = choice1;
			
			boolean loginCheck = false;
			if(Admin.VerifyAdminLogin(userID, password) == true) {
				loginCheck = true;
				System.out.println(login());	
				boolean exit = false;
				while (exit == false) {
					System.out.println("Would you like to add a new Employee? (Y/N)");
					choice1 = input.nextLine().toLowerCase();	
					if(choice1.compareTo("y") == 0) {
						String fname = "";
						String lname = "";
						String e_SSN = "";
						String e_id = "";
						String password = "";
						String salary = "";
						System.out.println("Enter the Employee's first name:");
						fname = input.nextLine();
						System.out.println("Enter the Employee's last name:");
						lname = input.nextLine();
						System.out.println("Enter the Employee's SSN:");
						e_SSN = input.nextLine();
						System.out.println("Create Employee's sign in ID:");
						e_id = input.nextLine();
						System.out.println("Create Employee's password:");
						password = input.nextLine();
						System.out.println("Set Employee's salary:");
						salary = input.nextLine();
						int SSN = Integer.parseInt(e_SSN);
						double sal = Double.parseDouble(salary);
						
						Employee newemployee = new Employee(fname, lname, SSN, e_id, password, sal, null, null);
						newemployee.appendlist();
						System.out.printf(":Above is the list of all employees: " +"\n" + Employee.listofEmployees());
					}
					System.out.println("Would you like to edit an existing Employee? (Y/N)");
					choice1 = input.nextLine().toLowerCase();
					if(choice1.compareTo("y") == 0) {
						String e_num = "";
						System.out.printf(":Above is the list of all employees: " +"\n" + Employee.listofEmployees());
						System.out.println();
						System.out.println("Enter the Employee # that corresponds to the one you want to change shown above:");
						e_num = input.nextLine();
						int e_numint = Integer.parseInt(e_num) - 1;
						boolean override = false; 
						System.out.println("Would you like to remove this employee? (Y/N)");
						choice1 = input.nextLine().toLowerCase();
						if(choice1.compareTo("y") == 0) {
							override = true;
							Employee.removelist(e_numint);
							System.out.println("Removed.. " + "\n" + Employee.listofEmployees());
						}
						boolean flag = false;
						while (flag == false && override == false) {
							System.out.println("What would you like to change: 'NAME', 'SSN', 'ID', 'PASSWORD', 'SALARY'?:");
							choice1 = input.nextLine().toLowerCase();
							if(choice1.compareTo("name") == 0) {
								String newfname = "";
								String newlname = "";
								System.out.println("Enter in the new first name:");
								newfname = input.nextLine();
								System.out.println("Enter in the new last name:");
								newlname = input.nextLine();
								Employee.editdata_name(e_numint, newfname, newlname);
							}
							if(choice1.compareTo("ssn") == 0) {
								String newSSN = "";
								System.out.println("Enter in the new SSN:");
								newSSN = input.nextLine();
								int new_SSN = Integer.parseInt(newSSN);
								Employee.editdata_SSN(e_numint, new_SSN);
							}
							if(choice1.compareTo("id") == 0) {
								String newid = "";
								System.out.println("Enter in the new ID:");
								newid = input.nextLine();
								Employee.editdata_ID(e_numint, newid);
							}
							if(choice1.compareTo("password") == 0) {
								String newpassword = "";
								System.out.println("Enter in the new password:");
								newpassword = input.nextLine();
								Employee.editdata_pass(e_numint, newpassword);
							}
							if(choice1.compareTo("salary") == 0) {
								String newsalary = "";
								System.out.println("Enter in the new salary:");
								newsalary = input.nextLine();
								double newsal = Double.parseDouble(newsalary);
								Employee.editdata_salary(e_numint, newsal);
							}
							System.out.println("Changes Made.. " + Employee.printcurremployee(e_numint));
							System.out.println("Would you like to exit the editor? (Y/N)");
							choice1 = input.nextLine().toLowerCase();
							if(choice1.compareTo("y") == 0)
								flag = true;
						}
					}
					System.out.println("Would you like to add a new Member? (Y/N)");
					choice1 = input.nextLine().toLowerCase();	
					if(choice1.compareTo("y") == 0) {
						System.out.println("Enter the first name:");
						String fname = input.nextLine();
						System.out.println("Enter the last name:");
						String lname = input.nextLine();
						System.out.println("Enter the phone #:");
						String phone = input.nextLine();
						System.out.println("Enter the full email:");
						String email = input.nextLine();;
						System.out.println("Create the members ID:");
						String c_id = input.nextLine();;
						System.out.println("Create the members password:");
						String password = input.nextLine();
						boolean isMember = true;
						System.out.println("Do you have a temporary license plate? (Y/N)");
						choice1 = input.nextLine().toLowerCase();
						boolean templicense = false;
						if(choice1.compareTo("y") == 0) {
							templicense = true;
						}
						System.out.println("Enter the license plate #:");
						String license = input.nextLine();
						System.out.println("Please enter credit card #:");
						String creditcard = input.nextLine();
						System.out.println("Please enter the CCV for the credit card:");
						int ccv = Integer.parseInt(input.nextLine());
						Customer newcustomer = new Customer(fname, lname, phone, email, c_id, password, isMember, 
								                              templicense, license, creditcard, ccv, null, null);
						newcustomer.appendCustomer();
						System.out.printf(":Above is the list of all customers: " + "\n" + Customer.listallCustomers());
					}
					System.out.println("Would you like to edit an existing Customer? (Y/N)");
					choice1 = input.nextLine().toLowerCase();	
					if(choice1.compareTo("y") == 0) {
						String c_num = "";
						System.out.printf(":Above is the list of all customers: " +"\n" + Customer.listallCustomers());
						System.out.println();
						System.out.println("Enter the Customer # that corresponds to the one you want to change shown above:");
						c_num = input.nextLine();
						int c_numint = Integer.parseInt(c_num) - 1;
						boolean override = false; 
						System.out.println("Would you like to remove this Customer? (Y/N)");
						choice1 = input.nextLine().toLowerCase();
						if(choice1.compareTo("y") == 0) {
							override = true;
							Customer.removelist(c_numint);
							System.out.println("Removed.. " + "\n" + Customer.listallCustomers());
						}
						boolean flag = false;
						while (flag == false && override == false) {
							System.out.println("What would you like to change: 'NAME', 'PHONE', 'EMAIL', 'ID', 'PASSWORD', 'MEMBERSHIP', 'LICENSE', 'CREDITCARD'");
							choice1 = input.nextLine().toLowerCase();
							if(choice1.compareTo("name") == 0) {
								String newfname = "";
								String newlname = "";
								System.out.println("Enter in the new first name:");
								newfname = input.nextLine();
								System.out.println("Enter in the new last name:");
								newlname = input.nextLine();
								Customer.editcustomer_name(c_numint, newfname, newlname);
							}
							if(choice1.compareTo("phone") == 0) {
								String phone = "";
								System.out.println("Enter in the new phone #:");
								phone = input.nextLine();
								Customer.editcustomer_phone(c_numint, phone);
							}
							if(choice1.compareTo("email") == 0) {
								String email = "";
								System.out.println("Enter in the new email:");
								email = input.nextLine();
								Customer.editcustomer_email(c_numint, email);
							}
							if(choice1.compareTo("id") == 0) {
								String id = "";
								System.out.println("Enter in the new ID:");
								id = input.nextLine();
								Customer.editcustomer_ID(c_numint, id);
							}
							if(choice1.compareTo("password") == 0) {
								String pass = "";
								System.out.println("Enter in the new password:");
								pass = input.nextLine();
								Customer.editcustomer_pass(c_numint, pass);
							}
							if(choice1.compareTo("membership") == 0) {
								boolean mem = false;
								System.out.println("Keep this customer as a member? (Y/N)");
								choice1 = input.nextLine().toLowerCase();
								if(choice1.compareTo("y") == 0) {
									mem = true;
									Customer.editcustomer_member(c_numint, mem);
								}
								else {
									Customer.editcustomer_member(c_numint, mem);
								}
							}
							if(choice1.compareTo("license") == 0) {
								boolean temp = false;
								String license = "";
								System.out.println("Would you like to change temporary license status? (Y/N)");
								choice1 = input.nextLine().toLowerCase();
								if(choice1.compareTo("y") == 0) {
									System.out.println("Is your license temporary? (Y/N)");
									choice1 = input.nextLine().toLowerCase();
									if(choice1.compareTo("y") == 0) {
										temp = true;
										Customer.editcustomer_templicense(c_numint, temp);
									}
									else
										Customer.editcustomer_templicense(c_numint, temp);
								}
								System.out.println("Would you like to change your license plate number? (Y/N)");
								choice1 = input.nextLine().toLowerCase();
								if(choice1.compareTo("y") == 0) {
									System.out.println("Enter the new license plate number:");
									license = input.nextLine();
									Customer.editcustomer_license(c_numint, license);
								}
							}
							if(choice1.compareTo("creditcard") == 0) {
								String creditcard = "";
								int ccv = 0;
								System.out.println("Change Credit Card #? (Y/N)");
								choice1 = input.nextLine().toLowerCase();
								if(choice1.compareTo("y") == 0) {
									System.out.println("Enter the New Credit Card #:");
									creditcard = input.nextLine();
									Customer.editcustomer_creditcard(c_numint, creditcard);
								}
								System.out.println("Change CCV? (Y/N)");
								choice1 = input.nextLine().toLowerCase();
								if(choice1.compareTo("y") == 0) {
									ccv = Integer.parseInt(input.nextLine());
									Customer.editcustomer_ccv(c_numint, ccv);
								}
							}
							System.out.println("Changes Made.. " + Customer.printcurrcustomer(c_numint));
							System.out.println("Would you like to exit the editor? (Y/N)");
						    choice1 = input.nextLine().toLowerCase();
							if(choice1.compareTo("y") == 0)
								flag = true;
						}
					}
					System.out.println("Would you like to create a reservation? (Y/N)");
					choice1 = input.nextLine().toLowerCase();
					if(choice1.compareTo("y") == 0) {
						System.out.println("What is your reservation type: 'ONLINE' or 'DRIVE-IN'");
						choice1 = input.nextLine().toLowerCase();
						if(choice1.compareTo("online") == 0) {
							String location = "";
							String day = "";
							String time = "";
							System.out.println("Specify the location: LOT 'A', 'B', 'C'");
							location = input.nextLine().toLowerCase();
							System.out.println("Specify the day: 'MON', 'TUES', 'WED', 'THURS', 'FRI', 'SAT', 'SUN'");
							day = input.nextLine().toLowerCase();
							if(day.compareTo("sat") == 0 || day.compareTo("sun") == 0) {
								System.out.println("Specify the time: '4PM', '6PM', '8PM'");
								time = input.nextLine().toLowerCase();
								if(Parkingspots.confirmSpot(location, day, time, userID) == true) {
									System.out.println("Succesful..");
								}
								else
									System.out.println("The Reservation you want is full. Refer to our report.\n " + Parkingspots.reportParkingLotINFO());
							}
							else {
								System.out.println("Specify the time: '8AM', '10AM', '12PM'");
								time = input.nextLine().toLowerCase();
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
					choice1 = input.nextLine().toLowerCase();
					if(choice1.compareTo("y") == 0) {
						System.out.println("Do you wish to view available parking spots for all lots? (Y/N)");
						choice1 = input.nextLine().toLowerCase();
						if(choice1.compareTo("y") == 0) {
							Parkingspots.reportParkingLotINFO();
						}
						System.out.println("Do you want to view monthly revenue? (Y/N)");
						choice1 = input.nextLine().toLowerCase();
						if(choice1.compareTo("y") == 0) {
							Parkingspots.trackMonthlyRev();
						}
						System.out.println("Do you want to view parking lot usage? (Y/N)");
						choice1 = input.nextLine().toLowerCase();
						if(choice1.compareTo("y") == 0) {
							Parkingspots.parkingusage();
						}
						System.out.println("Do you want to view login data? (Y/N)");
						choice1 = input.nextLine().toLowerCase();
						if(choice1.compareTo("y") == 0) {
							Customer.customerlogininfo();
							Employee.Employeelogininfo();
						}
						
					}
					System.out.println("Do you wish to exit the ADMINSTRATIVE TOOLS? (Y/N)");
					choice1 = input.nextLine().toLowerCase();
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
				userID = input.nextLine();
				System.out.println("ENTER PASSWORD:");
				password = input.nextLine();
				if(Employee.VerifyLogin(userID, password) == true) {
					System.out.println(login());
					Employee.recordLogin(loggedin, userID);	
					
					System.out.println("Would you like to view your Salary? (Y/N)");
					choice3 = input.nextLine().toLowerCase();
					if(choice3.compareTo("y") == 0) {
						Employee.viewSalary(userID);
					}
					System.out.println("Make Reservation? (Y/N)");
					choice3 = input.nextLine().toLowerCase();
					if(choice3.compareTo("y") == 0) {
						System.out.println("What is the reservation type: 'ONLINE' or 'DRIVE-IN'");
						choice3 = input.nextLine().toLowerCase();
						if(choice3.compareTo("online") == 0) {
							String location = "";
							String day = "";
							String time = "";
							System.out.println("Specify the location: LOT 'A', 'B', 'C'");
							location = input.nextLine().toLowerCase();
							System.out.println("Specify the day: 'MON', 'TUES', 'WED', 'THURS', 'FRI', 'SAT', 'SUN'");
							day = input.nextLine().toLowerCase();
							if(day.compareTo("sat") == 0 || day.compareTo("sun") == 0) {
								System.out.println("Specify the time: '4PM', '6PM', '8PM'");
								time = input.nextLine().toLowerCase();
								if(Parkingspots.confirmSpot(location, day, time, userID) == true) {
									System.out.println("Succesful..");
								}
								else
									System.out.println("The Reservation the Customer wants is full. Refer to our report.\n " + Parkingspots.reportParkingLotINFO());
							}
							else {
								System.out.println("Specify the time: '8AM', '10AM', '12PM'");
								time = input.nextLine().toLowerCase();
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
					choice3 = input.nextLine().toLowerCase();	
					if(choice3.compareTo("y") == 0) {
						String c_num = "";
						System.out.printf(":Above is the list of all customers: " +"\n" + Customer.listallCustomers());
						System.out.println();
						System.out.println("Enter the Customer # that corresponds to the one you want to change shown above:");
						c_num = input.nextLine();
						int c_numint = Integer.parseInt(c_num) - 1;
						boolean override = false; 
						System.out.println("Would you like to remove this Customer? (Y/N)");
						choice3 = input.nextLine().toLowerCase();
						if(choice3.compareTo("y") == 0) {
							override = true;
							Customer.removelist(c_numint);
							System.out.println("Removed.. " + "\n" + Customer.listallCustomers());
						}
						boolean flag = false;
						while (flag == false && override == false) {
							System.out.println("What would you like to change: 'NAME', 'PHONE', 'EMAIL', 'ID', 'PASSWORD', 'MEMBERSHIP', 'LICENSE', 'CREDITCARD'");
							choice3 = input.nextLine().toLowerCase();
							if(choice3.compareTo("name") == 0) {
								String newfname = "";
								String newlname = "";
								System.out.println("Enter in the new first name:");
								newfname = input.nextLine();
								System.out.println("Enter in the new last name:");
								newlname = input.nextLine();
								Customer.editcustomer_name(c_numint, newfname, newlname);
							}
							if(choice3.compareTo("phone") == 0) {
								String phone = "";
								System.out.println("Enter in the new phone #:");
								phone = input.nextLine();
								Customer.editcustomer_phone(c_numint, phone);
							}
							if(choice3.compareTo("email") == 0) {
								String email = "";
								System.out.println("Enter in the new email:");
								email = input.nextLine();
								Customer.editcustomer_email(c_numint, email);
							}
							if(choice3.compareTo("id") == 0) {
								String id = "";
								System.out.println("Enter in the new ID:");
								id = input.nextLine();
								Customer.editcustomer_ID(c_numint, id);
							}
							if(choice3.compareTo("password") == 0) {
								String pass = "";
								System.out.println("Enter in the new password:");
								pass = input.nextLine();
								Customer.editcustomer_pass(c_numint, pass);
							}
							if(choice3.compareTo("membership") == 0) {
								boolean mem = false;
								System.out.println("Keep this customer as a member? (Y/N)");
								choice3 = input.nextLine().toLowerCase();
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
								choice3 = input.nextLine().toLowerCase();
								if(choice3.compareTo("y") == 0) {
									System.out.println("Is your license temporary? (Y/N)");
									choice3 = input.nextLine().toLowerCase();
									if(choice3.compareTo("y") == 0) {
										temp = true;
										Customer.editcustomer_templicense(c_numint, temp);
									}
									else
										Customer.editcustomer_templicense(c_numint, temp);
								}
								System.out.println("Would you like to change your license plate number? (Y/N)");
								choice3 = input.nextLine().toLowerCase();
								if(choice3.compareTo("y") == 0) {
									System.out.println("Enter the new license plate number:");
									license = input.nextLine();
									Customer.editcustomer_license(c_numint, license);
								}
							}
							if(choice3.compareTo("creditcard") == 0) {
								String creditcard = "";
								int ccv = 0;
								System.out.println("Change Credit Card #? (Y/N)");
								choice3 = input.nextLine().toLowerCase();
								if(choice3.compareTo("y") == 0) {
									System.out.println("Enter the New Credit Card #:");
									creditcard = input.nextLine();
									Customer.editcustomer_creditcard(c_numint, creditcard);
								}
								System.out.println("Change CCV? (Y/N)");
								choice3 = input.nextLine().toLowerCase();
								if(choice3.compareTo("y") == 0) {
									ccv = Integer.parseInt(input.nextLine());
									Customer.editcustomer_ccv(c_numint, ccv);
								}
							}
							System.out.println("Changes Made.. " + Customer.printcurrcustomer(c_numint));
							System.out.println("Would you like to exit the editor? (Y/N)");
						    choice3 = input.nextLine().toLowerCase();
							if(choice3.compareTo("y") == 0)
								flag = true;
						}
					}
					System.out.println("Retrive a Members information? (Y/N)");
					choice3 = input.nextLine().toLowerCase();
					if(choice3.compareTo("y") == 0) {
						System.out.println("Enter in the Customers ID:");
						choice3 = input.nextLine();
						Customer.ViewCustomer(choice3);
					}
					
				}
				System.out.println("Do you wish to exit the EMPLOYEE menus? (Y/N)");
				choice3 = input.nextLine().toLowerCase();
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
			choice2 = input.nextLine().toLowerCase();
			if(choice2.compareTo("y") == 0) {
				System.out.println("Enter ID:");
				userID = input.nextLine();
				System.out.println("ENTER PASSWORD:");
				password = input.nextLine();
			
				if(Customer.VerifyLogin(userID, password) == true) {
					System.out.println(login());
					Customer.recordlogintime(loggedin, userID);	
					
					System.out.println("What is your reservation type: 'ONLINE' or 'DRIVE-IN'");
					choice2 = input.nextLine().toLowerCase();
					if(choice2.compareTo("online") == 0) {
						String location = "";
						String day = "";
						String time = "";
						System.out.println("Specify the location: LOT 'A', 'B', 'C'");
						location = input.nextLine().toLowerCase();
						System.out.println("Specify the day: 'MON', 'TUES', 'WED', 'THURS', 'FRI', 'SAT', 'SUN'");
						day = input.nextLine().toLowerCase();
						if(day.compareTo("sat") == 0 || day.compareTo("sun") == 0) {
							System.out.println("Specify the time: '4PM', '6PM', '8PM'");
							time = input.nextLine().toLowerCase();
							if(Parkingspots.confirmSpot(location, day, time, userID) == true) {
								System.out.println("Succesful..");
							}
							else
								System.out.println("The Reservation you want is full. Refer to our report.\n " + Parkingspots.reportParkingLotINFO());
						}
						else {
							System.out.println("Specify the time: '8AM', '10AM', '12PM'");
							time = input.nextLine().toLowerCase();
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
			choice2 = input.nextLine().toLowerCase();
			if(choice2.compareTo("y") == 0) {
				userID = "guest";
				System.out.println(login());
				Customer.recordlogintime(loggedin, userID);
				System.out.println("What is your reservation type: 'ONLINE' or 'DRIVE-IN'");
				choice2 = input.nextLine().toLowerCase();
				if(choice2.compareTo("online") == 0) {
					String location = "";
					String day = "";
					String time = "";
					System.out.println("Specify the location: LOT 'A', 'B', 'C'");
					location = input.nextLine().toLowerCase();
					System.out.println("Specify the day: 'MON', 'TUES', 'WED', 'THURS', 'FRI', 'SAT', 'SUN'");
					day = input.nextLine().toLowerCase();
					if(day.compareTo("sat") == 0 || day.compareTo("sun") == 0) {
						System.out.println("Specify the time: '4PM', '6PM', '8PM'");
						time = input.nextLine().toLowerCase();
						if(Parkingspots.confirmSpot(location, day, time, userID) == true) {
							String creditcard = "";
							int ccv = 0;
							System.out.println("Succesful.. You'll be charged $25. Enter Credit Card Information:");
							creditcard = input.nextLine();
							System.out.println("Finalize Payment, Enter CCV:");
							ccv = Integer.parseInt(input.nextLine());
							Customer.addguestcreditcard(creditcard, ccv);
						}
						else
							System.out.println("The Reservation you want is full. Refer to our report.\n " + Parkingspots.reportParkingLotINFO());
 					}
					else {
						System.out.println("Specify the time: '8AM', '10AM', '12PM'");
						time = input.nextLine().toLowerCase();
						if(Parkingspots.confirmSpot(location, day, time, userID) == true) {
							String creditcard = "";
							int ccv = 0;
							System.out.println("Succesful.. You'll be charged $25. Enter Credit Card Information:");
							creditcard = input.nextLine();
							System.out.println("Finalize Payment, Enter CCV:");
							ccv = Integer.parseInt(input.nextLine());
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
				String fname = input.nextLine();
				System.out.println("Enter your last name:");
				String lname = input.nextLine();
				System.out.println("Enter your phone #:");
				String phone = input.nextLine();
				System.out.println("Enter your full email:");
				String email = input.nextLine();;
				System.out.println("Create your member ID:");
				String c_id = input.nextLine();;
				System.out.println("Create your password:");
				String password = input.nextLine();
				boolean isMember = true;
				System.out.println("Do you have a temporary license plate? (Y/N)");
				choice2 = input.nextLine().toLowerCase();
				boolean templicense = false;
				if(choice2.compareTo("y") == 0) {
					templicense = true;
				}
				System.out.println("Enter the license plate #:");
				String license = input.nextLine();
				System.out.println("Please enter your credit card #:");
				String creditcard = input.nextLine();
				System.out.println("Please enter the CCV for your credit card:");
				int ccv = Integer.parseInt(input.nextLine());
				Customer newcustomer = new Customer(fname, lname, phone, email, c_id, password, isMember, 
						                              templicense, license, creditcard, ccv, null, null);
				newcustomer.appendCustomer();
				
			}
			}
			System.out.println("Do you exit the USER menus? (Y/N)");
			choice2 = input.nextLine().toLowerCase();
			if(choice2.compareTo("y") == 0) {
				System.out.println(logout());
				Customer.recordlogouttime(loggedout, userID);
				reservation = true;
			}
		}
	}			
		System.out.println("Do you wish to terminate? (Y/N)");
		start = input.nextLine();
		start = start.toLowerCase();
		if(start.compareTo("y") == 0)
			run = false;
}
}
}
}
