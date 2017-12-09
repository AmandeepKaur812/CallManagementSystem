package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTime 
{
	public String getCurrentDate()
	{
		DateFormat yearFormat = new SimpleDateFormat("YYYY");
		Date year = new Date();

		DateFormat monthFormat = new SimpleDateFormat("MM");
		Date month = new Date();
		
		DateFormat dayFormat = new SimpleDateFormat("DD");
		Date day = new Date();
		
		return dayFormat.format(day)+"-"+monthFormat.format(month)+"-"+yearFormat.format(year);
	}
	
	public String getCurrentTime()
	{
		Date date = new Date();
		
		SimpleDateFormat hourFormat = new SimpleDateFormat("HH");	
		SimpleDateFormat minuteFormat = new SimpleDateFormat("mm");
		SimpleDateFormat secondFormat = new SimpleDateFormat("ss");
		
		return hourFormat.format(date)+":"+minuteFormat.format(date)+":"+secondFormat.format(date);
	}
}
