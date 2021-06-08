package controllers;

import java.util.Properties;

import helpers.ConnectionDB;
import propertiesReader.PropertiesReader;

public class RegisterController {
	
	ConnectionDB DB=ConnectionDB.getInstances();
	PropertiesReader PR=PropertiesReader.getInstances();
	
	
	public RegisterController (){
		
	}
	
	public void registro (String username, String pass) {
		//Properties prop=new Properties();
		System.out.println(PR.add());
		DB.regUsuario(PR.add(), DB.userId(), username, pass);
		System.out.print("resgistrando user");
	}
	

}
