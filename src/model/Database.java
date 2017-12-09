package model;

import java.sql.*;
import java.util.Date;

public class Database 
{
	
	//This Method is Use for the Get a New Connection From Database
	public static  Connection getConnection() 
	{
		Connection con=null;
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver Loaded Successfully");
		}catch(ClassNotFoundException exp)
		{
			System.out.println("Driver Not Loaded Successfully");
		}
		try
		{
		 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cms","root","aman123kaur");
		System.out.println("DataBase connected succefully");
		}
		catch(SQLException ex)
		{
			System.out.println("Database not connected Successfully");
		}
		return con;
	}
	


	public ResultSet getOperators() throws Exception
	{
		Connection con = getConnection();  //calling connection
		Statement st = con.createStatement(); // it will create statement
		ResultSet rs = st.executeQuery("select * from user where role='Operator'");
		return rs;
	}
	public ResultSet getServiceEngineer() throws Exception
	{
		Connection con = getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from user where role='Service Engineer'");
		return rs;
	}
	public ResultSet getManagement() throws Exception
	{
		Connection con = getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from user where role='Management Officer'");
		return rs;
	}
	public ResultSet getAdministrator() throws Exception
	{
		Connection con = getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from user where role='Administrator'");
		return rs;
	}
	
	public boolean addUser(UserBean user) throws Exception
	{
		boolean flag=false;
		
		Connection con = getConnection();
		String sql = "INSERT INTO user(fname,lname,uname,pswd,role,email,profile_picture)VALUES(?,?,?,?,?,?,?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1,user.getFname().trim());
		st.setString(2,user.getLname().trim());
		st.setString(3,user.getUname().trim());
		st.setString(4,user.getPswd().trim());
		st.setString(5, user.getRole().trim());
		st.setString(6,user.getEmail().trim());
		st.setString(7,"propic.jpg");

		int i = st.executeUpdate();
		if(i!=0)
		{
			flag = true;
		}
		return flag;
	}
	
	public boolean addUnAssignedUser(UserBean user) throws Exception
	{
		boolean flag=false;
		Connection con = getConnection();
		String sql = "INSERT INTO assignment(complain_id,service_eng_uname,assign_status,task_count)VALUES(?,?,?,?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1,"no_complaint");
		st.setString(2,user.getUname().trim());
		st.setString(3,"not_assigned");
		st.setString(4,"0");
		

		int i = st.executeUpdate();
		if(i!=0)
		{
			flag = true;
		}
		return flag;
		
	}
	
	public boolean checkLogin(UserBean user) throws Exception
	{
		boolean flag=false;
		
		Connection con = getConnection();
		String sql = "SELECT COUNT(uname) FROM user WHERE uname=? AND pswd=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1,user.getUname().trim());
		st.setString(2,user.getPswd().trim());
		ResultSet rs = st.executeQuery();
		while(rs.next())
		{
			int n = rs.getInt(1);
			if(n>0)
			{
				flag=true;
			}
			else
			{
				flag=false;
			}
		}
		return flag;
	}
	
	public boolean isValidPassword(String oldpswd) throws Exception
	{
		boolean flag=false;
		Connection con  = getConnection();
		String sql="select Count(pswd) from user where pswd=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, oldpswd.trim());
		ResultSet rs = st.executeQuery();
		while(rs.next())
		{
			int n = rs.getInt(1);
			if(n>0)
			{
				flag=true;
			}
			else
			{
				flag=false;
			}
		}
		return flag;
	}
	
	public boolean changePassword(String oldpswd,String newpswd) throws Exception
	{
		Connection con  = getConnection();
		String sql="update user set pswd=? where pswd=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, newpswd.trim());
		st.setString(2, oldpswd.trim());
		int i = st.executeUpdate();
		if(i>0)
		{
			return	true;
		}
		else
		{
			return false;
		}
	}
	
	
	public boolean checkUser(UserBean user) throws Exception
	{
		boolean flag=false;
		
		Connection con = getConnection();
		String sql = "SELECT COUNT(uname) FROM user WHERE uname=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1,user.getUname().trim());
		ResultSet rs = st.executeQuery();
		rs.first();
		int n = rs.getInt(1);
		if(n>0)
		{
			flag=true;
		}
		else
		{
			flag=false;
		}
		
		return flag;
	}
	
	public UserBean selectUser(String uname) throws Exception
	{
		UserBean user = new UserBean();
		Connection con = getConnection();
		String sql = "SELECT * FROM user WHERE uname=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1,uname.trim());
		ResultSet rs = st.executeQuery();
		while(rs.next())
		{
			user.setUid(rs.getInt(1));
			user.setFname(rs.getString(2));
			user.setLname(rs.getString(3));
			user.setUname(rs.getString(4));
			user.setPswd(rs.getString(5));
			user.setRole(rs.getString(6));
			user.setEmail(rs.getString(7));
			user.setMobile(rs.getString(8));
			user.setAddress(rs.getString(9));
			user.setProfilePicture(rs.getString(10));
		}
		return user;
	}
	
	public boolean registerUser(UserBean old,UserBean latest) throws Exception
	{
		boolean flag=false;
		Connection con = getConnection();
		String sql = "UPDATE user set uname=?,pswd=? WHERE uname=? AND pswd=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1,latest.getUname().trim());
		st.setString(2,latest.getPswd().trim());
		st.setString(3,old.getUname().trim());
		st.setString(4,old.getPswd().trim());
		int i = st.executeUpdate();
		if(i>0)
		{
			flag=true;
		}
		else
		{
			flag=false;
		}
		return flag;
	}
	
	public boolean checkForget(UserBean user) throws Exception
	{
		boolean flag=false;
		
		Connection con = getConnection();
		String sql = "SELECT COUNT(uname) FROM user WHERE uname=? AND email=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1,user.getUname().trim());
		st.setString(2,user.getEmail().trim());
		ResultSet rs = st.executeQuery();
		while(rs.next())
		{
			int n = rs.getInt(1);
			if(n>0)
			{
				flag=true;
			}
			else
			{
				flag=false;
			}
		}
		return flag;
	}
	
	public boolean forgetUser(UserBean user,String pswd) throws Exception
	{
		boolean flag=false;
		Connection con = getConnection();
		String sql = "UPDATE user set pswd=? WHERE uname=? AND email=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1,pswd);
		st.setString(2,user.getUname().trim());
		st.setString(3,user.getEmail().trim());
		
		int i = st.executeUpdate();
		if(i>0)
		{
			flag=true;
		}
		else
		{
			flag=false;
		}
		return flag;
	}
	
	public boolean regainUser(UserBean old,UserBean latest) throws Exception
	{
		boolean flag=false;
		Connection con = getConnection();
		String sql = "UPDATE user set pswd=? WHERE uname=? AND pswd=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1,latest.getPswd().trim());
		st.setString(2,old.getUname().trim());
		st.setString(3,old.getPswd().trim());
		int i = st.executeUpdate();
		if(i>0)
		{
			flag=true;
		}
		else
		{
			flag=false;
		}
		return flag;
	}
	
	public String userRole(UserBean user) throws Exception
	{
		String role=null;
		Connection con = getConnection();
		String sql = "select role from user WHERE uname=? AND pswd=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1,user.getUname().trim());
		st.setString(2,user.getPswd().trim());
		ResultSet rs = st.executeQuery();
		while(rs.next())
		{
			role =rs.getString("role");
		}
		
		return role;
	}
	
	public boolean isCustomerExist(String teleno) throws Exception
	{
		boolean flag=false;
		Connection con = getConnection();
		String sql = "select count(customer_telephone) from customer where customer_telephone=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1,teleno.trim());
		ResultSet rs = st.executeQuery();
		while(rs.next())
		{
			int n = rs.getInt(1); // number of row inserted 
			if(n>0)
			{
				flag=true;
			}
			else
			{
				flag=false;               
			}
		}
		return flag;	
	}
	
	public CustomerBean getCustomerDetails(String teleno) throws Exception
	{
		CustomerBean customer = new CustomerBean();
		Connection con = getConnection();
		String sql = "select * from customer where customer_telephone=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1,teleno.trim());
		ResultSet rs = st.executeQuery();  //execute by each row
		
		while(rs.next())
		{
			customer.setId(rs.getString(1));
			customer.setName(rs.getString(2));
			customer.setAddress(rs.getString(3));
			customer.setTelephoneNo(rs.getString(4));
			customer.setEmailId(rs.getString(5));
		}
		return customer;
	}
	public ProductBean getProductDetails(String teleno) throws Exception
	{
		ProductBean product = new ProductBean();
		Connection con = getConnection();
		String sql = "select P.product_id,P.product_name,P.product_model,P.product_category,P.product_serial_no,O.purchase_date,O.purchase_warrenty_period from product P,purchase O where P.product_serial_no=O.product_serial_no AND O.customer_telephone=?;";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1,teleno.trim());
		ResultSet rs = st.executeQuery();
		
		while(rs.next())
		{
			product.setId(rs.getString(1));
			product.setName(rs.getString(2));
			product.setModel(rs.getString(3));
			product.setCategory(rs.getString(4));
			product.setSerialNo(rs.getString(5));
			product.setPurchaseDate(rs.getString(6));
			product.setWarrentyPeriod(rs.getString(7));
		}
		return product;
	}
	
	public boolean createComplain(String id,CustomerBean customer,ProductBean product,String uname,String complain) throws Exception
	{
		Connection con = getConnection();
		
		java.util.Date date = new Date();
		Timestamp t = new  Timestamp(date.getTime());
		String sql="INSERT INTO complaint(complaint_id,customer_telephone,product_serial_no,product_category,operator_uname,complaint_date,complaint_detail,complain_solution,root_cause,end_date)VALUES(?,?,?,?,?,curdate(),?,?,?,curdate())";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1,id );
		st.setString(2, customer.getTelephoneNo().trim());
		st.setString(3, product.getSerialNo().trim());
		st.setString(4, product.getCategory().trim());
		st.setString(5, uname.trim().trim());
		st.setString(6, complain.trim());
		st.setString(7, "None");
		st.setString(8,"None");

		int i = st.executeUpdate();
		if(i>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public ComplaintBean getComplaintDetails(String complainid) throws Exception
	{
		ComplaintBean complain = new ComplaintBean();	
		Connection con = getConnection();
		String sql="select * from complaint where complaint_id=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, complainid.trim());
		ResultSet rs = st.executeQuery();
		
		while(rs.next())
		{
			complain.setComplaintID(rs.getString(1));
			complain.setCustomerTelephone(rs.getString(2));
			complain.setProductSerialNo(rs.getString(3));
			complain.setProductCategory(rs.getString(4));
			complain.setOperatorUname(rs.getString(5));
			complain.setComplaintDate(rs.getString(6));
			complain.setComplainDetail(rs.getString(8));
			complain.setComplaintSolution(rs.getString(9));
			complain.setRootCause(rs.getString(10));
			complain.setEndDate(rs.getString(11));
		}
		return complain;
	}
	
	public boolean addSkill(String uname,String skill) throws Exception
	{
		Connection con = getConnection();
		String sql="insert into expertization(uname,category)values(?,?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1,uname.trim());
		st.setString(2, skill.trim());
		
		int i = st.executeUpdate();
		if(i>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
