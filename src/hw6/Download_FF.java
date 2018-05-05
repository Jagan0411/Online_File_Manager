package hw6;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Download_FF
 */
@WebServlet("/Download_FF")
public class Download_FF extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Download_FF() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String f_name = request.getParameter("f_name");
    	//System.out.println(fname);
    	String path = getServletContext().getRealPath( "/WEB-INF/files/"+f_name+""  );
    	File f = new File(path);
    	
    	response.setContentType( "application/octet-stream" );
        response.setHeader( "Content-Length", "" + f.length() );
        response.setHeader( "Content-Disposition",
            "attachment; filename="+f_name );

        
        FileInputStream in = new FileInputStream(f);
        OutputStream out = response.getOutputStream();

        byte buffer[] = new byte[2048];
        int bytesRead;
        while( (bytesRead = in.read( buffer )) > 0 )
            out.write( buffer, 0, bytesRead );
        in.close();

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
	}

}
