package hw6;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Create_FF
 */
@WebServlet("/Create_FF")
public class Create_FF extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Create_FF() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
    	String pid=request.getParameter("f_id");
		String name = request.getParameter("f_name");
    	
		Integer id = null;
		
		if(pid!=null)
		{
			id = Integer.parseInt(pid);
		}
		
	request.setAttribute("id", id);
	request.setAttribute("name", name);
	request.getRequestDispatcher("/WEB-INF/hw6/Create_FF.jsp").forward(request, response);
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/plain");
		HttpSession session = request.getSession();
		String sess = (String) session.getAttribute("sess");	
		String fname = request.getParameter("f_name");	
		String pid=request.getParameter("f_id");
		
		Integer id = null;
		
		String parent= request.getParameter("name");

		if(pid == "" || parent == "")
		{
			pid = null;
			parent= null;
		}
		
		if(pid!=null)
		{
			id = Integer.parseInt(pid);
		}
	
		//Add for root folders
		if(id==null)
		{
			Connection c =null;
	        try
	        {
	            String url = "jdbc:mysql://localhost/cs3220stu08";
	            String username = "cs3220stu08";
	            String password = "wIwJ45Ps";

	             c = DriverManager.getConnection( url, username,
	                password );

	            Statement stmt = c.createStatement();
	        	stmt.executeUpdate( "insert into files(name,type,size,is_folder,parent_id,owner_id) values "
	        			+ "('"+fname+"','folder',0, true, null,(SELECT id from users where name='"+sess+"' ))");
	     
	            c.close();
	        
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
			//response.sendRedirect("Display_FF");	
		}
		//Add for sub folders
		else
		{
				Connection c =null;
		        try
		        {
		            String url = "jdbc:mysql://localhost/cs3220stu08";
		            String username = "cs3220stu08";
		            String password = "wIwJ45Ps";

		             c = DriverManager.getConnection( url, username,
		                password );

		            Statement stmt = c.createStatement();
		        	stmt.executeUpdate( "insert into files(name,type,size,is_folder,parent_id,owner_id) values "
		        			+ "('"+fname+"','folder',0,true,"+id+",(SELECT id from users where name='"+sess+"' ))");
		     
		            c.close();
		        
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
		        //response.sendRedirect("Display_FF?f_id="+id+"&f_name="+parent);
			}
		}
}