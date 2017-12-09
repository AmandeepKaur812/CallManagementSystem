package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Report;


@WebServlet("/RootCauseReport")
public class RootCauseReport extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Report report = new Report();
		String filePath;
		String startDate = request.getParameter("fryear")+"-"+request.getParameter("frmonth")+"-"+request.getParameter("frdate");
		String endDate = request.getParameter("tryear")+"-"+request.getParameter("trmonth")+"-"+request.getParameter("trdate");
		
		try 
		{
			String path = getServletContext().getRealPath("\\pdf\\");
			filePath = report.generateRootCauseReport(startDate, endDate,path);
			request.setAttribute("filePath", filePath);
			RequestDispatcher rd = request.getRequestDispatcher("FileDownload");
			rd.forward(request, response);
		} 
		catch (Exception e) 
		{
			
		}
	}

}
