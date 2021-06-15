package controllers;

import helpers.ConnectionDB;

public class CalendarDeletion {

	public CalendarDeletion(){
		
	}
	ConnectionDB DB=ConnectionDB.getInstances();
	CalendarCreation CC=new CalendarCreation();
	
	public String deleteCalendar(String calendario, int userid) {
		String ret="";
		int index=0;
		//se obtienen los ids de calendarios procesados por el usuario de la sesion
		for(int i=0;i<CC.getCalendar(userid).size();i++) {
			int idcal=Integer.parseInt( CC.getCalendar(userid).get(i));
			if(DB.getCalendarNames(idcal).equals(calendario)) {
				index=idcal;
				ret="{\"status\":\"200\", \"nombre\":\""+calendario+"\"}";
			}else {
				ret="{\"status\":\"500\", \"message\":\"calendario no ha sido eliminado, no pertenece a su cuenta\"}";
			}
		}
		DB.borrarCalendar(index);
		
		return ret;
		
	}
}
