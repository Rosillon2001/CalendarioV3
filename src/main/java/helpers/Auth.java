package helpers;

public class Auth {

	ConnectionDB DB=ConnectionDB.getInstances();
	
	public Auth() {
		
	}
	
	public boolean userExists(String username) {
		
		if(DB.getUsuario(username)!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean calendExists(String calendario) {
		
		if(DB.nameCalendar(calendario)!=null) {
			return true;
		}else{
			return false;
		}
	}
}
