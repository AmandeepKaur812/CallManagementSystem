package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Database;
import model.UserBean;

@WebServlet("/UserValidation")
public class UserValidation extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Database db = new Database();
		UserBean user = new UserBean();
		PrintWriter out = response.getWriter();
		boolean flag = false;
		int i = 0;
		user.setUname(request.getParameter("runamer"));
		
		try 
		{
			flag = db.checkUser(user);
			if(flag)
			{
				i=1;
				out.print(i);
			}
			else
			{
				out.print(i);
			}
		} 
		catch (Exception e) 
		{}
	}

}
