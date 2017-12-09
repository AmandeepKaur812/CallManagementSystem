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


import java.sql.*;

import model.ComplaintID;
import model.CustomerBean;
import model.Database;
import model.DateTime;
import model.ForgetMessage;
import model.MyEmail;
import model.ProductBean;
import model.SMS;
import model.UserBean;
import model.Validation;


@WebServlet("/ComplaintCreator")
public class ComplaintCreator extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.sendRedirect("Operator/CMS");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		CustomerBean customer = new CustomerBean();
		ProductBean product = new ProductBean();
		HttpSession session = request.getSession();
		ArrayList<String> error = new ArrayList<String>();
		Validation valid = new Validation();
		Database db = new Database();
		DateTime dt = new DateTime();
		ComplaintID cid = new ComplaintID();
		SMS sms = new SMS();
		String sms_text;
		
		
		customer = (CustomerBean)session.getAttribute("customer");
		product = (ProductBean)session.getAttribute("product");
		String complain = request.getParameter("complain");
		String uname = session.getAttribute("username").toString();
		String time = dt.getCurrentTime();
		String date = dt.getCurrentDate();
		String id =cid.getComplaintId();
		
		if(valid.validateRequire(complain))
		{
			error.add("* Please Enter Complain");
		}
		
		
		if(error.isEmpty())
		{
			boolean flag=false;
			try 
			{
				flag = db.createComplain(id, customer, product, uname, complain);

				if(flag)
				{
					//error.add("Complaint Added Succesfully");
					session.setAttribute("complainerror", error);
					session.setAttribute("complainId", id);
					ForgetMessage msg = new ForgetMessage();
					sms_text="Your Complaint has Recived, We Resolve it Shortly. Your Complaint No :"+id;
					
					//if you sent the sms of generated complaint id then un comment this line and set way2sms uname or password in model.SMS.java file
					//sms.send(customer.getTelephoneNo(), sms_text);
				
					
					MyEmail.sendEmail(customer.getEmailId(),msg.getComplainSubject(),msg.getComplainMessage(id));
					response.sendRedirect("Operator/ComplaintDetails");
				}
				else
				{
					error.add("* Error");
					response.sendRedirect("Operator/CMS");
				}
			} 
			catch (Exception e) 
			{
				error.add(e.toString());
				session.setAttribute("complainerror", error);
				response.sendRedirect("Operator/CreateComplaint");
			}
			
		}
		else
		{
			session.setAttribute("complainerror", error);
			response.sendRedirect("Operator/CreateComplaint");
		}
			
	}

}
