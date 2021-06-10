package controllers;

import helpers.ConnectionDB;
import propertiesReader.PropertiesReader;

public class LoginController {

	ConnectionDB DB=ConnectionDB.getInstances();
	PropertiesReader PR=PropertiesReader.getInstances();
	
	public LoginController() {
		
	}
	
		public boolean Login(String username, String pass) {
			
			
			String usuario=DB.getUsuario(username);
			String clave=DB.getClave(username);
			
			//System.out.println("Username: "+username+"-"+usuario+" Pass: "+pass+"-"+clave);
			
			if(username.equals(usuario) && pass.equals(clave)) {
				return true;
			}else {
				return false;
			}
			
			
		}
}
