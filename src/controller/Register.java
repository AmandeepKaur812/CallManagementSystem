package controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cipher;
import model.Database;
import model.FileCreator;
import model.MyEmail;
import model.UniqueString;
import model.UserBean;
import model.Validation;

@WebServlet("/Register")
public class Register extends HttpServlet 
{
	private static final long serialVersionUID = -2810342107283578379L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		
			UniqueString random = new UniqueString();
			Validation valid = new Validation();
			UserBean user = new UserBean();
			Database db = new Database();
			Cipher cipher = new Cipher();
			PrintWriter out = response.getWriter();
			boolean flag=false;
			ArrayList<String> error = new ArrayList<String>();
		
			user.setFname(request.getParameter("rfname"));
			user.setLname(request.getParameter("rlname"));
			user.setUname(random.getUnameString());
			user.setPswd(random.getPswdString());
			user.setEmail(request.getParameter("remail"));
			user.setRole(request.getParameter("rrole"));
		
			if(valid.validateRequire(user.getFname()))
			{
				error.add("* Please Enter the FirstName");
			}
			else if(!valid.validateAlphabetic(user.getFname()))
			{
				error.add("* FirstName must contains Only Alphabets");
			}
			if(valid.validateRequire(user.getLname()))
			{
				error.add("* Please Enter the LastName");
			}
			else if(!valid.validateAlphabetic(user.getLname()))
			{
				error.add("* LastName must contains Only Alphabets");
			}
			if(valid.validateRequire(user.getEmail()))
			{
				error.add("* Please Enter the Email id");
			}
			else if(!valid.validateEmail(user.getEmail()))
			{
				error.add("* Please Enter a valid Email Address");
			}
			if(error.isEmpty())
			{
				try 
				{
					flag = db.addUser(user);
					
					if(flag)
					{
						String subject = "Activation of Your Account - CMS";
						String page = "RegisterMe";
						String str = "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/SCM?a="+cipher.encryptString(cipher.numericString(page))+"&b="+cipher.encryptString(user.getUname())+"&c="+cipher.encryptString(user.getPswd());
						String message="<fieldset align='center' style='background:Blue;width:600px'><h1 align='center' style='color:yellow;font-size:40px'>Service Call Management - CMS </h1><br /><b><a href='"+str+"' style='color:pink;font-size:20px;text-decoration:none;'>Click Here to Activate</a></b></br><i><h2 align='right' style='color:white'>Made by&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br><br />Vimesh Shah - 100380116012&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h2><h2 align='right' style='color:white'>Harsh Barach - 100380116050&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h2><br /></i></fieldset><p style='color:#F00'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;* This is automated generated email by CMS system.</p> ";
				
						MyEmail.sendEmail(user.getEmail(), subject, message);
					
						error.add("User Added Succesfully");
					}
					else
					{
						error.add("User Registration failed...");
					
					}
					//session.setAttribute("regerror", error);
					//response.sendRedirect("Adduser");
				} 
				catch (Exception e) 
				{	
					error.add(e.toString());
					//session.setAttribute("regerror", error);
					//response.sendRedirect("AddUser");
				}
			}
			//else
			//{
				session.setAttribute("regerror", error);
				response.sendRedirect("Admin/AddUser");
			//}
			
	}
}
