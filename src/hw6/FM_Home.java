package hw6;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class FM_Home
 */
@WebServlet("/FM_Home")
public class FM_Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FM_Home() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init(ServletConfig config) throws ServletException {

		super.init(config);
		//Create ArrayList for Users
		ArrayList<Users> u = new ArrayList<Users>();
		
		//Add default users
		u.add(new Users(1,"cysun","abcd"));
		u.add(new Users(2,"jdoe","pqrs"));
	
		getServletContext().setAttribute("u",u);
	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String sess = (String)session.getAttribute("sess");
		
		@SuppressWarnings("unchecked")
		ArrayList<Users> users = (ArrayList<Users>) getServletContext().getAttribute("u");
		
		for(Users user:users)
		{
			if(sess!=null && sess.equals(user.getUser_name()))
			{
				request.getRequestDispatcher("/WEB-INF/hw6/DisplayFF.jsp").forward(request, response);
			}
		}
		
		if(sess == null)
		{
			request.getRequestDispatcher("/WEB-INF/hw6/FM_Home.jsp").forward(request, response);
		}
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String button = request.getParameter("button");
		
		if("New".equals(button))
		{
			request.getRequestDispatcher("/WEB-INF/hw6/Registration_Page.jsp").forward(request, response);	
		}
		
		else if("Login".equals(button))
		{
			request.getRequestDispatcher("/WEB-INF/hw6/Login_Page.jsp").forward(request, response);
		}
			
	}

}
