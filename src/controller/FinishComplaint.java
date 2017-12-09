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

import model.ComplaintBean;
import model.DateTime;
import model.TaskManager;
import model.Validation;

@WebServlet("/FinishComplaint")
public class FinishComplaint extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Validation valid = new Validation();
		HttpSession session = request.getSession();
		String solution = request.getParameter("solution");
		String rootCause = request.getParameter("rootcause");
		
		ArrayList<String> error = new ArrayList<String>();
		ComplaintBean complain = new ComplaintBean();
		TaskManager task = new TaskManager();
		
		PrintWriter out = response.getWriter();
		DateTime dt = new DateTime();
		
		complain = (ComplaintBean)session.getAttribute("complain");
		String complainId = complain.getComplaintID();
		if(valid.validateRequire(solution))
		{
			error.add("* Please Enter Solution");
			
		}
		
		if(error.isEmpty())
		{
			try 
			{
				
				task.addRootCause(rootCause,complainId);
				task.updateTaskStatus(complainId, "finished");
				task.decreaseTaskCount(session.getAttribute("username").toString());
				task.addSolution(solution, complainId);
				task.setCurrentDate(complainId);
				session.setAttribute("navigation","1");
				session.setAttribute("task","0");
				response.sendRedirect("ServiceEngineer/CMS?tab=finished");
			} 
			catch (Exception e) 
			{
				// TODO Auto-generated catch block
				out.print(e.toString());
				e.printStackTrace();
				
			}
		}
		else
		{
			session.setAttribute("complainerror", error);
			response.sendRedirect("ServiceEngineer/ProgressDetails");
		}
	}

}
