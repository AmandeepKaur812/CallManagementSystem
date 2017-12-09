package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AssignToEngineer 
{

	
	public  static void assignTask(String complainID,String category) throws Exception
	{ synchronized (complainID) {
		
		int i=0,j=0;
		Connection con = Database.getConnection();
		String sql = "SELECT uname,task_count FROM (select E.uname,A.task_count from expertization E,assignment A where E.uname=A.service_eng_uname and E.category=?)as y WHERE  task_count=(SELECT MIN(task) FROM (select A.task_count as task,E.uname from assignment A,expertization E where A.service_eng_uname=E.uname and E.category=?)as x) ";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, category);
		st.setString(2, category);
		ResultSet rs = st.executeQuery();
		String uname="",count="";
		
		while(rs.next())
		{
			uname=rs.getString(1);
			count=rs.getString(2);
		}
		int c = Integer.parseInt(count);
		c++;
				
		String sql1="insert into assignment(complain_id,service_eng_uname,assign_status,task_count)values(?,?,?,?)";
		PreparedStatement st1 = con.prepareStatement(sql1);
		st1.setString(1,complainID);
		st1.setString(2,uname);
		st1.setString(3, "Assigned");
		st1.setString(4, String.valueOf(c));
		i =st1.executeUpdate();	
		
		String sql2 = "update assignment set task_count=? where service_eng_uname=?";
		PreparedStatement st2 = con.prepareStatement(sql2);
		st2.setString(1, String.valueOf(c));
		st2.setString(2, uname);
		j = st2.executeUpdate();
	}
		
		
	}
	
}
