package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.TaskManager;


@WebServlet("/StartComplaint")
public class StartComplaint extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String complainId = request.getParameter("complainId");
		HttpSession session = request.getSession();
		TaskManager task= new TaskManager();
		try 
		{
			task.updateTaskStatus(complainId,"inprogress");
			response.sendRedirect("ServiceEngineer/CMS?tab=inprogress");
		} 
		catch (Exception e) 
		{
			PrintWriter out = response.getWriter();
			out.print(e.toString());
		}
		
	}
}
