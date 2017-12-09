package model;

import java.sql.Date;

public class PurchaseBean 
{
	private String id;
	private String ownerTelephone;
	private String productserial;
	private String warrentPeriod;
	private String purchaseDate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getOwnerTelephone() {
		return ownerTelephone;
	}
	public void setOwnerTelephone(String ownerTelephone) {
		this.ownerTelephone = ownerTelephone;
	}
	public String getProductserial() {
		return productserial;
	}
	public void setProductserial(String productserial) {
		this.productserial = productserial;
	}
	public String getWarrentPeriod() {
		return warrentPeriod;
	}
	public void setWarrentPeriod(String warrentPeriod) {
		this.warrentPeriod = warrentPeriod;
	}
	public String getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	
}
