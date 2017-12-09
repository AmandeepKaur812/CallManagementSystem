package model;

public class Validation 
{
	public boolean validateEmail(String email)
	{
		return email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	}
	
	public boolean validateAlphaNumeric(String alphanum)
	{
		return alphanum.matches("^[a-zA-Z0-9_]*$");
	}
	
	public boolean validateAlphabetic(String alpha)
	{
		return alpha.matches("^[a-zA-Z]*$");
	}
	
	public boolean validateNumber(String num)
	{
		return num.matches("^[0-9]*$");
	}
	
	public boolean validateRequire(String str)
	{
		if(str==null || str.equals(""))
			return true;
		else
			return false;	
	}
	
	public boolean validateMobile(String num)
	{
		if(num.matches("^[0-9]*$"))
		{
			if(num.length()==10)
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	public boolean validateCompare(String str1,String str2)
	{
		if(str1.equals(str2))
			return true;
		else
			return false;
	}
}