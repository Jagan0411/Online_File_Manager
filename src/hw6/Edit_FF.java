package hw6;

import java.io.File;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Edit_FF
 */
@WebServlet("/Edit_FF")
public class Edit_FF extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Edit_FF() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String sess = (String) session.getAttribute("sess");
		
		String old_f_name= request.getParameter("f_name");
		int f_id = Integer.parseInt(request.getParameter("f_id"));
		
		String bool_Folder = request.getParameter("bool_Folder");
		
		Connection c = null;
	 	
	 	 try
	     {
	         String url = "jdbc:mysql://localhost/cs3220stu08";
	         String username = "cs3220stu08";
	         String password = "wIwJ45Ps";
	      
	         c = DriverManager.getConnection( url, username, password );
	         Statement stmt = c.createStatement();
	         ResultSet rs = stmt.executeQuery( "select * from files where id="+f_id+" "
	         		+ "and owner_id = (select id from users where name='"+sess+"')");
	         
	         while( rs.next() )
	         {            	
	        	 request.setAttribute("f_name",rs.getString("name"));
	        	 request.setAttribute("id",rs.getInt("parent_id"));
	         }
	        
	         c.close(); 
	     }
	     catch( SQLException e )
	     {
	         throw new ServletException( e );
	     }

	 	 request.setAttribute("bool_Folder",bool_Folder);
		request.setAttribute("f_id",f_id );
		request.setAttribute("old_f_name",old_f_name);
		request.getRequestDispatcher("/WEB-INF/hw6/Edit_FF.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		String sess = (String) session.getAttribute("sess");
		
		String f_name = request.getParameter("f_name");
		String old_f_name = request.getParameter("old_f_name");
		
		boolean bool_Folder = Boolean.parseBoolean(request.getParameter("bool_Folder"));
		
		if(bool_Folder == false)
		{
		
			File[] paths;
			File f1 = new File(getServletContext().getRealPath( "/WEB-INF/files" ));
			paths = f1.listFiles();
			String fileDir = getServletContext().getRealPath( "/WEB-INF/files" );
			
			File newFile = new File(fileDir,f_name);
			File oldFile= new File(fileDir,old_f_name);
		
		     for(File path:paths)
		 		{
		 			if(path.getName().equals(old_f_name))		
		 			{
		 				oldFile.renameTo(newFile);
		 				break;
		 			}
		 			
		 		}
			
		}
		
		//Get edited folder id 
		int f_id = Integer.parseInt(request.getParameter("f_id"));
		String pid = request.getParameter("parent_id");
		String parent_name="";
		
		Integer parent_id = null;
		
		if(pid!=null)
		{
			parent_id = Integer.parseInt(pid);
		}
		
		Connection c = null;
	 	
	 	 try
	     {
	 	   String url = "jdbc:mysql://localhost/cs3220stu08";
           String username = "cs3220stu08";
           String password = "wIwJ45Ps";
        
           c = DriverManager.getConnection( url, username, password );
           Statement stmt = c.createStatement();	
           stmt.executeUpdate( "update files set "
           		+ "name='"+f_name+"' where id='"+f_id+"' and owner_id = (select id from users where name='"+sess+"')");
           
           c.close();
	         
	    
	     }
	     catch( SQLException e )
	     {
	         throw new ServletException( e );
	     }

		
		if(parent_id == null || parent_id == 0)
		{		 	
			response.sendRedirect("Display_FF");
		}
		
		else
		{
			try
		     {
		 	   String url = "jdbc:mysql://localhost/cs3220stu08";
	           String username = "cs3220stu08";
	           String password = "wIwJ45Ps";
	        
	           c = DriverManager.getConnection( url, username, password );
	           Statement stmt = c.createStatement();	
	           ResultSet rs = stmt.executeQuery( " select name from files where id="+parent_id+" and owner_id = (select id from users where name='"+sess+"')");
	           
	           while(rs.next())
	           {
	        	   parent_name = rs.getString("name");
	           }
	           
	           c.close();
		         
		     }
		     catch( SQLException e )
		     {
		         throw new ServletException( e );
		     }

	 	 	response.sendRedirect("Display_FF?f_id="+parent_id+"&f_name="+parent_name);
 	 	
		}
	}
}
