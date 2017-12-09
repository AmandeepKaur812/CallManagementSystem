package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ProfileDAO;
import model.UserBean;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class ProPicUpdate
 */
@WebServlet("/ProPicUpdate")
public class ProPicUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ArrayList<String> error = new ArrayList<String>();
		HttpSession session = request.getSession();
		String uname = session.getAttribute("username").toString();
		UserBean user = new UserBean();
		ProfileDAO profile = new ProfileDAO();
		UserBean u = new UserBean();
		u=(UserBean)session.getAttribute("user");
		
		boolean isMultipart;
		String filePath="";
		String path = getServletContext().getRealPath("\\");
		if(session.getAttribute("role").toString().equals("Administrator"))
		{	
			 filePath=path+"\\Admin\\ProPic\\";
		}
		else if(session.getAttribute("role").toString().equals("Management Officer"))
		{	
			 filePath= path+"\\Management\\ProPic\\";
		}
		else if(session.getAttribute("role").toString().equals("Service Engineer"))
		{	
			 filePath= path+"\\ServiceEngineer\\ProPic\\";
		}
		else if(session.getAttribute("role").toString().equals("Operator"))
		{	
			 filePath= path+"\\WebContent\\Operator\\ProPic\\";
		}
		
		int maxFileSize = 5000 * 1024;
		int maxMemSize = 40 * 1024;
		File file ;
		
		
		isMultipart = ServletFileUpload.isMultipartContent(request);	   
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(maxMemSize);
		factory.setRepository(new File("c:\\temp"));
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax( maxFileSize );
		PrintWriter out = response.getWriter();

		try
		{ 
			
			List fileItems = upload.parseRequest(request);
			Iterator i = fileItems.iterator();

			while ( i.hasNext () ) 
			{
				FileItem fi = (FileItem)i.next();
				
				if ( !fi.isFormField () )	
				{
					String fieldName = fi.getFieldName();
					String fileName = uname;
					user.setUname(uname);
					String contentType = fi.getContentType();
					boolean isInMemory = fi.isInMemory();
					long sizeInBytes = fi.getSize();
					
					
					if(contentType.equals("image/jpeg") || contentType.equals("image/png"))
					{
						if(contentType.equals("image/jpeg"))
						{
							fileName = fileName+".jpg";
						}
						if(contentType.equals("image/png"))
						{
							fileName = fileName+".png";
						}
						if( fileName.lastIndexOf("\\") >= 0 )
						{
							file = new File( filePath +fileName.substring( fileName.lastIndexOf("\\"))) ;
						}
						else
						{
							file = new File( filePath + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
						}
					
						fi.write( file ) ;
						
						user.setProfilePicture(fileName);
						profile.updateProfilePicture(user);
						
					}
					else if(contentType.equals("application/octet-stream"))
					{	
						error.add("Please Select Image..!");
						user.setProfilePicture(u.getProfilePicture());
					}
					else
					{
						error.add("Please Select Image in JPG or PNG format");
						user.setProfilePicture(u.getProfilePicture());
					}
				}
			}
		}
		catch(Exception e)
		{
			error.add(e.toString());
		}
		
		if(error.isEmpty())
		{
			error.add("Profile Picture Updated Succesfully");
		}
		session.setAttribute("propicerror", error);
		if(session.getAttribute("role").toString().equals("Administrator"))
		{	
			response.sendRedirect("Admin/ProfilePic");
		}
		else if(session.getAttribute("role").toString().equals("Management Officer"))
		{	
			response.sendRedirect("Management/ProfilePic");
		}
		else if(session.getAttribute("role").toString().equals("Service Engineer"))
		{	
			response.sendRedirect("ServiceEngineer/ProfilePic");
		}
		else if(session.getAttribute("role").toString().equals("Operator"))
		{	
			response.sendRedirect("Operator/ProfilePic");
		}
			
	}

}
