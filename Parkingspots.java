import java.util.ArrayList;

public class Parkingspots {
	static ArrayList <Parkingspots> parkingA = new ArrayList <Parkingspots>();
	static ArrayList <Parkingspots> parkingB = new ArrayList <Parkingspots>();
	static ArrayList <Parkingspots> parkingC = new ArrayList <Parkingspots>();
	private static double monthly_revenue;
	private static int openspots;
	private int reserved;
	//Given as %
	private double parking_usage;
	//total parking spots for EACH time slot
	private final static int TOTALSPOTS = 5;
	private final static int TOTALSPOTSTOTAL = 5*21;
	//Monthly Membership fee
	private final static double MEMBERSHIP_FEEA = 50.25;
	private final static double MEMBERSHIP_FEEB = 75.00;
	private final static double MEMBERSHIP_FEEC = 100.50;
	private String location;
	private String time;
	private String day;
	private boolean val;
	private boolean val2;

	private String getlocaton() {
		return location;
	}
	private String gettime() {
		return time;
	}
	private String getday() {
		return day;
	}
	static String [] ParkingLocations = {"LOT A", "LOT B", "LOT C"};
	static String [] DaysoftheWeek = {"MON", "TUES", "WED", "THURS", "FRI", "SAT", "SUN"};
	static String [][] Times = {{"8AM", "10AM", "12PM"},
			                    {"8AM", "10AM", "12PM"},
			                    {"8AM", "10AM", "12PM"},
			                    {"8AM", "10AM", "12PM"},
			                    {"8AM", "10AM", "12PM"},
			                    {"4PM", "6PM", "8PM"},
			                    {"4PM", "6PM", "8PM"}};
	
//PARKING USAGE FOR THE WEEK.. LIMIT IS 5.. 
	static int [][] lotA = {{0, 0, 0}, //MON
			                             {0, 0, 0}, //TUES
			                             {0, 0, 0}, //WED
			                             {0, 0, 0},//THURS
			                             {0, 0, 0}, //FRI
			                             {0, 0, 0},//SAT
			                             {0, 0, 0}};//SUN
	
	static int [][] lotB = {{0, 0, 0}, //MON
										{0, 0, 0},//TUES
										{0, 0, 0},//WED
										{0, 0, 0},//THRUS
										{0, 0, 0},//FRI
										{0, 0, 0},//SAT
										{0, 0, 0}};//SUN
	
	static int [][] lotC = {{0, 0, 0},//MON
										{0, 0, 0},//TUES
										{0, 0, 0},//WED
										{0, 0, 0},//THURS
										{0, 0, 0},//FRI
										{0, 0, 0},//SAT
										{0, 0, 0}};//SUN

