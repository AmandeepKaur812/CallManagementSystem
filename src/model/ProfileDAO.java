package model;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ProfileDAO 
{
	public boolean updateProfile(UserBean user) throws Exception
	{
		Database db = new Database();
		
		Connection con = db.getConnection();
		PreparedStatement st = con.prepareStatement("update user set fname=?,lname=?,email=?,mobile=?,address=? where uname=?");
		st.setString(1, user.getFname());
		st.setString(2, user.getLname());
		st.setString(3, user.getEmail());
		st.setString(4, user.getMobile());
		st.setString(5, user.getAddress());
		st.setString(6, user.getUname());
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
	public boolean updateProfilePicture(UserBean user) throws Exception
	{
		Database db = new Database();
		
		Connection con = db.getConnection();
		PreparedStatement st = con.prepareStatement("update user set profile_picture=? where uname=?");
		st.setString(1,user.getProfilePicture());
		st.setString(2, user.getUname());
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
