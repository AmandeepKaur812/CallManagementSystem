package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CustomerBean;
import model.Database;
import model.ImportData;
import model.ProductBean;
import model.PurchaseBean;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/ProductDataUpload")
public class ProductDataUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		
		boolean isMultipart;
		String filePath = getServletContext().getRealPath("\\Product\\");
		int maxFileSize = 50 * 1024;
		int maxMemSize = 4 * 1024;
		File file ;
		
		Database db = new Database();
		ProductBean product = new ProductBean();
		CustomerBean customer = new CustomerBean();
		PurchaseBean purchase = new PurchaseBean();
		ImportData importData = new ImportData();
		ArrayList<String> error = new ArrayList<String>();
		HttpSession session = request.getSession();
		
		String temp_path = getServletContext().getRealPath("\\temp");
		
		isMultipart = ServletFileUpload.isMultipartContent(request);	   
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(maxMemSize);
		factory.setRepository(new File(temp_path));
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax( maxFileSize );
		PrintWriter out = response.getWriter();

		try
		{ 
			List fileItems = upload.parseRequest(request);
			Iterator i = fileItems.iterator();

			while ( i.hasNext () ) 
			{
				FileItem fi = (FileItem)i.next();
				
				if ( !fi.isFormField () )	
				{
					String fieldName = fi.getFieldName();
					String fileName = fi.getName();
					String contentType = fi.getContentType();
					boolean isInMemory = fi.isInMemory();
					long sizeInBytes = fi.getSize();
	            
					if( fileName.lastIndexOf("\\") >= 0 )
					{
						file = new File( filePath +fileName.substring( fileName.lastIndexOf("\\"))) ;
					}
					else
					{
						file = new File( filePath + fileName.substring(fileName.lastIndexOf("\\")+1)) ;
					}
					
					fi.write( file ) ;
	           
					String alltext = fi.getString();
	    
					String lines[] = alltext.split("\n");
					
					for(int k=1;k<lines.length;k++)
		            {
		                String columns[]=lines[k].split(",");
		         //0  No	
		         //1  Product_Name	
		         //2  Product_Model_No	
		         //3  Product_Category
		         //4  Product_Serial_No	
		         //5  Product_Purchase_Date	
		         //6  Product_Warrenty_Period	
		         //7  Owner_Name	
		         //8  Owner_Telephone_No	
		         //9  Owner_Email	
		         //10 Owner_Address
		                
		                	product.setName(columns[1]);
		                	product.setModel(columns[2]);
		                	product.setCategory(columns[3]);
		                	product.setSerialNo(columns[4]);
		                	
		                	customer.setName(columns[7]);
		                	customer.setTelephoneNo(columns[8]);
		                	customer.setEmailId(columns[9]);
		                	customer.setAddress(columns[10]);
		                	
		                	purchase.setOwnerTelephone(columns[8]);
		                	purchase.setProductserial(columns[4]);
		                	purchase.setPurchaseDate(columns[5]);
		                	purchase.setWarrentPeriod(columns[6]);
		                	
		                	importData.insertData(product, customer, purchase);
		            }	
	           }
			}
		}
			catch (Exception e) 
			{
				error.add("* Import Failed.. Try Again ");
			}
			
			if(error.isEmpty())
			{
				error.add("Import Successfull");
				
			}
			session.setAttribute("importerror", error);
			response.sendRedirect("Admin/ImportData");
		
	}
}
