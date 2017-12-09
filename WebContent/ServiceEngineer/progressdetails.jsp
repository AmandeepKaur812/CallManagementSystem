<%@page import="model.ComplaintBean"%>
<%@page import="model.WarrentyCheck"%>
<%@page import="model.CustomerBean"%>
<%@page import="model.ProductBean"%>
<%@page import="model.UserBean"%>
<%@page import="model.Database"%>
<%@page import="java.io.*,java.util.*" %>
<%
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    UserBean user = new UserBean();
	user = (UserBean)session.getAttribute("user");
	
	ProductBean product = new ProductBean();
	CustomerBean customer = new CustomerBean();
	Database db = new Database();
	ComplaintBean complain = new ComplaintBean();
	if(request.getParameter("complainID")!=null)
	{
		complain = db.getComplaintDetails(request.getParameter("complainID"));
		session.setAttribute("complain",complain);
	}
	else
	{
		complain = (ComplaintBean)session.getAttribute("complain");
	}
	
	if(!session.getAttribute("task").toString().equals("1"))
	{
		response.sendRedirect("CMS");	
	}
	product = db.getProductDetails(complain.getCustomerTelephone());
	customer = db.getCustomerDetails(complain.getCustomerTelephone());
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
<div id="login" class="boxed" style="width:850px;text-align:center;color:black;">
		<h2 class="title" style="text-align:left">Call Log</h2>
		<br><br></br>
			<center>
			<table>
				<tr>
					<td align="right"><label>Complain ID :</label></td>
					<td><%=complain.getComplaintID() %></td>
				</tr>
				<tr>
					<td align="right"><label>Customer Name :</label></td>
					<td><%=customer.getName() %></td>
				</tr>
				<tr>
					<td align="right"><label>Address :</label></td>
					<td><%=customer.getAddress() %></td>
				</tr>
				
				<tr>
					<td align="right"><label>Customer Telephone :</label></td>
					<td><%=customer.getTelephoneNo() %></td>
				</tr>
				<tr>
					<td align="right"><label>Product Serial No :</label></td>
					<td><%=product.getSerialNo() %></td>
				</tr>
				<tr>
					<td align="right"><label>Product Category :</label></td>
					<td><%=product.getCategory() %></td>
				</tr>
				<tr>
					<td align="right"><label>Complain Details :</label></td>
	
					<td><%= complain.getComplainDetail() %></td>
				</tr>
					
				<tr>
					<td align="right"><label>Date and Time :</label></td>
					<td><%=complain.getComplaintDate() %></td>
				</tr>
				<tr>
					<td align="right"><label>Root Cause:</label></td>
					<form action="../FinishComplaint" method="post">
					<td><select name="rootcause">
							<option>HARDISK_FAILURE</option>
							<option>MOTHERBORARD_FAILURE</option>
							<option>SMPS_FAILURE</option>
							<option>BATTERY_DAMAGE</option>
							<option>ADAPTER_FAILURE</option>
							<option>DISPLAY_FAILURE</option>
							<option>BLUETOOTH_FAILURE</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="right"><label>Solution :</label></td>
					
					<td><textarea name="solution" id="solution" rows="7"  class="mytextbox" ></textarea></td>
				</tr>
				<tr>
					<td>
					
						<input type="hidden" name="complainId" value="<%=complain.getComplaintID() %>"/>
						<input class="mybutton" type="submit" name="submit" value="Finish" style="text-decoration: none;color:black;"/>	
					</form>
					</td>
					<td>
						<a class="mylinkbutton" href="CMS" style="text-decoration: none;color:black;">Cancel</a>
					</td>
				</tr>
			</table>	
			<br></br>
			<%
						ArrayList<String> error = (ArrayList<String>)session.getAttribute("complainerror");
	 
	 					if(error!=null && !error.equals(""))
	 					{
	       					Iterator<String> i = error.iterator();
	       
	      					while(i.hasNext())
	       					{
	       						out.println("<br><b><i><font size='2' face='Consolas' color=red>"+i.next()+"</font><i></b>");
	       					}
	      					session.setAttribute("complainerror",null);
	 					}
	 			     %>
			
			
				</center>
					
					<br></br>			
		   
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
