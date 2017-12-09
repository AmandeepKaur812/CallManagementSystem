<%@page import="model.ComplaintBean"%>
<%@page import="model.CustomerBean"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="model.TaskManager"%>
<%@page import="model.UserBean"%>
<%@page import="model.Database"%>
<%@page import="java.io.*,java.util.*" %>
<%
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    UserBean user = new UserBean();
	user = (UserBean)session.getAttribute("user");
	CustomerBean customer = new CustomerBean();
	ComplaintBean complain = new ComplaintBean();
	Database db= new Database();
	session.setAttribute("navigation","1");
	ArrayList<String> tasks = new ArrayList<String>();
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

<div id="login" class="boxed" style="width:850px;text-align:center;min-height:500px">
		<h2 class="title" style="text-align:left">Asssignment Task</h2>
		
		<%
			if(request.getParameter("tab")==null)
			{
				response.sendRedirect("CMS?tab=assigned");
			}
		%>
		
		
		<center>
		<div id="menu">
		<ul>
		<li class="first"><a href="CMS?tab=assigned" class="mylinkbutton">Assigned</a></li>
		<li><a href="CMS?tab=inprogress" class="mylinkbutton">In Progress</a></li>
		<li><a href="CMS?tab=finished" class="mylinkbutton">Finished</a></li>
		</ul>	
		</div>	
		</center>
		<br><br>
	<%
	if(request.getParameter("tab")!=null)
	{
		if(request.getParameter("tab").equals("assigned"))
		{%>
			
		<h1><u>Assigned Tasks</u></h1>	
		<br></br>
		<center>
		<table>
		<%
			TaskManager task = new TaskManager();
		try
		{
			
		
			ResultSet rs = task.getTasks(user.getUname(),"Assigned");
			int i = 1;
			while(rs.next())
			{
				complain = db.getComplaintDetails(rs.getString(2));
				customer = db.getCustomerDetails(complain.getCustomerTelephone());
				out.print("<tr>");
				out.print("<td><label>"+i+"</label></td>");
				out.print("<td><label>"+customer.getName()+"</label></td>");
				out.print("<td><label>"+rs.getString(2)+"</label></td>");
				out.print("<td><a href='ComplaintDetails?complainID="+rs.getString(2)+"'><label>Details</label></a></td>");
				out.print("</tr>");
				
				
				i++;
			}
		}
		catch(Exception e)
		{
			out.print(e.toString());
		}
		}%>
		</table>	
		</center>
<%	}
	%>	
	
	<%
	if(request.getParameter("tab")!=null)
	{
		if(request.getParameter("tab").equals("inprogress"))
		{%>
			
		<h1><u>Tasks in Progress</u></h1>
		<br></br>
		<center>
		<table>
		<%
			TaskManager task = new TaskManager();
		try
		{
			
		
			ResultSet rs = task.getTasks(user.getUname(),"InProgress");
			int i = 1;
			while(rs.next())
			{
			
				out.print("<tr>");
				complain = db.getComplaintDetails(rs.getString(2));
				customer = db.getCustomerDetails(complain.getCustomerTelephone());
				out.print("<tr>");
				out.print("<td><label>"+i+"</label></td>");
				out.print("<td><label>"+customer.getName()+"</label></td>");
				out.print("<td><label>"+rs.getString(2)+"</label></td>");
				out.print("<td><a href='ProgressDetails?complainID="+rs.getString(2)+"'><label>Details</label></a></td>");
				out.print("</tr>");
				
				
				i++;
			}
		}
		catch(Exception e)
		{
			out.print(e.toString());
		}
		}%>
		</table>	
		</center>
	<% 	
		
	}
	%>	
	
	<%
	if(request.getParameter("tab")!=null)
	{
		if(request.getParameter("tab").equals("finished"))
		{%>
			
		<h1><u>Finshed Tasks</u></h1>
		<br></br>
		<center>
		<table>
		<%
			TaskManager task = new TaskManager();
		try
		{
			
		
			ResultSet rs = task.getTasks(user.getUname(),"finished");
			int i = 1;
			while(rs.next())
			{
				out.print("<tr>");
				complain = db.getComplaintDetails(rs.getString(2));
				customer = db.getCustomerDetails(complain.getCustomerTelephone());
				out.print("<tr>");
				out.print("<td><label>"+i+"</label></td>");
				out.print("<td><label>"+customer.getName()+"</label></td>");
				out.print("<td><label>"+rs.getString(2)+"</label></td>");
				out.print("<td><a href='FinishedDetails?complainID="+rs.getString(2)+"'><label>Details</label></a></td>");
				out.print("</tr>");
				
				
				i++;
			}
		}
		catch(Exception e)
		{
			out.print(e.toString());
		}
		}%>
		</table>	
		</center>	
			
<%
	}
%>		
	 </br></br>
	</div>
</div>


</div>


<div id="sidebar">

		<div id="partners" class="boxed">
			<h2 class="title">Account</h2>
			<div class="content">
				<ul>
					
					<li><a href="ChangePassword">Change Password</a></li>
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
