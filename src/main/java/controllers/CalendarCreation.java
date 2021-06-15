package controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import helpers.ConnectionDB;

public class CalendarCreation {

	public CalendarCreation() {
		
	}
	
	ConnectionDB DB=ConnectionDB.getInstances();
	
	public boolean RegistrarCalendario(String nombreCal, String color, int idsession) throws SQLException {
		int idcal=DB.idCal();
		DB.regCalendar(idcal, nombreCal, color);
		DB.accesoEd(idsession,idcal );
		return true;
	}
	
	public ArrayList<String> getCalendar(int userid) {
		if(DB.OwnCalendars(userid).equals("")) {}
		else {
			String idCalendarios=DB.OwnCalendars(userid); ArrayList <String> cal=new ArrayList<>();
			String[] split=idCalendarios.split("-");
			for (int i=0; i<split.length; i++) {
				cal.add(split[i]);
			}
		return cal;
		}
		return null;
	}
	
	public ArrayList<String> calends(int userid) {
		
		//ArrayList <String>ids=getCalendar(userid);
		ArrayList <String> cal=new ArrayList<>();
		String Calendarios;
		if(getCalendar(userid)!=null) {
			for(int i=0;i<getCalendar(userid).size();i++) {
				int idcal=Integer.parseInt(getCalendar(userid).get(i));
				Calendarios=DB.getCalendars(idcal); 
				cal.add(Calendarios);	
			}
		}
		return cal;
	}
	
	public boolean calExists(String calendario, int userid) {
		boolean b=true;
		System.out.println("Calendarios user ->"+getCalendar(userid));
		if(getCalendar(userid)==null) {
			return false;
		}
		else {
			for(int i=0;i<getCalendar(userid).size();i++) {
				int idcal=Integer.parseInt(getCalendar(userid).get(i));
				System.out.println("calenda"+DB.getCalendarNames(idcal));
				System.out.println(calendario);
				if(DB.getCalendarNames(idcal).equals(calendario)) {
					
					b=true;
				}else {
					b=false;
				}
			}
		}
		return b;
	}
}
