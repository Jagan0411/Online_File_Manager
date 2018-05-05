package hw6;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Logout_Pg
 */
@WebServlet("/Logout_Pg")
public class Logout_Pg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Logout_Pg() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getSession().invalidate();
		request.setAttribute("logout","Logged out successfully!!");
		request.getRequestDispatcher("/WEB-INF/hw6/FM_Home.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
