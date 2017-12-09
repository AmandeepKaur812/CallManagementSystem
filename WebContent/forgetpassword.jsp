<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page import="model.Cipher"%>
<%@page import="model.UserBean"%>
<%@page import="model.Database,java.util.*"%>
<%
	if(session.getAttribute("login")!=null)
	{
		response.sendRedirect("SCM");
	}
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
%>
    
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Forget Password</title>

<link href="css/default.css" rel="stylesheet" type="text/css" />

<script src="jquery-latest.js"></script>
<script src="jquery.validate.js"></script>
<script type="text/javascript">
	$(document).ready(function(e) 
	{
        $('#forgetpswd').validate(
        	{
			rules:
				{
					funame:{required:true},
					femail:{required:true,email:true}
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
			<h2 class="title" style="text-align:left">Forget Password</h2>
			
			<form action="ForgetPassword" method="post" id="forgetpswd">
				<fieldset>
					<legend>Forget Password</legend>
					<label for="inputtext1">User ID</label>
					<input type="text" name="funame" id="funame" class="mytextbox" value="<%if(session.getAttribute("funame")!=null){out.print(session.getAttribute("funame").toString());}%>" />
					
					<label for="inputtext1">Email</label>
					<input type="text" name="femail" id="femail" class="mytextbox" value="<%if(session.getAttribute("femail")!=null){out.print(session.getAttribute("femail").toString());}%>"/>
														
					</br></br>
					<input class="mybutton" style="width: 90px;" type="submit" name="submit" id="submit" value="Get Password" style="text-align:center"  /><br>
					<%
						ArrayList<String> error = (ArrayList<String>)session.getAttribute("forgetpassworderror");
	 
	 					if(error!=null && !error.equals(""))
	 					{
	       					Iterator<String> i = error.iterator();
	       
	      					while(i.hasNext())
	       					{
	       						out.println("<br><b><i><font size='2' face='Consolas' color=red>"+i.next()+"</font><i></b>");
	       					}
	      					session.setAttribute("forgetpassworderror",null);
	 					}
	 			     %> 	
	 			     <br></br>
	 			     <a href="SCM">Go back to Home Page</a>
	 			     <br></br>			
				</fieldset>
			</form>	     
	</div>
</div>

<jsp:include page="includes/footer.jsp" />
</body>
</html>
