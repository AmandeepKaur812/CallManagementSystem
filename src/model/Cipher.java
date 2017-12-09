package model;

import java.util.Random;

public class Cipher 
{
	UniqueString unique = new UniqueString();
	
	public String encryptString(String str)
	{
		
		String encr;
		int i,j,location=0;
		char arr[];
		int loc;
		char num[] = {'0','1','2','3','4','5','6','7','8','9'};
		int l[]   = {4,2,1,3,2,1,4,5,1,3};
		
		char chart[][]={{'f','y','x','a'},{'v','g'},{'b'},{'s','c','z'},{'e','t'},{'p'},{'j','n','d','o'},{'h','q','r','u','k'},{'l'},{'i','m','w'}};
		arr=str.toCharArray();
		
		for(i=0;i<arr.length;i++)
		{
			for(j=0;j<10;j++)
			{
				if(arr[i]==num[j])
				{
					location=j;				
				}
			}	
			loc= (int)(0+(Math.random()*(l[location]-0)));	
			arr[i]=chart[location][loc];
		}
		encr = String.valueOf(arr);
		
		
		return encr;
	}
	
	public String decryptString(String str)
	{
		
		String dcrypt;
		int i,j,k,location=0;
		char arr[];
		char num[] = {'0','1','2','3','4','5','6','7','8','9'};
		int l[]   = {4,2,1,3,2,1,4,5,1,3};
		
		char chart[][]={{'f','y','x','a'},{'v','g'},{'b'},{'s','c','z'},{'e','t'},{'p'},{'j','n','d','o'},{'h','q','r','u','k'},{'l'},{'i','m','w'}};
		arr=str.toCharArray();
		
		for(i=0;i<arr.length;i++)
		{
			for(j=0;j<10;j++)
			{
				for(k=0;k<l[j];k++)
				{
					if(arr[i]==chart[j][k])
					{
						location=j;	
					}
				}
			}	
		
			arr[i]=num[location];
		}
		dcrypt = String.valueOf(arr);
		return dcrypt;
	}
	
	public String numericString(String astr)
	{
	
		String nstr="";
		char arr[];
		int i=0,temp;
		
		arr=astr.toCharArray();
		
		for(i=0;i<arr.length;i++)
		{
			temp=(int)arr[i];
			if(temp<100)
			{
				nstr = nstr+"0"+temp;
			}
			else
			{
				nstr = nstr+temp;
			}
		}
		return nstr;
	}
	public String alphaString(String nstr)
	{
		
		String astr="";
		char arr[];
		int i=0;
		String temp;
		char t;
		
		arr=nstr.toCharArray();
		
		for(i=0;i<arr.length;i=i+3)
		{
			temp=""+ arr[i] +arr[i+1]+arr[i+2];
			astr = astr+(char)Integer.parseInt(temp);
		}
	
		return astr;
	}
}