	public static String reportParkingLotINFO() {
		int count = 0;
		openspots = 0;
		System.out.println("--- WEEKLY LOT A REPORT---");
		for(int row = 0; row < lotA.length; row++) {
			for(int col = 0; col < lotA[row].length; col++) {
				openspots = TOTALSPOTS - lotA[row][col];
				System.out.println(" TIME: " + Times[row][col] + " Open Spots: " + openspots);
				if(col == 2) {
					System.out.println("^^DAY^^: " + DaysoftheWeek[count]);
					count++;
				}
			}
		}
		System.out.println("--------------------------");
		count = 0;
		System.out.println("--- WEEKLY LOT B REPORT---");
		for(int row = 0; row < lotB.length; row++) {
			for(int col = 0; col < lotB[row].length; col++) {
				openspots = TOTALSPOTS - lotB[row][col];
				System.out.println(" TIME: " + Times[row][col] + ", Open Spots: " + openspots);
				if(col == 2) {
					System.out.println("^^DAY^^: " + DaysoftheWeek[count]);
					count++;
				}
			}
		}
		System.out.println("--------------------------");
		count = 0;
		System.out.println("--- WEEKLY LOT C REPORT---");
		for(int row = 0; row < lotC.length; row++) {
			for(int col = 0; col < lotC[row].length; col++) {
				openspots = TOTALSPOTS - lotC[row][col];
				System.out.println(" TIME: " + Times[row][col] + ", Open Spots: " + openspots);
				if(col == 2) {
					System.out.println("^^DAY^^: " + DaysoftheWeek[count]);
					count++;
				}
			}
		}
		return "";
	}
	public static void trackMonthlyRev() {
		for(int i = 0; i < parkingA.size(); i++) {
			if(parkingA.get(i).isVal2() == true) {
				monthly_revenue += 25;
			}
			if(parkingA.get(i).isVal2() == false) {
				monthly_revenue +=  MEMBERSHIP_FEEA;
			}
		}
		System.out.println("PARKING LOT A: $" + monthly_revenue);
		monthly_revenue = 0;
		for(int i = 0; i < parkingB.size(); i++) {
			if(parkingB.get(i).isVal2() == true) {
				monthly_revenue += 25;
			}
			if(parkingB.get(i).isVal2() == false) {
				monthly_revenue +=  MEMBERSHIP_FEEB;
			}
		}
		System.out.println("PARKING LOT B: $" + monthly_revenue);
		monthly_revenue = 0;
		for(int i = 0; i < parkingC.size(); i++) {
			if(parkingC.get(i).isVal2() == true) {
				monthly_revenue += 25;
			}
			if(parkingC.get(i).isVal2() == false) {
				monthly_revenue +=  MEMBERSHIP_FEEC;
			}
		}
		System.out.println("PARKING LOT C: $" + monthly_revenue);
		monthly_revenue = 0;
	}	

	public static void parkingusage() {
		double numMembers = 0;
		double numOnline = 0;
		double numdrivein = 0;
		for(int i = 0; i < parkingA.size(); i ++) {
			if(parkingA.get(i).isVal2() == false)
				numMembers += 1;
			if(parkingA.get(i).isVal() == false)
				numdrivein += 1;
			if(parkingA.get(i).isVal() == true)
				numOnline += 1;
		}
		numMembers = (numMembers / (double)(parkingA.size())) * 100.0;
		numOnline= (numOnline / (double)(parkingA.size())) * 100.0;
		numdrivein = (numdrivein / (double)(parkingA.size())) *100.0;
		System.out.println("LOT A: Amount of Members: " + Math.round(numMembers) + "%, Amount of Online Reservations: " + Math.round(numOnline) + "%, Amount of Drive-In Reservations: " + Math.round(numdrivein) + "%" );
		
		numMembers = 0;
		numOnline = 0;
		numdrivein = 0;
		for(int i = 0; i < parkingB.size(); i ++) {
			if(parkingB.get(i).isVal2() == false)
				numMembers += 1;
			if(parkingB.get(i).isVal() == false)
				numdrivein += 1;
			if(parkingB.get(i).isVal() == true)
				numOnline += 1;
		}
		numMembers = (numMembers / (double)(TOTALSPOTSTOTAL)) * 100.0;
		numOnline= (numOnline / (double) (TOTALSPOTSTOTAL)) * 100.0;
		numdrivein = (numdrivein / (double)(TOTALSPOTSTOTAL)) *100.0;
		System.out.println("LOT B: Amount of Members: " + Math.round(numMembers) + "%, Amount of Online Reservations: " + Math.round(numOnline) + "%, Amount of Drive-In Reservations: " + Math.round(numdrivein) + "%" );
		
		numMembers = 0;
		numOnline = 0;
		numdrivein = 0;
		for(int i = 0; i < parkingC.size(); i ++) {
			if(parkingC.get(i).isVal2() == false)
				numMembers += 1;
			if(parkingC.get(i).isVal() == false)
				numdrivein += 1;
			if(parkingC.get(i).isVal() == true)
				numOnline += 1;
		}
		numMembers = (numMembers / (double)parkingC.size())*100.0;
		numOnline= (numOnline / (double)parkingC.size())*100.0;
		numdrivein = (numdrivein / (double)parkingC.size())*100.0;
		System.out.println("LOT C: Amount of Members: " + Math.round(numMembers) + "%, Amount of Online Reservations: " + Math.round(numOnline) + "%, Amount of Drive-In Reservations: " + Math.round(numdrivein) + "%" );
	}
	public boolean isVal() {
		return val;
	}
	public boolean isVal2() {
		return val2;
	}
	
