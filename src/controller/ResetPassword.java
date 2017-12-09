package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Database;
import model.UserBean;
import model.Validation;


@WebServlet("/ResetPassword")
public class ResetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		UserBean olduser = new UserBean();
		UserBean latestuser = new UserBean();
		Database db = new Database();
		Validation valid = new Validation();
		boolean flag = false;
		PrintWriter out =response.getWriter();
		
		String pswd = request.getParameter("pswdf");
		String cpswd = request.getParameter("cpswdf");
		
		ArrayList<String> error = new ArrayList<String>();
		
		if(valid.validateRequire(pswd))
		{
			error.add("Please Enter Your Password");
		}
		else if(pswd.length()<8)
		{
			error.add("Password Length must be Minimum 8 ");
		}
		
		if(valid.validateRequire(cpswd))
		{
			error.add("Please Enter Your Confirm Password");
		}
		else if(valid.validateCompare(pswd, cpswd)==false)
		{
			error.add("Password and Confirm Password must be Same");
		}
			
		if(error.isEmpty())
		{
			olduser = (UserBean)session.getAttribute("folduser");
			latestuser.setPswd(pswd);
		
			try 
			{
				flag = db.regainUser(olduser, latestuser);
				error.add("Password Reseted successfully");
				session.setAttribute("url",null);
				session.setAttribute("error", error);
				response.sendRedirect("SCM");
			} 
			catch (Exception e) 
			{		
			}
		}
		else
		{
			session.setAttribute("reseterror", error);
			response.sendRedirect(session.getAttribute("url").toString());
		}
	
	}

}
