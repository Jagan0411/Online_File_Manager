package hw6;

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Display_FF
 */
@WebServlet("/Display_FF")
public class Display_FF extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public Display_FF() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	//Fetch the user name
    	HttpSession session = request.getSession();
		String sess = (String)session.getAttribute("sess");
    	
		//Fetch user files
		ArrayList<File> ff = new ArrayList<File>();
		
    	//Fetch values for id and name
		String pid=request.getParameter("f_id");
		String name = request.getParameter("f_name");
		
		//Check for the first run
		Integer id = null;
		
		if(pid!=null)
		{
			id = Integer.parseInt(pid);
		}
		
		Connection c =null;
        try
        {
            String url = "jdbc:mysql://localhost/cs3220stu08";
            String username = "cs3220stu08";
            String password = "wIwJ45Ps";

             c = DriverManager.getConnection( url, username,
                password );

             if(id==null)
             {
            	 
	            	 Statement stmt = c.createStatement();
	                 
	             	 ResultSet rs = stmt.executeQuery( "select * from files where owner_id = (select id from users where name='"+sess+"')"
	             			+ "and parent_id is null");
	           
	                 while( rs.next() )
	                 {
	                 	ff.add(new File(rs.getInt("id"),rs.getString("name"),rs.getString("type"),rs.getInt("size"),rs.getTimestamp("date"),
	                     		rs.getInt( "parent_id" ),rs.getBoolean( "is_folder" ), rs.getInt( "owner_id" ))); 	
	                 }
	                 c.close();

	         		request.setAttribute("name", name);
	         		request.setAttribute("id", null);
	         		request.setAttribute("ff", ff);	
	         		
	         		request.getRequestDispatcher("/WEB-INF/hw6/DisplayFF.jsp").forward(request, response);

             }
             
            else
            {
	            Statement stmt = c.createStatement();
	            
	        	ResultSet rs = stmt.executeQuery( "select * from files where owner_id = (select id from users where name='"+sess+"')"
	        			+ "and parent_id='"+id+"'");
	      
	            while( rs.next() )
	            {
	            	ff.add(new File(rs.getInt("id"),rs.getString("name"),rs.getString("type"),rs.getInt("size"),rs.getDate("date"),
	                		rs.getInt( "parent_id" ),rs.getBoolean( "is_folder" ), rs.getInt( "owner_id" ))); 	
	            }
	            c.close();
	         

	    		request.setAttribute("name", name);
	    		request.setAttribute("id", id);
	    		request.setAttribute("ff", ff);	
	    		
	    		request.getRequestDispatcher("/WEB-INF/hw6/DisplayFF.jsp").forward(request, response);

            }
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
        
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
