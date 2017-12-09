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
	if(session.getAttribute("product")==null || session.getAttribute("customer")==null)
	{
		response.sendRedirect("CMS");
	}
	else
	{
		product = (ProductBean)session.getAttribute("product");
		customer = (CustomerBean)session.getAttribute("customer");
	
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
        $('#complainform').validate(
        	{
			rules:
				{
					comlpain:{required:true,},
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
		<h2 class="title" style="text-align:left">Create Complain</h2>
		<br><br></br>
			<center>
			<table>
				<tr>
					<td align="right"><label>Customer Name :</label></td>
					<td><%=customer.getName() %></td>
				</tr>
				<tr>
					<td align="right"><label>Product Category :</label></td>
					<td><%=product.getCategory() %></td>
				</tr>
				<tr>
					<td align="right"><label>Product Serial No :</label></td>
					<td><%=product.getSerialNo() %></td>
				</tr>
				<tr>
					<td>
						<label>Complain :</label>
					</td>
					<td><form id = "complainform" action="../ComplaintCreator" method="post">
									<textarea rows="7"  class="mytextbox" name="complain" id="complain"></textarea>
					</td>
				</tr>
				<tr>
					<td><input class="mybutton" type="submit" name="submit" id="submit" value="Create" style="text-align:center" />
					</td>
					<td><a href="CMS" class="mylinkbutton">Cancel</a></td>
				</tr>  
			</form>
			</table>
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
					<li><a href="NewMessage">New Message</a></li>
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
<%

	}%>