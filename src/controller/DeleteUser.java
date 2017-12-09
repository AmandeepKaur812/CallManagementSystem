package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

import model.Database;


@WebServlet("/DeleteUser")
public class DeleteUser extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String id = request.getParameter("deleteid");
		String role = request.getParameter("roleid");
	
		try 
		{
			Connection con = Database.getConnection();
			if(role.equals("Service Engineer"))
			{
				String sql1 = "delete from assignment where service_eng_uname=?";
				PreparedStatement st1 = con.prepareStatement(sql1);
				st1.setString(1,id);
				st1.executeUpdate();
				
				String sql2 = "delete from expertization where uname=?";
				PreparedStatement st2 = con.prepareStatement(sql2);
				st2.setString(1,id);
				st2.executeUpdate();
			}
			
				String sql = "delete from user where uname=?";
				PreparedStatement st = con.prepareStatement(sql);
				st.setString(1,id);
				st.executeUpdate();
			
			
			response.sendRedirect("Admin/CMS?page=0&tab="+role);
		} 
		catch (Exception e) 
		{
			
		}
		
		
		
	}
}
