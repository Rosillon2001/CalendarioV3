package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.RegisterController;

/**
 * Servlet implementation class Registro
 */
@WebServlet("/Registro")
public class Registro extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	RegisterController RC=new RegisterController();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stu
		
		
		response.setContentType("text/html");
		PrintWriter s=response.getWriter();
		
		//s.print("<html><body>");
		String username=request.getParameter("nombre");
		String pass=request.getParameter("contra");
		String pass2=request.getParameter("contra2");
		
		System.out.println(pass+" "+ pass2+ " "+ "->"+pass.equals(pass2));
		
		if(pass.equals(pass2)){
			RC.registro(username, pass);
			RequestDispatcher rd=request.getRequestDispatcher("/Login.html");
			rd.forward(request, response);
		}else {
			RequestDispatcher rd=request.getRequestDispatcher("/Register.html");
			rd.forward(request, response);
			s.print("<script>window.alert('Las contraseñas no coinciden, intente de nuevo')</script>");
			
		}		
		
	
		
		
		/*RequestDispatcher rd=request.getRequestDispatcher("/a.html");
		rd.forward(request, response);*/
		
	}

}