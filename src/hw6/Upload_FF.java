package hw6;

import java.io.File;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class Upload_FF
 */
@WebServlet("/Upload_FF")
public class Upload_FF extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Upload_FF() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
    	String pid=request.getParameter("f_id");
    	String name=request.getParameter("f_name");
		
		Integer id = null;

		if(pid!=null)
		{
			id = Integer.parseInt(pid);
		}
		
		request.setAttribute("id", id);
		request.setAttribute("name", name);
		request.getRequestDispatcher("/WEB-INF/hw6/Upload_FF.jsp").forward(request, response);	
  
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   	
    	
    	HttpSession session = request.getSession();
    	String sess = (String) session.getAttribute("sess");
    	String pid = null;
    	String f_name="";
    	String parent = "";
		
		Integer id = null;
		
    	// Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Configure a repository (to ensure a secure temp location is used)
        ServletContext servletContext = this.getServletConfig()
            .getServletContext();
        File repository = (File) servletContext
            .getAttribute( "javax.servlet.context.tempdir" );
        factory.setRepository( repository );

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload( factory );

        // The directory we want to save the uploaded files to.
        String fileDir = getServletContext().getRealPath( "/WEB-INF/files" );
        
        File file = null;

        // Parse the request
        try
        {
            List<FileItem> items = upload.parseRequest( request );
            for( FileItem item : items )
            {
                // If the item is not a form field - meaning it's an uploaded
                // file, we save it to the target dir
                if( !item.isFormField() )
                {
                    // item.getName() will return the full path of the uploaded
                    // file, e.g. "C:/My Documents/files/test.txt", but we only
                    // want the file name part, which is why we first create a
                    // File object, then use File.getName() to get the file
                    // name.
                    String fileName = (new File( item.getName() )).getName();
                    f_name = fileName;
                    file = new File( fileDir, fileName );
                    item.write( file );
               
                }
                
                else
                {
                	if(item.getFieldName().equals("f_id"))
                	{
                		pid = item.getString();
                	}
                	
                	else if(item.getFieldName().equals("parent"))
                	{
                		parent = item.getString();
                	}
                }
            }

        }
        catch( Exception e )
        {
            throw new IOException( e );
        }

        
        if(pid!=null)
		{
			id = Integer.parseInt(pid);
		}
        
        //File[] paths;
		File f = new File(getServletContext().getRealPath( "/WEB-INF/files" ));
		//paths = f.listFiles();
		
		String fileType = new MimetypesFileTypeMap().getContentType(getServletContext().getRealPath( "/WEB-INF/files"+f_name ));
		
		if(id==null)
		{
			//for(File path:paths)
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
	        			+ "('"+file.getName()+"','"+fileType+"',"+f.length()/1024+",false, null,(SELECT id from users where name='"+sess+"' ))");
	     
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

			response.sendRedirect("Display_FF?f_name="+parent);	
			//ff.add(new File(++ff_size,file.getName(),fileType,(f.length()/1024),dNow,null,false));
			
		}
        //Uploading in sub folders
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
	        			+ "('"+file.getName()+"','"+fileType+"',"+f.length()/1024+",false,"+id+",(SELECT id from users where name='"+sess+"' ))");
	     
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
			response.sendRedirect("Display_FF?f_id="+id+"&f_name="+parent);
		}
	
	}

}
