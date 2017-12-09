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
<%
			Database db = new Database();

			if(request.getParameter("tab")!=null || request.getParameter("page")!=null)
			{
			
		%>


<div id="content">
<div id="login" class="boxed" style="width:850px;text-align:center;min-height:600px">
		<h2 class="title" style="text-align:left">Users</h2>
		<center>
		<div id="menu">
		<ul>
		<li class="first"><a href="CMS?tab=Operator&page=0" class="mylinkbutton">Operator</a></li>
		<li><a href="CMS?tab=Service Engineer&page=0" class="mylinkbutton">Service Engineer</a></li>
		<li><a href="CMS?tab=Management&page=0" class="mylinkbutton">Management</a></li>
		<li><a href="CMS?tab=Administrator&page=0" class="mylinkbutton">Administrator</a></li>
		</ul>	
		</div>	
		</center>
		<br><br>
		
		<% 
		if(request.getParameter("tab").equals("Operator"))
			{
				ResultSet op_rs = db.getOperators();
				
				 op_rs.last();
	             int Orows = op_rs.getRow();
	             op_rs.beforeFirst();
	                
	             int Opages = Orows/10;
	                
	             if(Orows%10!=0)
	              {
	                 Opages++;
	              }
		%>
		<center></center>
			<table  cellpadding ="-1" style="color:black">
			<tbody align="center">
                <tr>
                <th style="width: 70px">No</th>
                 <th style="width: 120px">Name</th>
                 <th style="width: 350px">Role</th>
                 <th style="width: 450px">Email Id</th>
                 <th style="width: 450px">Delete</th>
            </tr>
            <tr>
                <td colspan="5"><hr style="height: 2px;background-color: black;padding: 0px"></td>
            </tr>
            <%
                int j=0;
                int Opstart =Integer.parseInt(request.getParameter("page"));
            int o=1;    
           while(op_rs.next() && j<Orows+1)
            {    
               if(j>=Opstart*10 && j<((Opstart+1)*10))
                {
            %>
            <tr>
                <td><%= o %></td>
                <td><%=op_rs.getString(2) %>&nbsp;&nbsp;<%=op_rs.getString(3)%></td>
                <td><%=op_rs.getString(6) %></td>
                <td><%=op_rs.getString(7) %></td>
                <td><form action="../DeleteUser" method="post"><input type="hidden" name="deleteid" value="<%= op_rs.getString(4) %>"><input type="hidden" name="roleid" value="Operator"></input><input class="mybutton" type="submit" value="Delete"></input></form></td>
            </tr>
            <tr>
                <td colspan="5"><hr style="padding: 0px"></td>
            </tr>
           
            <%
                 }
                    
               j++;
               o++;
                    
             }
            
             %>
             </tbody>
        </table>
        </center>
        <br>
             <% 
                    for(j=0;j<Opages;j++)
                    {%>
                    <a href="CMS?tab=Operator&page=<%=j%>" class="pagenum">&nbsp;<%=j+1%>&nbsp;</a>
                    <%}
                    
			}
		%>
		
		
		<% 
		if(request.getParameter("tab").equals("Service Engineer"))
			{
				ResultSet se_rs = db.getServiceEngineer();
				
				 se_rs.last();
	             int Srows = se_rs.getRow();
	             se_rs.beforeFirst();
	                
	             int Spages = Srows/10;
	                
	             if(Srows%10!=0)
	              {
	                 Spages++;
	              }
		%>
		<center></center>
			<table  cellpadding ="-1" style="color:black">
			<tbody align="center">
                <tr>
                <th style="width: 70px">No</th>
                 <th style="width: 120px">Name</th>
                 <th style="width: 350px">Role</th>
                 <th style="width: 450px">Email Id</th>
                 <th style="width: 450px">Delete</th>
            </tr>
            <tr>
                <td colspan="5"><hr style="height: 2px;background-color: black;padding: 0px"></td>
            </tr>
            <%
                int j=0;
                int Spstart =Integer.parseInt(request.getParameter("page"));
            int o=1;    
           while(se_rs.next() && j<Srows+1)
            {    
               if(j>=Spstart*10 && j<((Spstart+1)*10))
                {
            %>
            <tr>
                <td><%= o %></td>
                <td><%=se_rs.getString(2) %>&nbsp;&nbsp;<%=se_rs.getString(3)%></td>
                <td><%=se_rs.getString(6) %></td>
                <td><%=se_rs.getString(7) %></td>
                <td><form action="../DeleteUser" method="post"><input type="hidden" name="deleteid" value="<%= se_rs.getString(4) %>"><input type="hidden" name="roleid" value="Service Engineer"></input><input class="mybutton" type="submit" value="Delete"></input></form></td>
            </tr>
            <tr>
                <td colspan="5"><hr style="padding: 0px"></td>
            </tr>
           
            <%
                 }
                    
               j++;
               o++;
                    
             }
            
             %>
             </tbody>
        </table>
        </center>
        <br>
             <% 
                    for(j=0;j<Spages;j++)
                    {%>
                    <a href="CMS?tab=Operator&page=<%=j%>" class="pagenum">&nbsp;<%=j+1%>&nbsp;</a>
                    <%}
                    
			}
		%>
		
		<% 
		if(request.getParameter("tab").equals("Management"))
			{
				ResultSet mn_rs = db.getManagement();
				
				 mn_rs.last();
	             int Mrows = mn_rs.getRow();
	             mn_rs.beforeFirst();
	                
	             int Mpages = Mrows/10;
	                
	             if(Mrows%10!=0)
	              {
	                 Mpages++;
	              }
	             
	        
		%>
		
		<center></center>
			<table  cellpadding ="-1" style="color:black">
			<tbody align="center">
                <tr>
                <th style="width: 70px">No</th>
                 <th style="width: 120px">Name</th>
                 <th style="width: 350px">Role</th>
                 <th style="width: 450px">Email Id</th>
                 <th style="width: 450px">Delete</th>
            </tr>
            <tr>
                <td colspan="5"><hr style="height: 2px;background-color: black;padding: 0px"></td>
            </tr>
            <%
                int j=0;
                int Mpstart =Integer.parseInt(request.getParameter("page"));
            int o=1;    
           while(mn_rs.next() && j<Mrows+1)
            {   
               if(j>=Mpstart*10 && j<((Mpstart+1)*10))
                {
            %>
            
            <tr>
                <td><%= o %></td>
                <td><%=mn_rs.getString(2) %>&nbsp;&nbsp;<%=mn_rs.getString(3)%></td>
                <td><%=mn_rs.getString(6) %></td>
                <td><%=mn_rs.getString(7) %></td>
                <td><form action="../DeleteUser" method="post"><input type="hidden" name="deleteid" value="<%= mn_rs.getString(4) %>"><input type="hidden" name="roleid" value="Management"></input><input class="mybutton" type="submit" value="Delete"></input></form></td>
            </tr>
            <tr>
                <td colspan="5"><hr style="padding: 0px"></td>
            </tr>
           
            <%
                 }
                    
               j++;
               o++;
                    
             }
            
             %>
             </tbody>
        </table>
        </center>
        <br>
             <% 
                    for(j=0;j<Mpages;j++)
                    {%>
                    <a href="CMS?tab=Operator&page=<%=j%>" class="pagenum">&nbsp;<%=j+1%>&nbsp;</a>
                    <%}
                    
			}
		%>
		
		<% 
		if(request.getParameter("tab").equals("Administrator"))
			{
				ResultSet ad_rs = db.getAdministrator();
				
				 ad_rs.last();
	             int Arows = ad_rs.getRow();
	             ad_rs.beforeFirst();
	                
	             int Apages = Arows/10;
	                
	             if(Arows%10!=0)
	              {
	                 Apages++;
	              }
		%>
		<center></center>
			<table  cellpadding ="-1" style="color:black">
			<tbody align="center">
                <tr>
                <th style="width: 70px">No</th>
                 <th style="width: 120px">Name</th>
                 <th style="width: 350px">Role</th>
                 <th style="width: 450px">Email Id</th>
                 <th style="width: 450px">Delete</th>
            </tr>
            <tr>
                <td colspan="5"><hr style="height: 2px;background-color: black;padding: 0px"></td>
            </tr>
            <%
                int j=0;
                int Opstart =Integer.parseInt(request.getParameter("page"));
            int o=1;    
           while(ad_rs.next() && j<Arows+1)
            {    
               if(j>=Opstart*10 && j<((Opstart+1)*10))
                {
            %>
            <tr>
                <td><%= o %></td>
                <td><%=ad_rs.getString(2) %>&nbsp;&nbsp;<%=ad_rs.getString(3)%></td>
                <td><%=ad_rs.getString(6) %></td>
                <td><%=ad_rs.getString(7) %></td>
                <td><form action="../DeleteUser" method="post"><input type="hidden" name="deleteid" value="<%= ad_rs.getString(4) %>"><input type="hidden" name="roleid" value="Operator"></input><input class="mybutton" type="submit" value="Delete"></input></form></td>
            </tr>
            <tr>
                <td colspan="5"><hr style="padding: 0px"></td>
            </tr>
           
            <%
                 }             
               j++;
               o++;             
             }
            
             %>
             </tbody>
        </table>
        </center>
        <br>
             <% 
                    for(j=0;j<Apages;j++)
                    {%>
                    <a href="CMS?tab=Operator&page=<%=j%>" class="pagenum">&nbsp;<%=j+1%>&nbsp;</a>
                    <%}
                    
			}
		%>
		
		
	</div>
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
<%
			}
else
{
response.sendRedirect("CMS?tab=Operator&page=0");
}

%>
