package controllers;

import java.util.Properties;

import helpers.ConnectionDB;
import propertiesReader.PropertiesReader;

public class RegisterController {
	
	ConnectionDB DB=ConnectionDB.getInstances();
	PropertiesReader PR=PropertiesReader.getInstances();
	
	
	public RegisterController (){
		
	}
	
	public String registro (String username, String pass) {
		//Properties prop=new Properties();
		
		DB.regUsuario(PR.add(), DB.userId(), username, pass);
		
		return "{'message: 'user created', 'status': 200 }";
	}
	

}
