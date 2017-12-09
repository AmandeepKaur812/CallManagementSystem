<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page import="model.Cipher"%>
<%@page import="model.UserBean"%>
<%@page import="model.Database,java.util.*"%>
<%
try
{
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    
    if(session.getAttribute("login")!=null)
	{
		response.sendRedirect("SCM");
	}
    
    Database db = new Database();
    UserBean olduser = new UserBean();
    Cipher cipher =new Cipher();
    
    olduser.setUname(cipher.alphaString(cipher.decryptString(request.getParameter("b"))));
    olduser.setPswd(cipher.decryptString(request.getParameter("c")));
    
    
    boolean flag = false;
    
    flag = db.checkLogin(olduser);
    session.setAttribute("folduser",olduser);

	if(flag)
	{
	%>
    
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Reset Password</title>

<link href="css/default.css" rel="stylesheet" type="text/css" />

<script src="jquery-latest.js"></script>
<script src="jquery.validate.js"></script>
<script type="text/javascript">
	$(document).ready(function(e) 
	{
        $('#resetme').validate(
        	{
			rules:
				{
					pswdf:{required:true,minlength:8},
					cpswdf:{required:true,equalTo:"#pswdf"}
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

</br>
<br />
<br />


<div id="content" style="margin-left: 18%;">
	<div id="login" class="boxed" style="width:250px;text-align:center">
			<h2 class="title" style="text-align:left">Reset Password</h2>
			
				<form method="post" action="ResetPassword" id="resetme">
					<fieldset>
					<legend>Reset Password</legend>
								
					<label for="inputtext2">New Password</label>
					<input class="mytextbox" type="password" id="pswdf" name="pswdf"/> 
				
					
					<label for="inputtext2">Confirm Password</label>
					<input class="mytextbox" type="password" id="cpswdf" name="cpswdf"/> 
					
					</br></br>
					<input class="mybutton" type="submit" style="width: 110px" name="submit" id="submit" value="Reset Password" style="text-align:center" /></br> 
					<%
						ArrayList<String> error = (ArrayList<String>)session.getAttribute("reseterror");
	 
	 					if(error!=null && !error.equals(""))
	 					{
	       					Iterator<String> i = error.iterator();
	       
	      					while(i.hasNext())
	       					{
	       						out.println("<br><b><i><font size='2' face='Consolas' color=red>"+i.next()+"</font><i></b>");
	       					}
	      					session.setAttribute("reseterror",null);
	 					}
	 			     %>
					<br></br>
					</fieldset>
				</form>	     
	</div>
</div>

<jsp:include page="includes/footer.jsp" />
</body>
</html>
<%
}
else
{
	response.sendRedirect("ExpiryPage.jsp");
}
}
catch(Exception e)
{
	//response.sendRedirect("SCM");
}
%>