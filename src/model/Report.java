package model;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;


public class Report 
{
	Database db = new Database();
	
	public String generateOperatorReport(String startDate , String endDate ,String path) throws Exception
	{
		Connection con = db.getConnection();
		String sql = "select U.fname,U.lname,count(C.operator_uname) from complaint C,user U where U.uname=C.operator_uname and complaint_date between '"+startDate+"' and '"+endDate+"' group by operator_uname";
		PreparedStatement st = con.prepareStatement(sql);
		
		ResultSet rs  = st.executeQuery();
		
		
		
		Document document = new Document(PageSize.A4, -30, -30, 50, 50);
		
		//PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream("C:\\Users\\Administrator.Vimesh-PC\\Desktop\\Operator"+startDate+"-"+endDate+".pdf"));
		
		PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(path+"\\Operator"+startDate+"-"+endDate+".pdf"));
		
		document.open();
		Paragraph paragraph1 = new Paragraph();

		paragraph1.setSpacingBefore(50);

		Paragraph title = new Paragraph("Service Call Management",
				FontFactory.getFont(FontFactory.TIMES, 18, Font.BOLD,
						new CMYKColor(0, 0, 0, 255)));

		Paragraph title1 = new Paragraph("Call Management System",
				FontFactory.getFont(FontFactory.TIMES, 12, Font.NORMAL,
						new CMYKColor(0, 0, 0, 255)));

		Paragraph title2 = new Paragraph("                         From : "+startDate,
				FontFactory.getFont(FontFactory.TIMES, 8, Font.BOLD,
						new CMYKColor(0, 0, 0, 255)));

		Paragraph title3 = new Paragraph("                         To : "+endDate,
				FontFactory.getFont(FontFactory.TIMES, 8, Font.BOLD,
						new CMYKColor(0, 0, 0, 255)));

		Paragraph title4 = new Paragraph("Operater wise Report",
				FontFactory.getFont(FontFactory.TIMES, 17, Font.BOLD,
						new CMYKColor(0, 0, 0, 255)));


		title.setAlignment(Element.ALIGN_CENTER);
		title1.setAlignment(Element.ALIGN_CENTER);
		title2.setAlignment(Element.ALIGN_LEFT);
		title3.setAlignment(Element.ALIGN_LEFT);
		title4.setAlignment(Element.ALIGN_CENTER);

		document.add(title);
		document.add(title1);
		document.add(title2);
		document.add(title3);
		document.add(title4);

		PdfPTable t = new PdfPTable(3);

		t.setHorizontalAlignment(Element.ALIGN_CENTER);
		t.setSpacingBefore(25);
		t.setSpacingAfter(25);

		Paragraph p1 = new Paragraph("No.",
				FontFactory.getFont(FontFactory.TIMES, 13, Font.BOLD,
						new CMYKColor(0, 0, 0, 255)));


		Paragraph p2 = new Paragraph("Operater Name",
				FontFactory.getFont(FontFactory.TIMES, 13, Font.BOLD,
						new CMYKColor(0, 0, 0, 255)));


		Paragraph p3 = new Paragraph("No. of Call Recieved",
				FontFactory.getFont(FontFactory.TIMES, 13, Font.BOLD,
						new CMYKColor(0, 0, 0, 255)));




		PdfPCell c1 = new PdfPCell(p1);
		PdfPCell c2 = new PdfPCell(p2);
		PdfPCell c3 = new PdfPCell(p3);


		t.addCell(c1);
		t.addCell(c2);
		t.addCell(c3);
		
