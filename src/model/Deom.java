package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import com.itextpdf.text.log.SysoCounter;

public class Deom 
{
	
	public static void main(String[] args) 
	{
		Database db = new Database();
		try 
		{
			Connection con = db.getConnection();
			String sql = "Insert into timedemo values(?)";
			PreparedStatement st= con.prepareStatement(sql);
			
			//String sql= "select * from timedemo where mydate='2014-01-25'";
			//PreparedStatement st= con.prepareStatement(sql);
			
			Date date = new Date(2015,9,18);
			
			st.setDate(1,date);
			st.executeUpdate();
			/*ResultSet rs = st.executeQuery();
			while(rs.next())
			{66
				System.out.println(rs.getDate(1));
			}*/
		} 
		catch (Exception e) 
		{
			System.out.println(e.toString());
		}
		
		
	}
}
