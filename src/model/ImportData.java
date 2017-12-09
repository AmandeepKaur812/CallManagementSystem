package model;

import java.sql.*;

public class ImportData 
{
	public void insertData(ProductBean product,CustomerBean customer,PurchaseBean purchase) throws Exception
	{
		Database db = new Database();
		boolean flag=false;
		
		Connection con = db.getConnection();
		con.setAutoCommit(false);
		con.setSavepoint("demo");
		PreparedStatement prodst = con.prepareStatement("INSERT INTO product(product_name,product_model,product_category,product_serial_no)VALUES(?,?,?,?)");
		prodst.setString(1, product.getName());
		prodst.setString(2, product.getModel());
		prodst.setString(3, product.getCategory());
		prodst.setString(4, product.getSerialNo());
		int i =prodst.executeUpdate();
		
		PreparedStatement custst = con.prepareStatement("INSERT INTO customer(customer_name,customer_address,customer_telephone,customer_email)VALUES(?,?,?,?)");
		custst.setString(1, customer.getName());
		custst.setString(2, customer.getAddress());
		custst.setString(3, customer.getTelephoneNo());
		custst.setString(4, customer.getEmailId());
		int j =custst.executeUpdate();
		
		PreparedStatement purst = con.prepareStatement("INSERT INTO purchase(customer_telephone,product_serial_no,purchase_date,purchase_warrenty_period)VALUES(?,?,?,?)");
		purst.setString(1, purchase.getOwnerTelephone());
		purst.setString(2, purchase.getProductserial());
		purst.setString(3, purchase.getPurchaseDate());
		purst.setString(4, purchase.getWarrentPeriod());
		int k = purst.executeUpdate();
		
		if(i>0 && j>0 && k>0)
		{
			con.commit();
			flag=true;
		}
		else
		{
			con.rollback();
		}
		
		
		
	}
}
