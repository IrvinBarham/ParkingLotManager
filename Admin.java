import java.util.ArrayList;

public class Admin {
	static ArrayList <Admin> admins = new ArrayList <Admin>();
	private String adminUser;
	private String password;
	
	private String getID() {
		return adminUser;
	}
	private String getPass() {
		return password;
	}
	
	public Admin(String AdminUser, String password) {
		this.adminUser = AdminUser;
		this.password = password;
	}
	
	public static boolean VerifyAdminLogin(String username, String password) {
		boolean flag1 = false;
		boolean flag2 = false;
		for(int i = 0; i < admins.size(); i++) {
			if(admins.get(i).getID().compareTo(username) == 0) {
				flag1 = true;
				break;
			}
		}
		for(int x = 0; x < admins.size(); x++) {
			if(admins.get(x).getPass().compareTo(password) == 0) {
				flag2 = true;
				break;
			}
		}
		if(flag1 == true && flag2 == true)
			return true;
		else
			return false;
	}
	
	
	public static void setup() {
		//TEST CODE
		Admin admin1 = new Admin("Boss", "123");
		admins.add(admin1);
		
		
		
		
	}


}
