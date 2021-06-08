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
		Properties prop=new Properties();
		//PR.prop.getProperty("insertUser");
		System.out.println("Usuario "+username+" Passwd "+pass);
	}

}
