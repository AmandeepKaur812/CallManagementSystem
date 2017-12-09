package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Validation;

@WebServlet("/AuthenticationUser")
public class AuthenticationUser extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		String uname = request.getParameter("uname");
		String pswd = request.getParameter("pswd");
		Validation valid = new Validation();
		
		ArrayList<String> error = new ArrayList<String>();
		session.setAttribute("uname", uname);
		if(valid.validateRequire(uname))
		{
			error.add("* Please Enter Your Username");
		}
		
		
		
		if(valid.validateRequire(pswd))
		{
			error.add("* Please Enter Your Password");
		}
		
		if(error.isEmpty())
		{
			RequestDispatcher rd = request.getRequestDispatcher("Login");
			rd.forward(request, response);
		}
		else
		{
			session.setAttribute("error",error);
			response.sendRedirect("SCM");
		}
		
	}

}
