package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class TaskManager 
{
	Database db  = new Database();
	
	public ResultSet getTasks(String uname,String task_status) throws Exception
	{
		ArrayList<String> tasks = new ArrayList<String>();
		
		Connection con = db.getConnection();
		String sql = "select A.assign_id,A.complain_id,A.service_eng_uname,A.assign_status,A.task_count from assignment A,complaint C where A.service_eng_uname=? AND A.assign_status=? and A.complain_id=C.complaint_id order by C.complaint_id desc";
		PreparedStatement st =con.prepareStatement(sql);
		st.setString(1,uname);
		st.setString(2,task_status);
		ResultSet rs = st.executeQuery();
		
		return rs;
	}
	
	public ComplaintBean getTaskDetail(String complainId) throws Exception
	{
		ComplaintBean complaint = new ComplaintBean();
		
		Connection con = db.getConnection();
		String sql = "select * from complaint where complaint_id=?";
		PreparedStatement st =con.prepareStatement(sql);
		st.setString(1,complainId.trim());
		ResultSet rs = st.executeQuery();
		
		while(rs.next())
		{
			complaint.setComplaintID(rs.getString(1));
			complaint.setCustomerTelephone(rs.getString(2));
			complaint.setProductSerialNo(rs.getString(3));
			complaint.setProductCategory(rs.getString(4));
			complaint.setOperatorUname(rs.getString(5));
			complaint.setComplaintDate(rs.getString(6));
			complaint.setComplainDetail(rs.getString(7));
		}
		return complaint;
	}
	
	public void updateTaskStatus(String complainId,String status) throws Exception
	{
		Connection con = db.getConnection();
		String sql = "update assignment set assign_status=? where complain_id=?";
		PreparedStatement st =con.prepareStatement(sql);
		st.setString(1,status);
		st.setString(2,complainId.trim());
		st.executeUpdate();
	}
	
	public void decreaseTaskCount(String uname) throws Exception
	{
		Connection con = db.getConnection();
		String sql = "update assignment set task_count=task_count-1 where service_eng_uname=?";
		PreparedStatement st =con.prepareStatement(sql);
		st.setString(1,uname.trim());
		st.executeUpdate();
	}
	
	public void addRootCause(String rootCause,String complaintId) throws Exception 
	{
		Connection con = db.getConnection();
		String sql = "update complaint set root_cause=? where complaint_id=?";
		PreparedStatement st =con.prepareStatement(sql);
		st.setString(1,rootCause.trim());
		st.setString(2, complaintId.trim());
		int i=st.executeUpdate();
		
	}
	public int addSolution(String solution,String complaintId) throws Exception 
	{
		Connection con = db.getConnection();
		String sql = "update complaint set complain_solution=? where complaint_id=?";
		PreparedStatement st =con.prepareStatement(sql);
		st.setString(1,solution.trim());
		st.setString(2, complaintId.trim());
		int i=st.executeUpdate();
		return i;
	}
	
	
	public void setCurrentDate(String complaintId) throws Exception 
	{
		Date date = new Date();
		Connection con = db.getConnection();
		String sql = "update complaint set end_date=curdate() where complaint_id=?";
		PreparedStatement st =con.prepareStatement(sql);
		st.setString(1, complaintId.trim());
		int i=st.executeUpdate();	
	}
	
	
}
