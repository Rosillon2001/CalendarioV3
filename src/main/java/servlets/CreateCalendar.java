package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateCalendar
 */
@MultipartConfig()
@WebServlet("/CreateCalendar")
public class CreateCalendar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		response.setContentType("text/html");
		response.sendRedirect("Calendario.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
		
		String nombre_calendario=request.getParameter("nombre_calendario");
		String color_calendario=request.getParameter("color_calendario");
		
		System.out.println(nombre_calendario);
		System.out.println(color_calendario);
		
		response.addHeader("Access-Control-Allow-Origin: ", "*");
		PrintWriter pr=response.getWriter();
		//pr.print("<html><body><div>"+nombre_calendario+color_calendario+"</div>"+"</body></html>");
		pr.print("<script>console.log("+nombre_calendario+")"+"</script>");
		/*RequestDispatcher rd=request.getRequestDispatcher("/Calendario.html");
		rd.include(request, response);*/
		
		
	}

}
