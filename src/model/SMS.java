package model;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class SMS {

	public void send(String rcpt,String msg)throws IOException
	{	
		HttpURLConnection httpURLConnection =(HttpURLConnection) new URL("http://codeangle-knightserver.rhcloud.com/mesazh/mesazh").openConnection();
		String __username = "Your UserName",__password = "Your Password",__message =msg,__rcpt =rcpt;
		
		String params = "password="+__password+"&sendto="+__rcpt+"&username="+__username+"&message="+__message;
		httpURLConnection.setDoOutput(true);
		httpURLConnection.setRequestProperty("Connection", "keep-alive");
		
		httpURLConnection.connect();
		PrintWriter printer =new PrintWriter(new OutputStreamWriter(httpURLConnection.getOutputStream()), true);
		printer.print(params);
		printer.flush();
		printer.close();
		httpURLConnection.disconnect();
		httpURLConnection.getContent();
	}
}


