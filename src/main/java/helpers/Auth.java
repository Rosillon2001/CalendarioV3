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

	public boolean calendExists(String nombre_calendario) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
