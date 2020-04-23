import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class Servlet
 */
//@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get the file chosen by the user
				Part filePart = request.getPart("text_file");
				
				//get the InputStream to store the file somewhere
			    InputStream fileInputStream = filePart.getInputStream();
			    
			    //for example, you can copy the uploaded file to the server
			    //note that you probably don't want to do this in real life!
			    //upload it to a file host like S3 or GCS instead
			    File fileToSave = new File("WebContent/uploaded-files/" + filePart.getSubmittedFileName());
				Files.copy(fileInputStream, fileToSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
				
				//get the URL of the uploaded file
				String fileUrl = "http://localhost:8080/uploaded-files/" + filePart.getSubmittedFileName();
				
				//create output HTML that uses the 
				response.getOutputStream().println("<p>Thanks! Here's a link to your uploaded file:</p>");
				response.getOutputStream().println("<p><a href=\"" + fileUrl + "\">" + fileUrl + "</a></p>");
				response.getOutputStream().println("<p>Upload another file <a href=\"http://localhost:8080/index.html\">here</a>.</p>");	
			}
	}


