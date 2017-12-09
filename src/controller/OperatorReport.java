package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Report;

@WebServlet("/OperatorReport")
public class OperatorReport extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Report report = new Report();
		String filePath;
		String startDate = request.getParameter("foyear")+"-"+request.getParameter("fomonth")+"-"+request.getParameter("fodate");
		String endDate = request.getParameter("toyear")+"-"+request.getParameter("tomonth")+"-"+request.getParameter("todate");
		
		try 
		{
			String path = getServletContext().getRealPath("\\pdf\\");
			filePath = report.generateOperatorReport(startDate, endDate,path);
			request.setAttribute("filePath", filePath);
			RequestDispatcher rd = request.getRequestDispatcher("FileDownload");
			rd.forward(request, response);
		} 
		catch (Exception e) 
		{
			
		}
	}

}
