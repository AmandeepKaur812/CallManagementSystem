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

import model.Cipher;
import model.Database;
import model.FileCreator;
import model.ForgetMessage;
import model.MyEmail;
import model.UniqueString;
import model.UserBean;
import model.Validation;


@WebServlet("/ForgetPassword")
public class ForgetPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.sendRedirect("Forget");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		Database db = new Database();
		UserBean user = new UserBean();
		Cipher cipher = new Cipher();
		UniqueString unique = new UniqueString();
		ForgetMessage msg = new ForgetMessage();
		String pswd = unique.getPswdString();
		Validation valid =new Validation();
		PrintWriter out = response.getWriter();
		
		ArrayList<String> error = new ArrayList<String>();
		
		String uname = request.getParameter("funame");
		String email = request.getParameter("femail");
		
		session.setAttribute("funame", uname);
		session.setAttribute("femail", email);
		
		if(valid.validateRequire(uname))
		{
			error.add("* Please Enter Your Username");
		}
		
		else if(valid.validateAlphaNumeric(uname)==false)
		{
			error.add("* Please Enter a Valid Username");
		}
		
		if(valid.validateRequire(email))
		{
			error.add("* Please Enter Your Email ID");
		}
		
		else if(valid.validateEmail(email)==false)
		{
			error.add("* Please Enter a Valid Email ID");
		}
	
		if(error.isEmpty())
		{
			boolean flag = false;
			boolean flag1 = false;
		
			user.setUname(uname);
			user.setEmail(email);
	
			try 
			{
				flag = db.checkForget(user);
			
				if(flag)
				{
					flag1=db.forgetUser(user, pswd);
					String page="Reset";
					String link="http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/SCM?a="+cipher.encryptString(cipher.numericString(page))+"&b="+cipher.encryptString(cipher.numericString(user.getUname()))+"&c="+cipher.encryptString(pswd);
					String message = msg.getForgetMessage(link);
					
					MyEmail.sendEmail(user.getEmail(), msg.getForgetSubject(), message);
					
				}
				else
				{
					error.add("* Invalid Combition of Username and Email ID");
				}
			}
			catch (Exception e) 
			{
				error.add(e.toString());
			}
		}
		
		if(error.isEmpty())
		{
			error.add("Password Reset link Sended to Your Email ID Successfully");
			session.setAttribute("funame", null);
			session.setAttribute("femail",null);
			session.setAttribute("error",error);
			response.sendRedirect("SCM");
		}
		else
		{
			session.setAttribute("forgetpassworderror",error);
			response.sendRedirect("Forget");
		}
	}

}
