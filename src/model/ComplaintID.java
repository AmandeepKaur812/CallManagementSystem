package model;

import java.util.UUID;

public class ComplaintID 
{
	public static String getComplaintId()
	{
        String s = UUID.randomUUID().toString();
		String s1[] = s.split("-");
		String cid = s1[4].toUpperCase()+s1[1].toUpperCase()+s1[2].toUpperCase()+s1[3].toUpperCase();
		return cid;
    }
}
