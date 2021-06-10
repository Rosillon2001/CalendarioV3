package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controllers.LoginController;
import helpers.Auth;
import helpers.Hashing;
/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 2L;
       
	Hashing hash=new Hashing();
	Auth auth=new Auth();
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
		
		if(LC.Login(username, pass)) {
			response.sendRedirect("a.html");
		}else {
			s.print("<script>window.alert('Las credenciales no coinciden con los datos en nuestro registro')</script>");
			response.sendRedirect("Login.html");
		}
		
	}

}
