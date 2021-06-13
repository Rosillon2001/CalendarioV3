package controllers;

import java.sql.SQLException;

import helpers.ConnectionDB;

public class CalendarCreation {

	public CalendarCreation() {
		
	}
	
	ConnectionDB DB=ConnectionDB.getInstances();
	
	public boolean RegistrarCalendario(String nombreCal, String color) throws SQLException {
		DB.regCalendar(DB.idCal(), nombreCal, color);
		return true;
	}
}
