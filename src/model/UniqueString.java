package model;

import java.security.SecureRandom;
import java.math.BigInteger;

public class UniqueString 
{

	private SecureRandom random = new SecureRandom();
	
	public String getUnameString()
	{
		return new BigInteger(50,random).toString();
	}
	
	public String getPswdString()
	{
		return new BigInteger(50,random).toString();
	}
}

