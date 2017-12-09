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

import model.AssignToEngineer;
import model.ComplaintBean;
import model.Database;

@WebServlet("/AssignToSME")
public class AssignToSME extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		AssignToEngineer asg =  new AssignToEngineer();
		ArrayList<String> error = new ArrayList<String>();
		ComplaintBean complain = new ComplaintBean();
		Database db = new Database();
		
		
		String complainID = session.getAttribute("complainId").toString();
		
		boolean flag = true;
		try {
			complain = db.getComplaintDetails(complainID);
			asg.assignTask(complainID,complain.getProductCategory());
			error.add("Complaint Registerd Successfully");
			session.setAttribute("complainerror", error);
			response.sendRedirect("Operator/CMS");
		} 
		catch (Exception e) 
		{
			error.add(e.toString());
			session.setAttribute("complainerror", error);
			response.sendRedirect("Operator/CreateComplaint");
		}
	}
	

}
