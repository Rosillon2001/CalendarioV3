package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import helpers.ConnectionDB;

/**
 * Servlet implementation class EliminacionCuenta
 */
@WebServlet("/EliminacionCuenta")
public class EliminacionCuenta extends HttpServlet {
	private static final long serialVersionUID = 8L;
	ConnectionDB DB=ConnectionDB.getInstances();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminacionCuenta() {
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
		response.setContentType("txt/html");
		HttpSession session=request.getSession();
		String username=(String) session.getAttribute("Usuario");
		DB.deleteUser(username);
		response.sendRedirect("index.html");
	}

}
