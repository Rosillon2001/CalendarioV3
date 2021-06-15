package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controllers.CalendarCreation;
import controllers.LoginController;
import helpers.Auth;
import helpers.ConnectionDB;
import helpers.Hashing;
/**
 * Servlet implementation class Login
 */
@MultipartConfig()
//@WebServlet("/public/views/Login")
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 2L;
       
	Hashing hash=new Hashing();
	Auth auth=new Auth();
	CalendarCreation CC=new CalendarCreation();
	ConnectionDB DB=ConnectionDB.getInstances();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
      
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//HttpSession sesion=request.getSession();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter s=response.getWriter();
		
		response.setContentType("text/html");
		
		String username=request.getParameter("nombre");
		String pass=request.getParameter("contra");
		pass=hash.getHash(pass);
		
		LoginController LC=new LoginController();
		HttpSession session=request.getSession();
		try {
			if(LC.Login(request,username, pass) && session.getAttribute(username)==null) {
				//response.sendRedirect("Calendario.html");
				RequestDispatcher rd=request.getRequestDispatcher("/Calendario.html");
				rd.include(request, response);
				//System.out.println(session.getAttribute("Usuario"));
			}else {
				s.print("<script>window.alert('Las credenciales no coinciden con los datos en nuestro registro')</script>");
				response.sendRedirect("Login.html");
			}
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//pasar el username al front
		UserSession(response, session.getAttribute("Usuario"));
		String usuariosesion=(String) session.getAttribute("Usuario");
		
	}
	public void UserSession(HttpServletResponse resp, Object user) throws IOException {
		PrintWriter s=resp.getWriter();
		s.print("<html><body><div id='NombreUsuario'>"+user+"</div></body></html>");
	}
}
