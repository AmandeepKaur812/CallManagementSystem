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

import model.CustomerBean;
import model.Database;
import model.ProductBean;
import model.Validation;


@WebServlet("/CustomerSearch")
public class CustomerSearch extends HttpServlet 
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
		customer.setTelephoneNo(request.getParameter("teleno"));
		Validation valid = new Validation();
		Database db = new Database();
		PrintWriter out  = response.getWriter();
		ArrayList<String> error = new ArrayList<String>();
		
		if(valid.validateRequire(customer.getTelephoneNo()))
		{
			error.add("* Please Enter Customer's Contact No");
		}
		else if(customer.getTelephoneNo().length()!= 10)
		{
			error.add("* Please Enter Valid Contact Number");
		}
		
		if(error.isEmpty())
		{
			
			try 
			{
				if(db.isCustomerExist(customer.getTelephoneNo()))
				{
					customer = db.getCustomerDetails(customer.getTelephoneNo());
					product = db.getProductDetails(customer.getTelephoneNo());
					session.setAttribute("product", product);
					session.setAttribute("customer",customer);
					response.sendRedirect("Operator/CustomerDetails");
				}
				else
				{
					error.add("No Customer Found");
					session.setAttribute("complainerror", error);
					response.sendRedirect("Operator/CMS");
				}
			} 
			catch (Exception e) 
			{
				
			}
		}
		else
		{
			response.sendRedirect("Operator/CMS");
		}
	}
}
