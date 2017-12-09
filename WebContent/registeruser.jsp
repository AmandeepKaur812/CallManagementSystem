<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page import="model.Cipher"%>
<%@page import="model.UserBean"%>
<%@page import="model.Database,java.util.*"%>
<%try
{
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    
    
    Database db = new Database();
    UserBean olduser = new UserBean();
    Cipher cipher =new Cipher();
    
    
    
    olduser.setUname(cipher.decryptString(request.getParameter("b")));
    olduser.setPswd(cipher.decryptString(request.getParameter("c")));
    
    
    boolean flag = false;
    
    flag = db.checkLogin(olduser);
    olduser = db.selectUser(cipher.decryptString(request.getParameter("b")));
    session.setAttribute("olduser",olduser);

	if(flag)
	{
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
        $('#regmeuser').validate(
        	{
			rules:
				{
					unamer:{required:true},
					pswdr:{required:true,minlength:8},
					cpswdr:{required:true,equalTo:"#pswdr"}
				},
					submitHandler: function(form) 
					{
  						form.submit();
					}
			});
    });
</script>

<script language="javascript">
	function check()
	{
		
	var xmlhttp;
	if(window.XMLHttpRequest)
		{
			xmlhttp=new XMLHttpRequest();
		
		}
	else
		{
			
			xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
	
	xmlhttp.onreadystatechange=function()
	{
		
		if(xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			
			if(xmlhttp.responseText=="1" && document.getElementById("unamer").value!=null)
			{
				document.getElementById("error").innerHTML = "<b><i><font size='2' face='Consolas' color=red>* UserName already Exist</font><i></b>";		
			}
			else
			{
				document.getElementById("error").innerHTML = "<b><i><font size='2' face='Consolas' color=green>* UserName Available</font><i></b>";
			}
		}
		
	};
	var uname = document.getElementById("unamer").value;
	xmlhttp.open("post","UserValidation",true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp.send("runamer="+uname);
	}
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
			<h2 class="title" style="text-align:left">Complete Sign up</h2>
			
				<form id="regmeuser" method="post" action="RegisterUser">
					<fieldset>
					<legend>Complete Sign up</legend>
					<label for="inputtext1">Set User ID</label>
					<input class="mytextbox" type="text" id="unamer" name="unamer" onblur="check()" />
					<p id="error" ></p>
					
					<label for="inputtext2">Password</label>
					<input class="mytextbox" type="password" id="pswdr" name="pswdr"/> 
					
					
					<label for="inputtext2">Confirm Password</label>
					<input class="mytextbox" type="password" id="cpswdr" name="cpswdr"/> <br>
					
					
					<% if(olduser.getRole().equals("Service Engineer"))
						{
					%>	
					<label>Skill</label>
					<select name="skill">
						<option>Laptop</option>
						<option>Desktop</option>
					</select>
					<%
						}
					%>
				
					</br></br>
					<input class="mybutton" type="submit" id="submit" name="submit" value="Register" style="text-align:center" /></br>   
				
					
					</fieldset>
				</form>	     
				<%
						ArrayList<String> error = (ArrayList<String>)session.getAttribute("regerror");
	 
	 					if(error!=null && !error.equals(""))
	 					{
	       					Iterator<String> i = error.iterator();
	       
	      					while(i.hasNext())
	       					{
	       						out.println("<br><b><i><font size='2' face='Consolas' color=red>"+i.next()+"</font><i></b>");
	       					}
	      					session.setAttribute("regerror",null);
	 					}
	 			     %>
	 			     <br></br>
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
	response.sendRedirect("SCM");
}
%>