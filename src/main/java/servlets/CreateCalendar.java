package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controllers.CalendarCreation;
import helpers.Auth;
import helpers.ConnectionDB;

/**
 * Servlet implementation class CreateCalendar
 */
@MultipartConfig()
@WebServlet("/CreateCalendar")
public class CreateCalendar extends HttpServlet {
	private static final long serialVersionUID = 3L;
	CalendarCreation CC=new CalendarCreation();
	ConnectionDB DB=ConnectionDB.getInstances();
	Auth auth=new Auth();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateCalendar() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		
		HttpSession session=request.getSession();
		String usuariosesion=(String) session.getAttribute("Usuario");
		int idsession=DB.idSession(usuariosesion);
		 PrintWriter pr=response.getWriter();
		if(CC.getCalendar(idsession)!=null){
			pr.print(""+CC.calends(idsession)+"");
		}else {
			pr.print("{\"status\":\"500\"}");
		}
		
		
        
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
		
		
		String nombre_calendario=request.getParameter("nombre_calendario");
		String color_calendario=request.getParameter("color_calendario");
		
		PrintWriter pr=response.getWriter();
		if(CC.calExists(nombre_calendario, idsession)==false) {
			response.addHeader("Access-Control-Allow-Origin: ", "*");
			pr.print("{\"nombre\":\""+nombre_calendario+"\",\"color\":\""+color_calendario+"\"}");
			try {
				CC.RegistrarCalendario(nombre_calendario, color_calendario, idsession);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			pr.print("{\"status\":\"500\", \"message\":\"calendario con ese nombre existe\"}");
		}
		
		
		System.out.println(CC.calExists(nombre_calendario, idsession));
		
		/*RequestDispatcher rd=request.getRequestDispatcher("/Calendario.html");
		rd.include(request, response);*/
        
		
	}

}
