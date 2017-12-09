package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Report;


@WebServlet("/ServiceEngineerReport")
public class ServiceEngineerReport extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Report report = new Report();
		String filePath;
		String startDate = request.getParameter("fsyear")+"-"+request.getParameter("fsmonth")+"-"+request.getParameter("fsdate");
		String endDate = request.getParameter("tsyear")+"-"+request.getParameter("tsmonth")+"-"+request.getParameter("tsdate");
		
		try 
		{
			String path = getServletContext().getRealPath("\\pdf\\");
			filePath = report.generateServiceEngineerReport(startDate, endDate,path);
			request.setAttribute("filePath", filePath);
			RequestDispatcher rd = request.getRequestDispatcher("FileDownload");
			rd.forward(request, response);
		} 
		catch (Exception e) 
		{
			
		}
	}

}
