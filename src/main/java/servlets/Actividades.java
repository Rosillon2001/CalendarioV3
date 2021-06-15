package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controllers.CalendarCreation;
import helpers.ConnectionDB;

/**
 * Servlet implementation class Actividades
 */
@MultipartConfig()
@WebServlet("/Actividades")
public class Actividades extends HttpServlet {
	private static final long serialVersionUID = 7L;
	ConnectionDB DB=ConnectionDB.getInstances();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Actividades() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");

		HttpSession session=request.getSession();
		String usuariosesion=(String) session.getAttribute("Usuario");
		
		int idsession=DB.idSession(usuariosesion);
		
		
		String nombre_calendario=request.getParameter("cal_name");
		String nombre_actividad=request.getParameter("act_name");
		String descripcion_actividad=request.getParameter("act_descp");
		String color_actividad=request.getParameter("act_color");
		String fecha=request.getParameter("act_date");
		String init=request.getParameter("act_init");
		String end=request.getParameter("act_end");
		
		String[] splitInit=init.split(":");
		String initS=splitInit[0];
		String[] splitEnd=end.split(":");
		String endS=splitEnd[0];
		
		int duracion=Integer.parseInt(endS)-Integer.parseInt(initS);
		
		int f=getIdCalendar(nombre_calendario, idsession);
		
		System.out.println(nombre_actividad+"-"+nombre_calendario+"-"+color_actividad+"-"+fecha+"-"+descripcion_actividad+"-"+init+"-"+end+"IDcal: "+f+"--"+duracion);
		
		PrintWriter pr=response.getWriter();
		pr.write("{\"status\":\"200\", \"message\":\"creando actividad\"}");
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}


	CalendarCreation CC=new CalendarCreation();
	
	public int getIdCalendar(String calendario, int userid) {
		int ret = 0;
		int index=0;
		//se obtienen los ids de calendarios procesados por el usuario de la sesion
		for(int i=0;i<CC.getCalendar(userid).size();i++) {
			int idcal=Integer.parseInt( CC.getCalendar(userid).get(i));
			if(DB.getCalendarNames(idcal).equals(calendario)) {
				index=idcal;
			}
		}
		return index;
	}
	
	public String postAct(int id, String nombre, String descp, String color, String fecha, String init, String end, String duracion, String estado, int idCal) {
		if(DB.regAct(id, nombre, descp, color, fecha, init, end, duracion, estado, idCal)) {
			return "{\"status\":\"200\", \"message\":\"creando actividad\"}";
		}else {
			return "{\"status\":\"500\", \"message\":\"Error, no se creo actividad\"}";
		}
	}
}
