package model;

public class ForgetMessage 
{
	public String getForgetMessage(String link)
	{
		String msg = "<fieldset align='center' style='background:Blue;width:600px'><h1 align='center' style='color:yellow;font-size:40px'>Service Call Management - CMS </h1><br /><b><a href="+link+" style='color:pink;font-size:20px;text-decoration:none;'>Click Here to Reset Your Password</a></b></br><i><h2 align='right' style='color:white'>Made by&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br><br />Vimesh Shah - 100380116012&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h2><h2 align='right' style='color:white'>Harsh Barach - 100380116050&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h2><br /></i></fieldset><p style='color:#F00'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;* This is automated generated email by CMS system.</p> ";
		return msg;
	}
	
	public String getForgetSubject()
	{
		return "Password Recovary - CMS";
	}
	
	public String getActiveMessage(String link)
	{
		String msg = "<fieldset align='center' style='background:Blue;width:600px'><h1 align='center' style='color:yellow;font-size:40px'>Service Call Management - CMS </h1><br /><b><a href="+link+" style='color:pink;font-size:20px;text-decoration:none;'>Click Here to Activate Your Account</a></b></br><i><h2 align='right' style='color:white'>Made by&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br><br />Vimesh Shah - 100380116012&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h2><h2 align='right' style='color:white'>Harsh Barach - 100380116050&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h2><br /></i></fieldset><p style='color:#F00'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;* This is automated generated email by CMS system.</p> ";
		return msg;
	}
	
	public String getActiveSubject()
	{
		return "Activate Your Account - CMS";
	}
	
	public String getComplainMessage(String link)
	{
		String msg = "<fieldset align='center' style='background:Blue;width:600px'><h1 align='center' style='color:yellow;font-size:40px'>Service Call Management - CMS </h1><br /><b><p style='color:white'>Your Complain Id is "+link+"</p></b><br></br><p style='color:white'>Your Complaint Resolved Shortly<i></p><h2 align='right' style='color:white'>Made by&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br><br />Vimesh Shah - 100380116012&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h2><h2 align='right' style='color:white'>Harsh Barach - 100380116050&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h2><br /></i></fieldset><p style='color:#F00'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;* This is automated generated email by CMS system.</p> ";
		return msg;
	}
	
	public String getComplainSubject()
	{
		return "Complain Registered - CMS";
	}
}
