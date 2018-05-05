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
 * Servlet implementation class Login_Pg
 */
@WebServlet("/Login_Pg")
public class Login_Pg extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public Login_Pg() {
        super();
    }
    
    public void init(ServletConfig config) throws ServletException {
		 
		super.init( config );

	        try
	        {
	            Class.forName( "com.mysql.jdbc.Driver" );
	        }
	        catch( ClassNotFoundException e )
	        {
	            throw new ServletException( e );
	        }
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		@SuppressWarnings("unchecked")
		ArrayList<Users> users = (ArrayList<Users>) getServletContext().getAttribute("u");
		
		boolean b =false;
		
		String uname = request.getParameter( "uname" );
        String pwd = request.getParameter( "pwd" );
        	
        //Check for existing user name and pwd
        for(Users user:users)
        {
        	if( uname.equals(user.getUser_name()) && pwd.equals(user.getPass()))
        	{
        		b=true;
        	}	
        	else
        	{
        		request.setAttribute("invalid","Invalid username or password");
        	}
        }
        
        //Give access to FM if user name and pwd exists
        if(b)
        {
        	session.setAttribute("sess",uname);
        	//request.setAttribute("id",null);
        	response.sendRedirect("Display_FF");
        	//request.getRequestDispatcher("/WEB-INF/hw6/DisplayFF.jsp").forward(request, response);
        }
        
        if(pwd == null || pwd.equals(null) || pwd == "")
    	{
    		request.setAttribute("blank_p","Password can not be blank");
    		request.getRequestDispatcher("/WEB-INF/hw6/Login_Page.jsp").forward(request, response);
    	}
        
        if(uname == null || uname.equals(null) || uname == "")
        {
        	request.setAttribute("blank_u","Username can not be blank");
        	request.getRequestDispatcher("/WEB-INF/hw6/Login_Page.jsp").forward(request, response);
        }
        
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
