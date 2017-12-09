<%@page import="model.UserBean"%>
<%@page import="model.Database"%>
<%@page import="java.io.*,java.util.*" %>
<%
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    
	UserBean user = new UserBean();
	user = (UserBean)session.getAttribute("user");
	 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">


    
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Home</title>

<link href="../css/default.css" rel="stylesheet" type="text/css" />

<script src="../jquery-latest.js"></script>
<script src="../jquery.validate.js"></script>
<script type="text/javascript">
	$(document).ready(function(e) 
	{
        $('#regme').validate(
        	{
			rules:
				{
					rfname:{required:true},
					rlname:{required:true},
					remail:{required:true,email:true}
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
	<div id="topmenu">
		<ul>
			<li><a href="#" id="topmenu1" accesskey="1" title="">Welcome &nbsp;&nbsp;<%=user.getFname()%>&nbsp;<%=user.getLname()%></a></li>
			<li><a href="../Logout" id="topmenu3" accesskey="2" title="">Logout</a></li>
		</ul>
	</div>
	<div id="logo">
		<h1><a href="#">service call management </a></h1>
		<h2><a href="#">Call management system</a></h2>
	</div>
</div>


<div id="menu">
	<ul>
		<li class="first"><a href="CMS" title="">Home</a></li>
		<li><a href="Profile" title="">Setting</a></li>
	
	</ul>
	</div></br>
	
<marquee behavior="scroll" direction="left" scrollamount="3" style="color:red;font-weight:bold"><% if(application.getAttribute("notification")!=null){out.print(application.getAttribute("notification"));} %></marquee>

</br>
<br />
<br />



<div id="content">
<div id="login" class="boxed" style="width:450px;text-align:center;height: 450px">

			<h2 class="title" style="text-align:left">Register New User</h2>
<br></br><br></br>			
				<form method="post" action="../Register" id="regme">
					<fieldset>
					<legend>Register User</legend>
					<label for="inputtext1">FirstName</label>
					<input class="mytextbox" type="text" name="rfname" id="rfname" value="" />
					
					<label for="inputtext1">LastName</label>
					<input class="mytextbox" type="text" name="rlname" id="rlname" value="" />
					
					<label for="inputtext1">Email ID</label>
					<input class="mytextbox" type="text" name="remail" id="remail" value="" />
					
					<label for="inputtext1">Role</label>
					
					<select name="rrole">
						<option name="op">Operator</option>>
						<option name="se">Service Engineer</option>>
						<option name="mis">Management Officer</option>>
						<option name="admin">Administrator</option>>
					</select>
					
					<span style="color: red;font-size:15px;"></span>
					<br><br>
					<input class="mybutton" type="submit" name="submit" id="submit" value="Register" style="text-align:center" /></br></br>   
								
					</fieldset>
				</form>	   
				<button class="mybutton"><a href="CMS">Cancel</a></button>  
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
	</div>
</div>


<div id="sidebar">

		<div id="partners" class="boxed">
			<h2 class="title">Account</h2>
			<div class="content">
				<ul>
					
					<li><a href="ChangePassword">Change Password</a></li>
					<li><a href="ImportData">Import Data</a></li>
					<li><a href="AddUser">Register New User</a></li>
					<li><a href="SetNotification">Set Notification</a></li>
				</ul>
			</div>
		</div>
	</div>

</div>
</div>
<br /><br /><br /></br><br /><br /></br><br /><br /></br><br /><br /></br><br /><br /></br><br /><br />


<jsp:include page="../includes/footer.jsp" />

</body>
</html>
