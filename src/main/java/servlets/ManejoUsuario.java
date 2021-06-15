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

import helpers.ConnectionDB;
import helpers.Hashing;

/**
 * Servlet implementation class ManejoUsuario
 */
@MultipartConfig
@WebServlet("/ManejoUsuario")
public class ManejoUsuario extends HttpServlet {
	private static final long serialVersionUID = 6L;
	ConnectionDB DB=ConnectionDB.getInstances();
	Hashing hash=new Hashing();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManejoUsuario() {
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
		String clave=DB.getClave(usuariosesion);
		//String hashClave=hash.getHash(clave);
		
	        PrintWriter pr=response.getWriter();
	        pr.print("{\"Username\":\""+usuariosesion+"\",\"Password\":\""+clave+"\"}");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("txt/html");
		cerrarSesion(request);
		response.sendRedirect("index.html");
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		HttpSession session=request.getSession();
		String username=(String) session.getAttribute("Usuario");
		int iduser=DB.idSession(username);
		//seobtienen los datos editados
		String nombre=request.getParameter("nombre_usuario");
		String pass=request.getParameter("clave_usuario");
		String hashPass=hash.getHash(pass);
		DB.modificar(iduser, nombre, hashPass);
		PrintWriter pr=response.getWriter();
		pr.write("{\"status\":\"200\", \"Nombre_Usuario\":\""+nombre+"\"}");
		System.out.println(nombre+"-"+pass);
		
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	public void cerrarSesion(HttpServletRequest request ) {
		HttpSession session=request.getSession();
		session.invalidate();
	}

}
