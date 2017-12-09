package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Database;
import model.UserBean;
import model.Validation;


@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		UserBean olduser = new UserBean();
		UserBean user = new UserBean();
		UserBean latestuser = new UserBean();
		Database db = new Database();
		Validation valid = new Validation();
		boolean flag = false;
		PrintWriter out =response.getWriter();
		ArrayList<String> error =new ArrayList<String>();
		ArrayList<String> error1 =new ArrayList<String>();
		
		olduser = (UserBean)session.getAttribute("olduser");
		latestuser.setUname(request.getParameter("unamer"));
		latestuser.setPswd(request.getParameter("pswdr"));
		
		
			try 
			{
				flag = db.registerUser(olduser, latestuser);
			
				user = db.selectUser(latestuser.getUname());
				if(user.getRole().equals("Service Engineer"))
				{
					flag = db.addUnAssignedUser(latestuser);
					flag = db.addSkill(user.getUname(), request.getParameter("skill"));
				}
				error.add("You are Registered Succesfully");
				session.setAttribute("error",error);
				response.sendRedirect("SCM");
			} 
			catch (Exception e) 
			{		
				out.print(e.toString());
				
			}
		}
		
		
	

}
