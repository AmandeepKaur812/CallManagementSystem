<%@page import="model.UserBean"%>
<%@page import="model.Database"%>
<%@page import="java.io.*,java.sql.*" %>
<%
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    UserBean user = new UserBean();
	user = (UserBean)session.getAttribute("user");
	//out.println("Session ID: "+session.getId());
	 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">  
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />

<title>Home</title>
<link href="../css/default.css" rel="stylesheet" type="text/css" />
</head>

<body>

<div id="header">
	<div id="topmenu">
		<ul>
			<li><a href="#" id="topmenu1" accesskey="1" title="">Welcome &nbsp;&nbsp;<%=user.getFname() %>&nbsp;<%=user.getLname() %></a></li>
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
<div id="login" class="boxed" style="width:850px;text-align:center;min-height:600px">
		<h2 class="title" style="text-align:left">Users</h2>
		<form action="SetNotification" method="post">
		<textarea rows="5" cols="80" name="notification"></textarea></br>
		<input type="submit" value="Set Notification"></input>
		</form>
		<%
	if(request.getParameter("notification")!=null)
	{
		application.setAttribute("notification",request.getParameter("notification"));
		out.print("<br><b><i><font size='2' face='Consolas' color=red>Notification Seted</font><i></b>");
	}
%>
	</div>
</div><br></br>



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