	public static boolean confirmSpot(String location, String day, String time, String c_id) {	
		boolean flag = false;
		int index = 0;
		if(time.compareTo("10am") == 0)
			index = 1;
		if(time.compareTo("12pm") == 0)
			index = 2;
		if(time.compareTo("6pm") == 0)
			index = 1;
		if(time.compareTo("8pm") == 0)
			index = 2;		
		if(location.compareTo("a") == 0) {
			if(day.compareTo("mon") == 0) {
				if(lotA[0][index] != TOTALSPOTS) {
					lotA[0][index] += 1;
					flag = true;
				}
			}
			if(day.compareTo("tues") == 0) {
				if(lotA[1][index] != TOTALSPOTS) {
					lotA[1][index] += 1;
					flag = true;
				}
			}
			if(day.compareTo("wed") == 0) {
				if(lotA[2][index] != TOTALSPOTS) {
					lotA[2][index] += 1;
					flag = true;
				}
			}
			if(day.compareTo("thurs") == 0) {
				if(lotA[3][index] != TOTALSPOTS) {
					lotA[3][index] += 1;
					flag = true;
				}
			}
			if(day.compareTo("fri") == 0) {
				if(lotA[4][index] != TOTALSPOTS) {
					lotA[4][index] += 1;
					flag = true;
				}
			}
			if(day.compareTo("sat") == 0) {
				if(lotA[5][index] != TOTALSPOTS) {
					lotA[5][index] += 1;
					flag =  true;
				}
			}
			if(day.compareTo("sun") == 0) {
				if(lotA[6][index] != TOTALSPOTS) {
					lotA[6][index] += 1;
					flag = true;
				}
			}
		}
		if(location.compareTo("b") == 0) {
			if(day.compareTo("mon") == 0) {
				if(lotB[0][index] != TOTALSPOTS) {
					lotB[0][index] += 1;
					flag = true;
				}
			}
			if(day.compareTo("tues") == 0) {
				if(lotB[1][index] != TOTALSPOTS) {
					lotB[1][index] += 1;
					flag =  true;
				}
			}
			if(day.compareTo("wed") == 0) {
				if(lotB[2][index] != TOTALSPOTS) {
					lotB[2][index] += 1;
					flag =  true;
				}
			}
			if(day.compareTo("thurs") == 0) {
				if(lotB[3][index] != TOTALSPOTS) {
					lotB[3][index] += 1;
					flag =  true;
				}
			}
			if(day.compareTo("fri") == 0) {
				if(lotB[4][index] != TOTALSPOTS) {
					lotB[4][index] += 1;
					flag =  true;
				}
			}
			if(day.compareTo("sat") == 0) {
				if(lotB[5][index] != TOTALSPOTS) {
					lotB[5][index] += 1;
					flag = true;
				}
			}
			if(day.compareTo("sun") == 0) {
				if(lotB[6][index] != TOTALSPOTS) {
					lotB[6][index] += 1;
					flag = true;
				}
			}
		}
		if(location.compareTo("c") == 0) {
			if(day.compareTo("mon") == 0) {
				if(lotC[0][index] != TOTALSPOTS) {
					lotC[0][index] += 1;
					flag = true;
				}
			}
			if(day.compareTo("tues") == 0) {
				if(lotC[1][index] != TOTALSPOTS) {
					lotC[1][index] += 1;
					flag =  true;
				}
			}
			if(day.compareTo("wed") == 0) {
				if(lotC[2][index] != TOTALSPOTS) {
					lotC[2][index] += 1;
					flag =  true;
				}
			}
			if(day.compareTo("thurs") == 0) {
				if(lotC[3][index] != TOTALSPOTS) {
					lotC[3][index] += 1;
					flag =  true;
				}
			}
			if(day.compareTo("fri") == 0) {
				if (lotC[4][index] != TOTALSPOTS) {
					lotC[4][index] += 1;
					flag =  true;
				}
			}
			if(day.compareTo("sat") == 0) {
				if(lotC[5][index] != TOTALSPOTS) {
					lotC[5][index] += 1;
					flag =  true;
				}
			}
			if(day.compareTo("sun") == 0) {
				if(lotC[6][index] != TOTALSPOTS) {
					lotC[6][index] += 1;
					flag = true;
				}
			}
		}
		boolean isOnline = true;
		if(flag == true) {
			if(c_id.compareTo("guest") == 0) {
				if(location.compareTo("a") == 0) {
					Parkingspots p1 = new Parkingspots(isOnline, flag, time, day);
					parkingA.add(p1);
				}
				if(location.compareTo("b") == 0) {
					Parkingspots p1 = new Parkingspots(isOnline, flag, time, day);
					parkingB.add(p1);
				}
				if(location.compareTo("c") == 0) {
					Parkingspots p1 = new Parkingspots(isOnline, flag, time, day);
					parkingC.add(p1);
				}
			}
		//ISMEMBER
			else {
				if(location.compareTo("a") == 0) {
					Parkingspots p1 = new Parkingspots(isOnline, false, time, day);
					parkingA.add(p1);
				}
				if(location.compareTo("b") == 0) {
					Parkingspots p1 = new Parkingspots(isOnline, false, time, day);
					parkingB.add(p1);
				}
				if(location.compareTo("c") == 0) {
					Parkingspots p1 = new Parkingspots(isOnline, false, time, day);
					parkingC.add(p1);
				}
			}
		}
		return flag;
	}
	