		int i=1;
		while(rs.next())
		{
			Paragraph pp1 = new Paragraph(""+i,FontFactory.getFont(FontFactory.TIMES, 10, Font.NORMAL,new CMYKColor(0, 0, 0, 255)));
			Paragraph pp2 = new Paragraph(rs.getString(1)+" "+rs.getString(2),FontFactory.getFont(FontFactory.TIMES, 10, Font.NORMAL,new CMYKColor(0, 0, 0, 255)));
			Paragraph pp3 = new Paragraph(rs.getString(3),FontFactory.getFont(FontFactory.TIMES, 10, Font.NORMAL,new CMYKColor(0, 0, 0, 255)));
			
			PdfPCell cc1 = new PdfPCell(pp1);
			PdfPCell cc2 = new PdfPCell(pp2);
			PdfPCell cc3 = new PdfPCell(pp3);
			
			t.addCell(cc1);
			t.addCell(cc2);
			t.addCell(cc3);
			i++;
		}

		document.add(t);
		document.close();

		
		return path+"\\Operator"+startDate+"-"+endDate+".pdf";
	}
	
	public String generateServiceEngineerReport(String startDate , String endDate ,String path) throws Exception
	{
		Connection con = db.getConnection();
		
		String sql= "SELECT distinct(A.service_eng_uname),U.fname,U.lname from assignment A,user U where A.assign_status not like 'not_assigned' and U.uname=A.service_eng_uname";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet eng_rs  = st.executeQuery();
		
		Document document = new Document(PageSize.A4, -30,-30,50,50);
		PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(path+"\\ServiceEngineer"+startDate+"-"+endDate+".pdf"));
		document.open();
		Paragraph paragraph1 = new Paragraph();

		paragraph1.setSpacingBefore(50);

		Paragraph title = new Paragraph("Service Call Management",
				FontFactory.getFont(FontFactory.TIMES, 18, Font.BOLD,
						new CMYKColor(0, 0, 0, 255)));

		Paragraph title1 = new Paragraph("Call Management System",
				FontFactory.getFont(FontFactory.TIMES, 12, Font.NORMAL,
						new CMYKColor(0, 0, 0, 255)));

		Paragraph title2 = new Paragraph("                         From : "+startDate,
				FontFactory.getFont(FontFactory.TIMES, 8, Font.BOLD,
						new CMYKColor(0, 0, 0, 255)));

		Paragraph title3 = new Paragraph("                         To : "+endDate,
				FontFactory.getFont(FontFactory.TIMES, 8, Font.BOLD,
						new CMYKColor(0, 0, 0, 255)));

		Paragraph title4 = new Paragraph("Service Engineer wise Report",
				FontFactory.getFont(FontFactory.TIMES, 17, Font.BOLD,
						new CMYKColor(0, 0, 0, 255)));


		title.setAlignment(Element.ALIGN_CENTER);
		title1.setAlignment(Element.ALIGN_CENTER);
		title2.setAlignment(Element.ALIGN_LEFT);
		title3.setAlignment(Element.ALIGN_LEFT);
		title4.setAlignment(Element.ALIGN_CENTER);

		document.add(title);
		document.add(title1);
		document.add(title2);
		document.add(title3);
		document.add(title4);

		PdfPTable t = new PdfPTable(6);

		t.setHorizontalAlignment(Element.ALIGN_CENTER);
		t.setSpacingBefore(25);
		t.setSpacingAfter(25);

		Paragraph p1 = new Paragraph("No.",
				FontFactory.getFont(FontFactory.TIMES, 13, Font.BOLD,
						new CMYKColor(0, 0, 0, 255)));


		Paragraph p2 = new Paragraph("Service Engineer Name",
				FontFactory.getFont(FontFactory.TIMES, 13, Font.BOLD,
						new CMYKColor(0, 0, 0, 255)));


		Paragraph p3 = new Paragraph("Assigned Task",
				FontFactory.getFont(FontFactory.TIMES, 13, Font.BOLD,
						new CMYKColor(0, 0, 0, 255)));
		
		Paragraph p4 = new Paragraph("Task In Progress",
				FontFactory.getFont(FontFactory.TIMES, 13, Font.BOLD,
						new CMYKColor(0, 0, 0, 255)));
		
		Paragraph p5 = new Paragraph("Finished Task",
				FontFactory.getFont(FontFactory.TIMES, 13, Font.BOLD,
						new CMYKColor(0, 0, 0, 255)));
		
		Paragraph p6 = new Paragraph("Total Perfomed Task",
				FontFactory.getFont(FontFactory.TIMES, 13, Font.BOLD,
						new CMYKColor(0, 0, 0, 255)));




		PdfPCell c1 = new PdfPCell(p1);
		PdfPCell c2 = new PdfPCell(p2);
		PdfPCell c3 = new PdfPCell(p3);
		PdfPCell c4 = new PdfPCell(p4);
		PdfPCell c5 = new PdfPCell(p5);
		PdfPCell c6 = new PdfPCell(p6);

		Paragraph pp1=null,pp2=null,pp3=null,pp4=null,pp5=null,pp6=null;

		t.addCell(c1);
		t.addCell(c2);
		t.addCell(c3);
		t.addCell(c4);
		t.addCell(c5);
		t.addCell(c6);
		
		int i=1;
		while(eng_rs.next())
		{
			String sql1 = "SELECT count(A.service_eng_uname) from assignment A,complaint C where A.service_eng_uname ='"+eng_rs.getString(1)+"' and A.assign_status='assigned' and C.complaint_id=A.complain_id and C.end_date between '"+startDate+"' and '"+endDate+"' group by A.service_eng_uname";
			PreparedStatement st1 = con.prepareStatement(sql1);
			ResultSet assigned_rs  = st1.executeQuery();
			
			String sql2 = "SELECT count(A.service_eng_uname) from assignment A,complaint C where A.service_eng_uname ='"+eng_rs.getString(1)+"' and A.assign_status='inprogress' and C.complaint_id=A.complain_id and C.end_date between '"+startDate+"' and '"+endDate+"' group by A.service_eng_uname";
			PreparedStatement st2 = con.prepareStatement(sql2);
			ResultSet inprogress_rs  = st2.executeQuery();
			
			String sql3 = "SELECT count(A.service_eng_uname) from assignment A,complaint C where A.service_eng_uname ='"+eng_rs.getString(1)+"' and A.assign_status='finished' and C.complaint_id=A.complain_id and C.end_date between '"+startDate+"' and '"+endDate+"' group by A.service_eng_uname";
			PreparedStatement st3 = con.prepareStatement(sql3);
			ResultSet finished_rs  = st3.executeQuery();
			
			String sql4 = "SELECT count(A.service_eng_uname) from assignment A,complaint C where A.service_eng_uname ='"+eng_rs.getString(1)+"' and A.assign_status not like 'not_assigned' and C.complaint_id=A.complain_id and C.end_date between '"+startDate+"' and '"+endDate+"' group by A.service_eng_uname";
			PreparedStatement st4 = con.prepareStatement(sql4);
			ResultSet total_rs  = st4.executeQuery();
			
			
			pp1 = new Paragraph(""+i,FontFactory.getFont(FontFactory.TIMES, 10, Font.NORMAL,new CMYKColor(0, 0, 0, 255)));
			
			pp2 = new Paragraph(eng_rs.getString(2)+" "+eng_rs.getString(3),FontFactory.getFont(FontFactory.TIMES, 10, Font.NORMAL,new CMYKColor(0, 0, 0, 255)));
			
			if(assigned_rs.next())
			{
				pp3 = new Paragraph(assigned_rs.getString(1),FontFactory.getFont(FontFactory.TIMES, 10, Font.NORMAL,new CMYKColor(0, 0, 0, 255)));	
			}
			else
			{
				pp3 = new Paragraph("0",FontFactory.getFont(FontFactory.TIMES, 10, Font.NORMAL,new CMYKColor(0, 0, 0, 255)));
			}
			
			if(inprogress_rs.next())
			{
				pp4 = new Paragraph(inprogress_rs.getString(1),FontFactory.getFont(FontFactory.TIMES, 10, Font.NORMAL,new CMYKColor(0, 0, 0, 255)));
				System.out.println(i +" "+inprogress_rs.getString(1));
			}
			else
			{
				pp4 = new Paragraph("0",FontFactory.getFont(FontFactory.TIMES, 10, Font.NORMAL,new CMYKColor(0, 0, 0, 255)));
			}
			
			if(finished_rs.next())
			{
				pp5 = new Paragraph(finished_rs.getString(1),FontFactory.getFont(FontFactory.TIMES, 10, Font.NORMAL,new CMYKColor(0, 0, 0, 255)));
			}
			else
			{
				pp5 = new Paragraph("0",FontFactory.getFont(FontFactory.TIMES, 10, Font.NORMAL,new CMYKColor(0, 0, 0, 255)));
			}
			
			if(total_rs.next())
			{
				pp6 = new Paragraph(total_rs.getString(1),FontFactory.getFont(FontFactory.TIMES, 10, Font.NORMAL,new CMYKColor(0, 0, 0, 255)));
			}
			else
			{
				pp6 = new Paragraph("0",FontFactory.getFont(FontFactory.TIMES, 10, Font.NORMAL,new CMYKColor(0, 0, 0, 255)));
			}

			PdfPCell cc1 = new PdfPCell(pp1);
			PdfPCell cc2 = new PdfPCell(pp2);
			PdfPCell cc3 = new PdfPCell(pp3);
			PdfPCell cc4 = new PdfPCell(pp4);
			PdfPCell cc5 = new PdfPCell(pp5);
			PdfPCell cc6 = new PdfPCell(pp6);
					
			t.addCell(cc1);
			t.addCell(cc2);
			t.addCell(cc3);
			t.addCell(cc4);
			t.addCell(cc5);
			t.addCell(cc6);
				
			i++;
		}

		document.add(t);
		document.close();

		
		return path+"\\ServiceEngineer"+startDate+"-"+endDate+".pdf";
	}

	public String generateRootCauseReport(String startDate , String endDate,String path) throws Exception
	{
		Connection con = db.getConnection();
		String sql = "SELECT DISTINCT(product_category) FROM complaint";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet cat_rs  = st.executeQuery();
		
		
		
		Document document = new Document(PageSize.A4, -30, -30, 50, 50);
		PdfWriter writer = PdfWriter.getInstance(document,new FileOutputStream(path+"\\RootCause"+startDate+"-"+endDate+".pdf"));
		document.open();
		Paragraph paragraph1 = new Paragraph();

		paragraph1.setSpacingBefore(50);

		Paragraph title = new Paragraph("Service Call Management",
				FontFactory.getFont(FontFactory.TIMES, 18, Font.BOLD,
						new CMYKColor(0, 0, 0, 255)));

		Paragraph title1 = new Paragraph("Call Management System",
				FontFactory.getFont(FontFactory.TIMES, 12, Font.NORMAL,
						new CMYKColor(0, 0, 0, 255)));

		Paragraph title2 = new Paragraph("                         From : "+startDate,
				FontFactory.getFont(FontFactory.TIMES, 8, Font.BOLD,
						new CMYKColor(0, 0, 0, 255)));

		Paragraph title3 = new Paragraph("                         To : "+endDate,
				FontFactory.getFont(FontFactory.TIMES, 8, Font.BOLD,
						new CMYKColor(0, 0, 0, 255)));

		Paragraph title4 = new Paragraph("RootCause wise Report",
				FontFactory.getFont(FontFactory.TIMES, 17, Font.BOLD,
						new CMYKColor(0, 0, 0, 255)));


		title.setAlignment(Element.ALIGN_CENTER);
		title1.setAlignment(Element.ALIGN_CENTER);
		title2.setAlignment(Element.ALIGN_LEFT);
		title3.setAlignment(Element.ALIGN_LEFT);
		title4.setAlignment(Element.ALIGN_CENTER);

		document.add(title);
		document.add(title1);
		document.add(title2);
		document.add(title3);
		document.add(title4);

		PdfPTable t = new PdfPTable(3);

		t.setHorizontalAlignment(Element.ALIGN_CENTER);
		t.setSpacingBefore(25);
		t.setSpacingAfter(25);
		

		Paragraph p1 = new Paragraph("No.",
				FontFactory.getFont(FontFactory.TIMES, 13, Font.BOLD,
						new CMYKColor(0, 0, 0, 255)));


		Paragraph p2 = new Paragraph("Product Category",
				FontFactory.getFont(FontFactory.TIMES, 13, Font.BOLD,
						new CMYKColor(0, 0, 0, 255)));


		Paragraph p3 = new Paragraph("Root Cause",
				FontFactory.getFont(FontFactory.TIMES, 13, Font.BOLD,
						new CMYKColor(0, 0, 0, 255)));
		
		Paragraph p4 = new Paragraph("No. of Complains",
				FontFactory.getFont(FontFactory.TIMES, 13, Font.BOLD,
						new CMYKColor(0, 0, 0, 255)));




		PdfPCell c1 = new PdfPCell(p1);
		PdfPCell c2 = new PdfPCell(p2);
		PdfPCell c3 = new PdfPCell(p3);
		PdfPCell c4 = new PdfPCell(p4);

		c1.setMinimumHeight(20);
		//t.addCell(c1);
		t.addCell(c2);
		t.addCell(c3);
		t.addCell(c4);
		
		int i=1;
		
		Paragraph pp1,pp2,pp3,pp4;
		while(cat_rs.next())
		{
			pp1 = new Paragraph(""+i,FontFactory.getFont(FontFactory.TIMES, 10, Font.NORMAL,new CMYKColor(0, 0, 0, 255)));
			pp2 = new Paragraph(cat_rs.getString(1),FontFactory.getFont(FontFactory.TIMES, 10, Font.NORMAL,new CMYKColor(0, 0, 0, 255)));
			
			PdfPCell cc1 = new PdfPCell(pp1);
			PdfPCell cc2 = new PdfPCell(pp2);
			//t.addCell(cc1);
			t.addCell(cc2);
			
			PdfPTable rsubt = new PdfPTable(1);
			PdfPTable csubt = new PdfPTable(1);
			
			
			String sql1 = "SELECT root_cause,count(root_cause) from complaint where end_date between '"+startDate+"' and '"+endDate+"' and product_category='"+cat_rs.getString(1)+"' and root_cause not Like 'None'  group by root_cause";
			PreparedStatement st1 = con.prepareStatement(sql1);
			ResultSet root_rs  = st1.executeQuery();
			
			while(root_rs.next())
			{
				Paragraph ppp1,ppp2;
				
				ppp1 = new Paragraph(root_rs.getString(1),FontFactory.getFont(FontFactory.TIMES, 10, Font.NORMAL,new CMYKColor(0, 0, 0, 255)));
				ppp2 = new Paragraph(root_rs.getString(2),FontFactory.getFont(FontFactory.TIMES, 10, Font.NORMAL,new CMYKColor(0, 0, 0, 255)));
				
			
				PdfPCell ccc1 = new PdfPCell(ppp1);
				PdfPCell ccc2 = new PdfPCell(ppp2);
				
				rsubt.addCell(ccc1);
				csubt.addCell(ccc2);
			}
			
			
			PdfPCell cc3 = new PdfPCell(rsubt);
			PdfPCell cc4 = new PdfPCell(csubt);
				
			
			t.addCell(cc3);
			t.addCell(cc4);
			
			i++;
		}

		document.add(t);
		document.close();

		
		return path+"\\RootCause"+startDate+"-"+endDate+".pdf";
	}
	
	
}
