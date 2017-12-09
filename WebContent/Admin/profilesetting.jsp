<%@page import="model.Cipher"%>
<%@page import="model.UserBean"%>
<%@page import="model.Database"%>
<%@page import="java.io.*,java.util.*" %>
<%
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    
	UserBean user = new UserBean();
	user = (UserBean)session.getAttribute("user");
	Cipher cipher = new Cipher();
	 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">


    
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Profile Setting</title>

<link href="../css/default.css" rel="stylesheet" type="text/css" />

<script src="../jquery-latest.js"></script>
<script src="../jquery.validate.js"></script>
<script type="text/javascript">
	$(document).ready(function(e) 
	{
        $('#profileme').validate(
        	{
			rules:
				{
					pfname:{required:true},
					plname:{required:true},
					pemail:{required:true,email:true},
					pmobile:{required:true,minlength:10},
					paddress:{required:true}
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
<div id="login" class="boxed" style="width:550px;text-align:center;height: 650px">

			<h2 class="title" style="text-align:left">Profile</h2>
<br></br><br></br>		
				<a href="ProfilePic"><img src="ProPic/<%=  user.getProfilePicture() %>" height="150px" width="150px"/>	</a>
				<form method="post" action="../ProfileUpdate" id="profileme">
					<fieldset>
					<legend>Profile</legend>
					
					
					<br>
					<center>
					<table>
					<tbody align="right">
					
					<tr>
						<td><label>FirstName :</label></td>
						<td><input class="mytextbox" type="text" name="pfname" id="pfname" style="color:black;" value="<%= user.getFname() %>" /></td>
					</tr>
					
					<tr>
						<td><label>LastName :</label></td>
						<td><input class="mytextbox" type="text" name="plname" id="plname" value="<%= user.getLname() %>" /></td>
					</tr>
					
					<tr>
						<td><label>Email ID :</label></td>
						<td><input class="mytextbox" type="text" name="pemail" id="pemail" value="<%= user.getEmail() %>" /></td>
					</tr>
					
					<tr>
						<td><label>Mobile No :</label></td>
						<td><input class="mytextbox" type="text" name="pmobile" id="pmobile" value="<%= user.getMobile() %>" /></td>
					</tr>
					
					<tr>
						<td><label>Address :</label></td>
						<td><textarea class="mytextbox" name="paddress" id="paddress"><%= user.getAddress() %></textarea></td>
					</tr>
					
					<tr>
					<td align="center" colspan="2"><input class="mybutton" type="submit" name="submit" id="submit" value="Save" style="text-align:center" />
								
					</fieldset>
				</form>	   
					<button class="mybutton"><a href="CMS">Cancel</a></button></td>
					</tr>
					</tbody>
					</table>
					<%
						ArrayList<String> error = (ArrayList<String>)session.getAttribute("profileerror");
	 
	 					if(error!=null && !error.equals(""))
	 					{
	       					Iterator<String> i = error.iterator();
	       
	      					while(i.hasNext())
	       					{
	       						out.println("<br><b><i><font size='2' face='Consolas' color=red>"+i.next()+"</font><i></b>");
	       					}
	      					session.setAttribute("profileerror",null);
	 					}
	 			     %>
					</center>
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
