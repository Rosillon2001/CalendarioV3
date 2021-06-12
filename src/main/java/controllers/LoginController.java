package controllers;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import helpers.ConnectionDB;
import propertiesReader.PropertiesReader;

public class LoginController {

	ConnectionDB DB=ConnectionDB.getInstances();
	PropertiesReader PR=PropertiesReader.getInstances();
	
	public LoginController() {
		
	}
	
		public boolean AuthCheck(String username, String pass) {
			
			
			String usuario=DB.getUsuario(username);
			String clave=DB.getClave(username);
			
			//System.out.println("Username: "+username+"-"+usuario+" Pass: "+pass+"-"+clave);
			
			if(username.equals(usuario) && pass.equals(clave)) {
				return true;
			}else {
				return false;
			}
		}
		
		public boolean Login(HttpServletRequest request, String username, String password) throws SQLException {
			
			if(AuthCheck(username, password)==true) {
			HttpSession HSession=request.getSession();
			HSession.setAttribute("Usuario", username);
			System.out.println(HSession.getAttribute("Usuario"));
			return true;
			}else {
				return false;
			}
		}
}