	public Parkingspots(boolean online, boolean isguest, String time, String day) {
		this.time = time;
		this.day = day;
		 val = online;
		 val2 = isguest;
	}
	public static void populateParking() {
//TEST CODE
		Parkingspots p1 = new Parkingspots(true, true, "8am", "mon");
		Parkingspots p2 = new Parkingspots(false, true, "10am", "mon");
		Parkingspots p3 = new Parkingspots(true, false, "12pm", "mon");		
		for(int i = 0; i < 5; i++)
			parkingA.add(p1);
		for(int i = 0; i < 5; i++) 
			parkingA.add(p2);	
		for(int i = 0; i < 5; i++) 
			parkingA.add(p3);	
		Parkingspots p4 = new Parkingspots(true, false, "8am", "tues");
		Parkingspots p5 = new Parkingspots(false, true, "10am", "tues");
		Parkingspots p6 = new Parkingspots(true, false, "12pm", "tues");	
		for(int i = 0; i < 5; i++)
			parkingA.add(p4);
		for(int i = 0; i < 5; i++) 
			parkingA.add(p5);	
		for(int i = 0; i < 5; i++) 
			parkingA.add(p6);
		Parkingspots p7 = new Parkingspots(true, false, "8am", "wed");
		Parkingspots p8 = new Parkingspots(false, true, "10am", "wed");
		Parkingspots p9 = new Parkingspots(true, false, "12pm", "wed");	
		for(int i = 0; i < 5; i++)
			parkingA.add(p7);
		for(int i = 0; i < 5; i++) 
			parkingA.add(p8);	
		for(int i = 0; i < 5; i++) 
			parkingA.add(p9);
		Parkingspots p10 = new Parkingspots(true, false, "8am", "thurs");
		Parkingspots p11 = new Parkingspots(false, false, "10am", "thurs");
		Parkingspots p12 = new Parkingspots(true, false, "12pm", "thurs");	
		for(int i = 0; i < 5; i++)
			parkingA.add(p10);
		for(int i = 0; i < 5; i++) 
			parkingA.add(p11);	
		for(int i = 0; i < 5; i++) 
			parkingA.add(p12);
		Parkingspots p13 = new Parkingspots(true, false, "8am", "fri");
		Parkingspots p14 = new Parkingspots(false, false, "10am", "fri");
		Parkingspots p15 = new Parkingspots(true, false, "12pm", "fri");	
		for(int i = 0; i < 5; i++)
			parkingA.add(p13);
		for(int i = 0; i < 5; i++) 
			parkingA.add(p14);	
		for(int i = 0; i < 5; i++) 
			parkingA.add(p15);
		Parkingspots p16 = new Parkingspots(true, false, "4pm", "sat");
		Parkingspots p17 = new Parkingspots(false, false, "6pm", "sat");
		Parkingspots p18 = new Parkingspots(true, false, "8pm", "sat");	
		for(int i = 0; i < 5; i++)
			parkingA.add(p16);
		for(int i = 0; i < 5; i++) 
			parkingA.add(p17);	
		for(int i = 0; i < 5; i++) 
			parkingA.add(p18);
		Parkingspots p19 = new Parkingspots(true, false, "4pm", "sun");
		Parkingspots p20 = new Parkingspots(false, false, "6pm", "sun");
		Parkingspots p21 = new Parkingspots(true, false, "8pm", "sun");	
		for(int i = 0; i < 5; i++)
			parkingA.add(p19);
		for(int i = 0; i < 5; i++) 
			parkingA.add(p20);	
		for(int i = 0; i < 5; i++) 
			parkingA.add(p21);
		
		int count1 = 0;
		int count2 = 0;
		int count3 = 0;
		int count4 = 0;
		int count5 = 0;
		int count6 = 0;
		int count7 = 0;
		int count8 = 0;
		int count9 = 0;
		int count10 = 0;
		int count11 = 0;
		int count12 = 0;
		int count13 = 0;
		int count14 = 0;
		int count15 = 0;
		int count16 = 0;
		int count17 = 0;
		int count18 = 0;
		int count19 = 0;
		int count20 = 0;
		int count21 = 0;
		for(int i = 0; i < parkingA.size(); i++) {
			if(parkingA.get(i).gettime().compareTo("8am") == 0 && parkingA.get(i).getday().compareTo("mon") == 0) {
				count1 += 1;
				lotA [0][0] = count1;
			}
			if(parkingA.get(i).gettime().compareTo("10am") == 0 && parkingA.get(i).getday().compareTo("mon") == 0) {
				count2 += 1;
				lotA [0][1] = count2;
			}
			if(parkingA.get(i).gettime().compareTo("12pm") == 0 && parkingA.get(i).getday().compareTo("mon") == 0) {
				count3 += 1;
				lotA [0][2] = count3;
			}
			if(parkingA.get(i).gettime().compareTo("8am") == 0 && parkingA.get(i).getday().compareTo("tues") == 0) {
				count4 += 1;
				lotA [1][0] = count4;
			}
			if(parkingA.get(i).gettime().compareTo("10am") == 0 && parkingA.get(i).getday().compareTo("tues") == 0) {
				count5 += 1;
				lotA [1][1] = count5;
			}
			if(parkingA.get(i).gettime().compareTo("12pm") == 0 && parkingA.get(i).getday().compareTo("tues") == 0) {
				count6 += 1;
				lotA [1][2] = count6;
			}
			if(parkingA.get(i).gettime().compareTo("8am") == 0 && parkingA.get(i).getday().compareTo("wed") == 0) {
				count7 += 1;
				lotA [2][0] = count7;
			}
			if(parkingA.get(i).gettime().compareTo("10am") == 0 && parkingA.get(i).getday().compareTo("wed") == 0) {
				count8 += 1;
				lotA [2][1] = count8;
			}
			if(parkingA.get(i).gettime().compareTo("12pm") == 0 && parkingA.get(i).getday().compareTo("wed") == 0) {
				count9 += 1;
				lotA [2][2] = count9;
			}
			if(parkingA.get(i).gettime().compareTo("8am") == 0 && parkingA.get(i).getday().compareTo("thurs") == 0) {
				count10 += 1;
				lotA [3][0] = count10;
			}
			if(parkingA.get(i).gettime().compareTo("10am") == 0 && parkingA.get(i).getday().compareTo("thurs") == 0) {
				count11 += 1;
				lotA [3][1] = count11;
			}
			if(parkingA.get(i).gettime().compareTo("12pm") == 0 && parkingA.get(i).getday().compareTo("thurs") == 0) {
				count12 += 1;
				lotA [3][2] = count12;
			}
			if(parkingA.get(i).gettime().compareTo("8am") == 0 && parkingA.get(i).getday().compareTo("fri") == 0) {
				count13 += 1;
				lotA [4][0] = count13;
			}
			if(parkingA.get(i).gettime().compareTo("10am") == 0 && parkingA.get(i).getday().compareTo("fri") == 0) {
				count14 += 1;
				lotA [4][1] = count14;
			}
			if(parkingA.get(i).gettime().compareTo("12pm") == 0 && parkingA.get(i).getday().compareTo("fri") == 0) {
				count15 += 1;
				lotA [4][2] = count15;
			}
			if(parkingA.get(i).gettime().compareTo("4pm") == 0 && parkingA.get(i).getday().compareTo("sat") == 0) {
				count16 += 1;
				lotA [5][0] = count16;
			}
			if(parkingA.get(i).gettime().compareTo("6pm") == 0 && parkingA.get(i).getday().compareTo("sat") == 0) {
				count17 += 1;
				lotA [5][1] = count17;
			}
			if(parkingA.get(i).gettime().compareTo("8pm") == 0 && parkingA.get(i).getday().compareTo("sat") == 0) {
				count18 += 1;
				lotA [5][2] = count18;
			}
			if(parkingA.get(i).gettime().compareTo("4pm") == 0 && parkingA.get(i).getday().compareTo("sun") == 0) {
				count19 += 1;
				lotA [6][0] = count19;
			}
			if(parkingA.get(i).gettime().compareTo("6pm") == 0 && parkingA.get(i).getday().compareTo("sun") == 0) {
				count20 += 1;
				lotA [6][1] = count20;
			}
			if(parkingA.get(i).gettime().compareTo("8pm") == 0 && parkingA.get(i).getday().compareTo("sun") == 0) {
				count21 += 1;
				lotA [6][2] = count21;
			}
	}
	for(int i = 0; i < 2; i++)
		parkingC.add(p1);
	for(int i = 0; i < 3; i++) 
		parkingC.add(p2);	
	for(int i = 0; i < 5; i++) 
		parkingC.add(p3);
	for(int i = 0; i < 3; i++)
		parkingC.add(p4);
	for(int i = 0; i < 4; i++) 
		parkingC.add(p5);	
	for(int i = 0; i < 4; i++) 
		parkingC.add(p6);		
	for(int i = 0; i < 2; i++)
		parkingC.add(p7);
	for(int i = 0; i < 3; i++) 
		parkingC.add(p8);	
	for(int i = 0; i < 5; i++) 
		parkingC.add(p9);
	for(int i = 0; i < 4; i++)
		parkingC.add(p10);
	for(int i = 0; i < 3; i++) 
		parkingC.add(p11);	
	for(int i = 0; i < 5; i++) 
		parkingC.add(p12);		
	for(int i = 0; i < 4; i++)
		parkingC.add(p13);
	for(int i = 0; i < 2; i++) 
		parkingC.add(p14);	
	for(int i = 0; i < 3; i++) 
		parkingC.add(p15);
	for(int i = 0; i < 2; i++)
		parkingC.add(p16);
	for(int i = 0; i < 4; i++) 
		parkingC.add(p17);	
	for(int i = 0; i < 5; i++) 
		parkingC.add(p18);
	for(int i = 0; i < 2; i++)
		parkingC.add(p19);
	for(int i = 0; i < 3; i++) 
		parkingC.add(p20);	
	for(int i = 0; i < 2; i++) 
		parkingC.add(p21);
	
	count1 = 0;
	count2 = 0;
	count3 = 0;
	count4 = 0;
	count5 = 0;
	count6 = 0;
	count7 = 0;
	count8 = 0;
	count9 = 0;
	count10 = 0;
	count11 = 0;
	count12 = 0;
	count13 = 0;
	count14 = 0;
	count15 = 0;
	count16 = 0;
	count17 = 0;
	count18 = 0;
	count19 = 0;
	count20 = 0;
	count21 = 0;
	for(int i = 0; i < parkingC.size(); i++) {
		if(parkingC.get(i).gettime().compareTo("8am") == 0 && parkingC.get(i).getday().compareTo("mon") == 0) {
			count1 += 1;
			lotC [0][0] = count1;
		}
		if(parkingC.get(i).gettime().compareTo("10am") == 0 && parkingC.get(i).getday().compareTo("mon") == 0) {
			count2 += 1;
			lotC [0][1] = count2;
		}
		if(parkingC.get(i).gettime().compareTo("12pm") == 0 && parkingC.get(i).getday().compareTo("mon") == 0) {
			count3 += 1;
			lotC [0][2] = count3;
		}
		if(parkingC.get(i).gettime().compareTo("8am") == 0 && parkingC.get(i).getday().compareTo("tues") == 0) {
			count4 += 1;
			lotC [1][0] = count4;
		}
		if(parkingC.get(i).gettime().compareTo("10am") == 0 && parkingC.get(i).getday().compareTo("tues") == 0) {
			count5 += 1;
			lotC [1][1] = count5;
		}
		if(parkingC.get(i).gettime().compareTo("12pm") == 0 && parkingC.get(i).getday().compareTo("tues") == 0) {
			count6 += 1;
			lotC [1][2] = count6;
		}
		if(parkingC.get(i).gettime().compareTo("8am") == 0 && parkingC.get(i).getday().compareTo("wed") == 0) {
			count7 += 1;
			lotC [2][0] = count7;
		}
		if(parkingC.get(i).gettime().compareTo("10am") == 0 && parkingC.get(i).getday().compareTo("wed") == 0) {
			count8 += 1;
			lotC [2][1] = count8;
		}
		if(parkingC.get(i).gettime().compareTo("12pm") == 0 && parkingC.get(i).getday().compareTo("wed") == 0) {
			count9 += 1;
			lotC [2][2] = count9;
		}
		if(parkingC.get(i).gettime().compareTo("8am") == 0 && parkingC.get(i).getday().compareTo("thurs") == 0) {
			count10 += 1;
			lotC [3][0] = count10;
		}
		if(parkingC.get(i).gettime().compareTo("10am") == 0 && parkingC.get(i).getday().compareTo("thurs") == 0) {
			count11 += 1;
			lotC [3][1] = count11;
		}
		if(parkingC.get(i).gettime().compareTo("12pm") == 0 && parkingC.get(i).getday().compareTo("thurs") == 0) {
			count12 += 1;
			lotC [3][2] = count12;
		}
		if(parkingC.get(i).gettime().compareTo("8am") == 0 && parkingC.get(i).getday().compareTo("fri") == 0) {
			count13 += 1;
			lotC [4][0] = count13;
		}
		if(parkingC.get(i).gettime().compareTo("10am") == 0 && parkingC.get(i).getday().compareTo("fri") == 0) {
			count14 += 1;
			lotC [4][1] = count14;
		}
		if(parkingC.get(i).gettime().compareTo("12pm") == 0 && parkingC.get(i).getday().compareTo("fri") == 0) {
			count15 += 1;
			lotC [4][2] = count15;
		}
		if(parkingC.get(i).gettime().compareTo("4pm") == 0 && parkingC.get(i).getday().compareTo("sat") == 0) {
			count16 += 1;
			lotC [5][0] = count16;
		}
		if(parkingC.get(i).gettime().compareTo("6pm") == 0 && parkingC.get(i).getday().compareTo("sat") == 0) {
			count17 += 1;
			lotC [5][1] = count17;
		}
		if(parkingC.get(i).gettime().compareTo("8pm") == 0 && parkingC.get(i).getday().compareTo("sat") == 0) {
			count18 += 1;
			lotC [5][2] = count18;
		}
		if(parkingC.get(i).gettime().compareTo("4pm") == 0 && parkingC.get(i).getday().compareTo("sun") == 0) {
			count19 += 1;
			lotC [6][0] = count19;
		}
		if(parkingC.get(i).gettime().compareTo("6pm") == 0 && parkingC.get(i).getday().compareTo("sun") == 0) {
			count20 += 1;
			lotC [6][1] = count20;
		}
		if(parkingC.get(i).gettime().compareTo("8pm") == 0 && parkingC.get(i).getday().compareTo("sun") == 0) {
			count21 += 1;
			lotC [6][2] = count21;
		}
}
	}
}
