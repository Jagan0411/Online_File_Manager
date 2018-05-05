package hw6;

import java.io.File;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
 * Servlet implementation class Delete_FF
 */
@WebServlet("/Delete_FF")
public class Delete_FF extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<Integer> ids = new ArrayList<>();   
	ArrayList<hw6.File> ff = new ArrayList<hw6.File>();
	public Delete_FF() {
        super();
    }

	public void deleteFolder(int id)
    {	
		ids.add(id);
		for(int i = 0;i<ff.size();i++)
		{
			if(ff.get(i).getParentId() == id)
			{
				if(!ff.get(i).bool_Folder)
				{
					ids.add(ff.get(i).getId());
				}
				else
				{
					deleteFolder(ff.get(i).getId());
				}
			}
		}
  	
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
    	int id = Integer.parseInt(request.getParameter("f_id"));
    	String f_name = request.getParameter("f_name");	
    	
    	File[] paths;
		File f = new File(getServletContext().getRealPath( "/WEB-INF/files" ));
		paths = f.listFiles();
    	
		HttpSession session = request.getSession();
		String sess = (String) session.getAttribute("sess");
	
    	int temp=0;
    	String temp_name="";

    	Connection c = null;
    	
    	try
	     {
	 	   String url = "jdbc:mysql://localhost/cs3220stu08";
          String username = "cs3220stu08";
          String password = "wIwJ45Ps";
       
          c = DriverManager.getConnection( url, username, password );
          Statement stmt = c.createStatement();	
          ResultSet rs = stmt.executeQuery( " select name from files where id= and owner_id = (select id from users where name='"+sess+"')");
          
          while(rs.next())
          {
       	   temp_name = rs.getString("name");
       	 //  ff.add(new hw5.File(rs.getInt(""),rs.getString("name"),0, size, date, parentId, bool_Folder, ownerId));
          }
          
          c.close();
	         
	     }
	     catch( SQLException e )
	     {
	         throw new ServletException( e );
	     }

    	//Deletes sub folders 
    	deleteFolder(id);

		try
        {
	        String url = "jdbc:mysql://localhost/cs3220stu08";
            String username = "cs3220stu08";
            String password = "wIwJ45Ps";

            c = DriverManager.getConnection( url, username, password );
            for(int i = 0;i<ids.size();i++)
            {
            PreparedStatement stmt = (PreparedStatement) c.prepareStatement("DELETE from files where id = ?");
            stmt.setInt(1, ids.get(i));
            stmt.executeUpdate();
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

		for (int i = 0; i < ff.size(); i++) 
    	{
    			if(ff.get(i).getId() == id && ff.get(i).bool_Folder)
    			{
    				temp = ff.get(i).getParentId();
    				temp_name=ff.get(i).getName();
    				
    				response.sendRedirect("Display_FF?f_id="+temp+"&f_name="+temp_name);
    				//request.getRequestDispatcher("/WEB-INF/DisplayFF.jsp").forward(request, response);
    			}
    			else if(ff.get(i).getId() == id && ff.get(i).bool_Folder== false)
    			{
    				temp = ff.get(i).getParentId();
    				temp_name=ff.get(i).getName();	
    				
    				for(File path:paths)
    				{
    					if(f_name.equals(path.getName()))
    					{
    						path.delete();
    					}		
    				}
    				response.sendRedirect("Display_FF?f_id="+temp+"&f_name="+temp_name);
    				//request.getRequestDispatcher("/WEB-INF/DisplayFF.jsp").forward(request, response);
    			}
    	}	
		
    	
/*    	
    	//After removing the leafs, remove the parent
    	for (int i = 0; i < ff.size(); i++) 
    	{
    		if(ff.get(i).getId()==id && ff.get(i).bool_Folder == true)
    		{
    			if(ff.get(i).getParent()!=null)
    			{
    				temp = ff.get(i).getParent().getId();
    				temp_name=ff.get(i).getParent().getName();
    				request.setAttribute("id", temp);
    				request.setAttribute("name", temp_name);
    				ff.remove(i);
    				request.getRequestDispatcher("/WEB-INF/DisplayFF.jsp").forward(request, response);
    				//response.sendRedirect("Display_FF?f_id="+temp+"&f_name="+temp_name);
    			}
    	
    			else
    			{	
    				ff.remove(i);
    				request.getRequestDispatcher("/WEB-INF/DisplayFF.jsp").forward(request, response);
    				//response.sendRedirect("Display_FF");
    			}
    		}
    		
    		//Delete file from path and array list
    		else if(ff.get(i).getId()==id && ff.get(i).bool_Folder == false)
    		{
    			if(ff.get(i).getParent()!=null)
    			{
    				for(File path:paths)
    				{
    					if(f_name.equals(path.getName()))
    					{
    						temp = ff.get(i).getParent().getId();
    	    				temp_name=ff.get(i).getParent().getName();
    	    				request.setAttribute("id", temp);
    	    				request.setAttribute("name", temp_name);
    	    				ff.remove(i);
    	    				path.delete();
    						
    					}
    				}
    				
    				request.getRequestDispatcher("/WEB-INF/DisplayFF.jsp").forward(request, response);
    				//response.sendRedirect("Display_FF?f_id="+temp+"&f_name="+temp_name);
    			}
    			
    			else
    			{	
    				for(File path:paths)
    				{
    					if(f_name.equals(path.getName()))
    					{
    						ff.remove(i);
    						path.delete();
    						
    					}
    				}
    				request.getRequestDispatcher("/WEB-INF/DisplayFF.jsp").forward(request, response);
    				//response.sendRedirect("Display_FF");
    			}
    		
    		}
    	}*/
    	
	}    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
