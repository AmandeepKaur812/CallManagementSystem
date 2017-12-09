package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cipher;
import model.Database;
import model.UserBean;
import model.Validation;

@WebServlet("/Login")
public class Login extends HttpServlet 
{
	private static final long serialVersionUID = -9139149609635447743L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();	
		Database db = new Database();
		UserBean user = new UserBean();
		Cipher cipher = new Cipher();
		
		ArrayList<String> error = new ArrayList<String>();
	
		user.setUname(request.getParameter("uname"));
		user.setPswd(request.getParameter("pswd"));
		
		try 
		{
			if(db.checkLogin(user))
			{
				user.setRole(db.userRole(user));
				String role = user.getRole();
				if(role.equals("Administrator") || role.equals("Management Officer") || role.equals("Service Engineer") || role.equals("Operator"))
				{
					session.setAttribute("login","1");
					session.setAttribute("role",role);
					session.setAttribute("username",user.getUname());
				
					if(request.getParameter("remember")!=null)
					{
						if(request.getParameter("remember").equals("on"))
						{
							Cookie cookie_uname = new Cookie("login_uname",cipher.encryptString(cipher.numericString(user.getUname())));
							Cookie cookie_login = new Cookie("login_status",cipher.encryptString(cipher.numericString(user.getPswd())));
							Cookie cookie_pswd = new Cookie("login_pswd",cipher.encryptString(cipher.numericString(user.getRole())));
							cookie_uname.setMaxAge(60*60*24*365);
							cookie_login.setMaxAge(60*60*24*365);
							cookie_pswd.setMaxAge(60*60*24*365);
							response.addCookie(cookie_uname);
							response.addCookie(cookie_login);
							response.addCookie(cookie_pswd);
							
						}
					}
				}
				if(role.equals("Administrator"))
				{
					response.sendRedirect("Admin/CMS?page=0&tab=operator");
				}
				else if(role.equals("Management Officer"))
				{
					response.sendRedirect("Management/CMS");
				}
				else if(role.equals("Service Engineer"))
				{
					response.sendRedirect("ServiceEngineer/CMS");
				}
				else if(role.equals("Operator"))
				{
					response.sendRedirect("Operator/CMS");
				}
				else
				{
					error.add("UnAuthorised User");
					session.setAttribute("error",error);
					response.sendRedirect("SCM");
					
				}
			}
			else
			{
				session.setAttribute("uname",user.getUname());
				error.add("Invalid Combination of Username and Password");	
				session.setAttribute("error",error);
				response.sendRedirect("SCM");
			}
		} 
		catch(Exception e) 
		{
			error.add(e.toString());	
			session.setAttribute("error",error);
			response.sendRedirect("SCM");
		}
	}

}
