package controllers;

import helpers.ConnectionDB;

public class CalendarDeletion {

	public CalendarDeletion(){
		
	}
	ConnectionDB DB=ConnectionDB.getInstances();
	
	public String deleteCalendar(String calendario) {
		if(DB.borrar(calendario)==true) {
			return "{\"status\":\"200\", \"message\":\"calendario eliminado con exito\"}";
		}else {
			return "{\"status\":\"500\", \"message\":\"calendario no pudo ser eliminado\"}";
		}
	}
}
