package hw6;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public Registration() {
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

	response.setContentType("text/plain");	
		
	HttpSession session = request.getSession();	
		
	@SuppressWarnings("unchecked")
	ArrayList<Users> users = (ArrayList<Users>) getServletContext().getAttribute("u");
	
	boolean b1=true;
	boolean b2 =true;
	
	int u_size = users.size();
	
	String uname = request.getParameter("uname");
	String pwd = request.getParameter("pwd");
	
	//Check if user name already exists
	for(Users user: users)
	{
		if(uname.equals(user.getUser_name()))
		{
			response.getWriter().print("Username already taken!");
			//request.setAttribute("same","Username already taken!");
			b1 =false;
			break;
		}
	}
	
	//Check for blank password
	if(pwd == null || pwd.equals(null) || pwd == "")
	{
		b2 = false;
		response.getWriter().print("Password can not be blank");
		//request.setAttribute("blank_p","Password can not be blank");
	}
	
	//Add new user with new name
	users.add(new Users(++u_size,uname,pwd));
	
	
	//Make sure that user name is new and pwd not blank
	if(b1 && b2)
	{
		session.setAttribute("user_name",uname);
		//response.getWriter().print("Registered Successfully,please Login");
		//request.setAttribute("msg","Registered Successfully,please Login");
		
		
		Connection c =null;
	    try
	    {
	        String url = "jdbc:mysql://localhost/cs3220stu08";
	        String username = "cs3220stu08";
	        String password = "wIwJ45Ps";

	         c = DriverManager.getConnection( url, username,
	            password );

	        Statement stmt = c.createStatement();
	        stmt.executeUpdate( "insert into users(name) values ('"+uname+"')");
	  
	    }
	    catch( SQLException e )
	    {
	        throw new ServletException( e );
	    }
	    finally
	    {
	        try
	        {
	            if( c != null ) c.close();
	        }
	        catch( SQLException e )
	        {
	            throw new ServletException( e );
	        }
	    }
		
	    response.getWriter().print("false");
		//request.getRequestDispatcher("/WEB-INF/hw6/FM_Home.jsp").forward(request, response);
	}
	/*else
	{
		request.getRequestDispatcher("/WEB-INF/hw6/Registration_Page.jsp").forward(request, response);
	}*/
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
