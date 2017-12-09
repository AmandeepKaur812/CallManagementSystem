package myFilters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Cipher;
import model.Database;
import model.UserBean;


@WebFilter("/CookieChecker")
public class CookieChecker implements Filter {

   
    public CookieChecker() 
    {
   
    }


	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
		 Cipher cipher = new Cipher();
		 Database db = new Database();
		 UserBean user = new UserBean();
		    
		   HttpSession session = ((HttpServletRequest) request).getSession();	
		   Cookie c[] = ((HttpServletRequest) request).getCookies();
		    
		    if(c!=null)
		    {
		    	for (int i = 0; i < c.length; i++)
		    	{
		    		/*if(c[i].getName().equals("login_uname"))
		    		{
		    			user.setUname(cipher.alphaString(cipher.decryptString(c[i].getValue())));
		    		}
		    	
		    		if(c[i].getName().equals("login_status"))
		    		{
		    			user.setPswd(cipher.alphaString(cipher.decryptString(c[i].getValue())));
		    		}
		    		if(c[i].getName().equals("login_pswd"))
		    		{
		    			user.setRole(cipher.alphaString(cipher.decryptString(c[i].getValue())));
		    		}*/
		    		
		    		
		    	}	
		    	
				try 
				{
					if(db.checkLogin(user))
					{
						session.setAttribute("login","1");
						session.setAttribute("role", user.getRole());
						session.setAttribute("username",user.getUname());
			
					}
				} 
				catch (Exception e) 
				{}
				try
				{
					user = db.selectUser(session.getAttribute("username").toString());
				}
				catch(Exception e)
				{}
				session.setAttribute("user",user);
		    }
		chain.doFilter(request, response);
	}

	
	public void init(FilterConfig fConfig) throws ServletException 
	{
		
	}

}
