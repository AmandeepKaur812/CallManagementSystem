package myFilters;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Database;
import model.UserBean;

/**
 * Servlet Filter implementation class ManagementLoginChecker
 */
@WebFilter("/ManagementLoginChecker")
public class ManagementLoginChecker implements Filter {

	public ManagementLoginChecker() 
    {}

	public void destroy() 
	{}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		ArrayList<String> error = new ArrayList<String>();
		
		if(session.getAttribute("login")!=null)
		{
			String role = session.getAttribute("role").toString();
			if(role.equals("Management Officer"))
			{
				Database db = new Database();
				UserBean user = new UserBean();
				try
				{
					user = db.selectUser(session.getAttribute("username").toString());
				}
				catch(Exception e)
				{}
				session.setAttribute("user",user);
				chain.doFilter(request, response);
			}
			else
			{
				if(role.equals("Administrator"))
				{
					res.sendRedirect("../Admin/CMS");
				}
				else if(role.equals("Service Engineer"))
				{
					res.sendRedirect("../ServiceEngineer/CMS");
				}
				else if(role.equals("Operator"))
				{
					res.sendRedirect("../Operator/CMS");
				}
				else
				{	error.add("Unauthorised User");
					session.setAttribute("error", error);
					session.setAttribute("login",null);
					res.sendRedirect("../SCM");	
				}
			}
		}
		else
		{
			res.sendRedirect("../SCM");
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {}
}
