package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Logout")
public class Logout extends HttpServlet 
{ 
	private static final long serialVersionUID = 6122247415905136356L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		
		Cookie c[] = request.getCookies();
		for (int i = 0; i < c.length; i++)
    	{
    		if(c[i].getName().equals("login_uname"))
    		{
    			c[i].setValue(null);
    			c[i].setMaxAge(0);
    			
    		}
    		if(c[i].getName().equals("login_status"))
    		{
    			c[i].setValue(null);
    			c[i].setMaxAge(0);
    		}
    		response.addCookie(c[i]);
    	}
		
		session.invalidate();
		response.sendRedirect("SCM");
	}

}
