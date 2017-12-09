<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@page import="model.UserBean"%>
<%@page import="model.Database"%>
<%@ page import="model.Cipher" %>
<%@ page import="java.util.*" %>
<%
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    
    Cipher cipher = new Cipher();
    Database db = new Database();

   
   
    if(request.getParameter("a")!=null && request.getParameter("b")!=null && request.getParameter("c")!=null)
    {
    	String url = "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/SCM?a="+request.getParameter("a")+"&b="+request.getParameter("b")+"&c="+request.getParameter("c");
    	session.setAttribute("url", url);
    	RequestDispatcher rd = request.getRequestDispatcher(cipher.alphaString(cipher.decryptString(request.getParameter("a"))));
    	rd.forward(request,response);
    }
    else
    	 if(session.getAttribute("login")!=null && session.getAttribute("role")!=null)
    		{
    	    	if(session.getAttribute("role").toString().equals("Administrator"))
    			{	
    				response.sendRedirect("Admin/CMS");
    			}
    			else if(session.getAttribute("role").toString().equals("Management Officer"))
    			{	
    				response.sendRedirect("Management/CMS");
    			}
    			else if(session.getAttribute("role").toString().equals("Service Engineer"))
    			{	
    				response.sendRedirect("ServiceEngineer/CMS");
    			}
    			else if(session.getAttribute("role").toString().equals("Operator"))
    			{	
    				response.sendRedirect("Operator/CMS");
    			}
    		}
    	     
   
	%>
    
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Service Call Management - CMS</title>

<link href="css/default.css" rel="stylesheet" type="text/css" />

<script src="jquery-latest.js"></script>
<script src="jquery.validate.js"></script>
<script type="text/javascript">
	$(document).ready(function(e) 
	{
        $('#loginform').validate(
        	{
			rules:
				{
					pswd:{required:true},
					uname:{required:true},
				},
					submitHandler: function(form) 
					{
  						form.submit();
					}
			});
    });
</script>

</head>

<body>

<div id="header">
	<div id="logo">
		<h1><a href="#">service call management </a></h1>
		<h2><a href="#">Call management system</a></h2>
	
	</div>
</div>

<div id="menu">
</br>
</div></br>
<marquee behavior="scroll" direction="left" scrollamount="3" style="color:red;font-weight:bold"><% if(application.getAttribute("notification")!=null){out.print(application.getAttribute("notification"));} %></marquee>

</br>
<br />
<br />


<div id="content" style="margin-left: 18%;">
	<div id="login" class="boxed" style="width:250px;text-align:center">
		<h2 class="title" style="text-align: left">Sign In Here</h2>
			
			<form id="loginform" name="login" method="post" action="AuthenticationUser">
				<fieldset>
					<legend>Sign-In</legend>
					<label for="inputtext1">User ID</label>
					<input class="mytextbox" type="text" name="uname" id="uname" value="<%if(session.getAttribute("uname")!=null){out.print(session.getAttribute("uname").toString());}%>" />
					
					<label for="inputtext2">Password</label>
					<input class="mytextbox" type="password" name="pswd" id="pswd" value="" /><br> 
					<input type="checkbox" name="remember" id="remember">&nbsp;Remember Me</input>
					</br></br>
					<input class="mybutton" type="submit" name="submit" id="submit" value="Sign In" style="text-align:center" /></br>   
					<%
						ArrayList<String> error = (ArrayList<String>)session.getAttribute("error");
	 
	 					if(error!=null && !error.equals(""))
	 					{
	       					Iterator<String> i = error.iterator();
	       
	      					while(i.hasNext())
	       					{
	       						out.println("<br><b><i><font size='2' face='Consolas' color=red>"+i.next()+"</font><i></b>");
	       					}
	      					session.setAttribute("error",null);
	 					}
	 			     %>
					<br></br><p><a href="Forget">Forgot your password?</a></br></br></p>
				</fieldset>
			</form>	     
	</div>
</div>

<jsp:include page="includes/footer.jsp" />
</body>
</html>
