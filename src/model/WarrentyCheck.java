package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WarrentyCheck 
{
	public boolean checkWarrenty(String purchaseDate,int warrentyPeriod)
	{
		
		String date[] = purchaseDate.split("-");
		int sday = Integer.parseInt(date[0]);
		int  smonth = Integer.parseInt(date[1]);
		int syear =Integer.parseInt(date[2]);
		
		DateFormat yearFormat = new SimpleDateFormat("YYYY");
		Date year = new Date();
		int myear = Integer.parseInt(yearFormat.format(year));
		
		DateFormat monthFormat = new SimpleDateFormat("MM");
		Date month = new Date();
		int mmonth=Integer.parseInt(monthFormat.format(month));
		
		DateFormat dayFormat = new SimpleDateFormat("DD");
		Date day = new Date();
		int mday=Integer.parseInt(dayFormat.format(day));
		
		float y,m,d,totalmonths;
		
		y=myear-syear;
		m=mmonth-smonth;
		d = mday-sday;
		
		totalmonths = (y*12)+m+(d/30);
		
		if(totalmonths>warrentyPeriod)
		{
			return false;
		}
		else
		{
			return true;
		}
		
	}
}
