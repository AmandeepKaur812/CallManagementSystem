package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileCreator 
{
	public static void create(String content,String filename) throws Exception
	{
		File file = new File("C:\\Users\\Administrator.Vimesh-PC\\Desktop\\"+filename+".html");	
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		bw.write(content);
		bw.close();
	}
}
