package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Database;
import model.Validation;


@WebServlet("/ChangePSWD")
public class ChangePSWD extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String opswd = request.getParameter("opswd");
		String npswd = request.getParameter("npswd");
		String ncpswd = request.getParameter("ncpswd");
		
		ArrayList<String> error = new ArrayList<String>();
		Validation valid = new Validation();
		Database db = new Database();
		HttpSession session = request.getSession();
		
		if(valid.validateRequire(opswd))
		{
			error.add("* Please Enter Your Old Password");
		}
		
		if(valid.validateRequire(npswd))
		{
			error.add("* Please Enter Your New Password");
		}
		else if(npswd.length()>20 || npswd.length() <8)
		{
			error.add("* Password Length Should Between 8 to 20");
		}
		
		if(valid.validateRequire(ncpswd))
		{
			error.add("* Please Enter Your Confirm Password");
		}
		else if(!valid.validateCompare(npswd,ncpswd))
		{
			error.add("* Confirm Password should Match With Confirm Password ");
		}
		
		if(error.isEmpty())
		{
			boolean flag;
			try 
			{
				flag = db.isValidPassword(opswd);
				if(flag)
				{
					flag = db.changePassword(opswd, npswd);
					error.add("Password Changed Succesfully");				
				}
				else
				{
					error.add("* Enter Correct Old Password");
				}
			} 
			catch (Exception e) 
			{
				error.add("Error Occured...!!!");
			}
		}
		session.setAttribute("cpswderror", error);	
		if(session.getAttribute("role").toString().equals("Administrator"))
		{	
			response.sendRedirect("Admin/ChangePassword");
		}
		else if(session.getAttribute("role").toString().equals("Management Officer"))
		{	
			response.sendRedirect("Management/ChangePassword");
		}
		else if(session.getAttribute("role").toString().equals("Service Engineer"))
		{	
			response.sendRedirect("ServiceEngineer/ChangePassword");
		}
		else if(session.getAttribute("role").toString().equals("Operator"))
		{	
			response.sendRedirect("Operator/ChangePassword");
		}
		
	}

}
