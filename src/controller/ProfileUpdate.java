package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import model.Cipher;
import model.CustomerBean;
import model.Database;
import model.ImportData;
import model.ProductBean;
import model.ProfileDAO;
import model.PurchaseBean;
import model.UserBean;
import model.Validation;

@WebServlet("/ProfileUpdate")
public class ProfileUpdate extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ArrayList<String> error = new ArrayList<String>();
		HttpSession session = request.getSession();
		String uname = session.getAttribute("username").toString();
		UserBean user = new UserBean();
		Validation valid = new Validation();
		ProfileDAO profile = new ProfileDAO();
	
		Database db = new Database();
		Cipher cipher = new Cipher();
		String fname = request.getParameter("pfname");
		String lname =request.getParameter("plname");
		String email = request.getParameter("pemail");
		String mobile = request.getParameter("pmobile");
		String address = request.getParameter("paddress");
		
		if(valid.validateRequire(fname))
		{
			error.add("Please Enter Your FirstName");
		}
		else if(valid.validateAlphabetic(fname)==false)
		{
			error.add("FirstName Contains Only Alphabets");
		}
		
		if(valid.validateRequire(lname))
		{
			error.add("Please Enter Your LastName");
		}
		else if(valid.validateAlphabetic(lname)==false)
		{
			error.add("LastName Contains Only Alphabets");
		}
		
		if(valid.validateRequire(email))
		{
			error.add("Please Enter Your Email ID");
		}
		else if(valid.validateEmail(email)==false)
		{
			error.add("Please Enter Valid Email ID ");
		}
		
		if(valid.validateRequire(mobile))
		{
			error.add("Please Enter Your Mobile");
		}
		else if(valid.validateNumber(mobile)==false)
		{
			error.add("Mobile No Contains Only Digits");
		}
		else if(valid.validateMobile(mobile)==false)
		{
			error.add("Mobile no Lenght Should be 10");
		}
		
		if(valid.validateRequire(address))
		{
			error.add("Please Enter Your Address");
		}
		
		
		if(error.isEmpty())
		{
		try
		{
			boolean flag;
			user.setFname(fname);
			user.setLname(lname);
			user.setEmail(email);
			user.setMobile(mobile);
			user.setAddress(address);
			user.setUname(uname);
			
			flag = profile.updateProfile(user);
			if(!flag)
			{
				error.add("Profile Updation Falied..!!");
			}
		
		}
		catch(Exception e)
		{
			error.add(e.toString());
		}
		}
		if(error.isEmpty())
		{
			error.add("Profile Updated Succesfully");
		}
		session.setAttribute("profileerror", error);
		if(session.getAttribute("role").toString().equals("Administrator"))
		{	
			response.sendRedirect("Admin/Profile");
		}
		else if(session.getAttribute("role").toString().equals("Management Officer"))
		{	
			response.sendRedirect("Management/Profile");
		}
		else if(session.getAttribute("role").toString().equals("Service Engineer"))
		{	
			response.sendRedirect("ServiceEngineer/Profile");
		}
		else if(session.getAttribute("role").toString().equals("Operator"))
		{	
			response.sendRedirect("Operator/Profile");
		}
	}

}
