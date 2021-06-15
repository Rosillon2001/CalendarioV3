package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controllers.CalendarDeletion;
import helpers.ConnectionDB;

/**
 * Servlet implementation class DeleteCalendar
 */
@MultipartConfig()
@WebServlet("/DeleteCalendar")
public class DeleteCalendar extends HttpServlet {
	private static final long serialVersionUID = 5L;
      CalendarDeletion CD=new CalendarDeletion(); 
      ConnectionDB DB=ConnectionDB.getInstances();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCalendar() {
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
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		String usuariosesion=(String) session.getAttribute("Usuario");
		int idusuario=DB.idSession(usuariosesion);
		
		response.setContentType("application/json");
		response.addHeader("Access-Control-Allow-Origin: ", "*");
		String nombre_calendario=request.getParameter("nombre_calendario-delete");
		System.out.println(nombre_calendario);
		PrintWriter pr=response.getWriter();
		pr.write(CD.deleteCalendar(nombre_calendario,idusuario));
	}

}
