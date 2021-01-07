
public class Reservation {
	private static boolean online;
	private static boolean drivein;
	
	public static boolean getOnline() {
		return online;
	}
	public static boolean getDrivin() {
		return drivein;
	}
//IF FALSE THEN COUNTED AS DRIVEIN
	public Reservation(boolean online) {
		Reservation.online = online;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
