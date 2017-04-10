package kr.or.dgit.sw_project.dto;

import java.util.Date;
import java.util.List;

public class Delivery {
	//소프트웨어 납품
	private String delCode; 
	private SupplyCompany supplyCompany;
	private Software software;
	private int supplyPrice;
	private int supplyAmount;
	private String orderDate;
	private boolean delIsExist;
	
	
	
	public Delivery() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Delivery(Software software) {
		this.software = software;
	}

	public Delivery(String delCode) {
		super();
		this.delCode = delCode;
	}

	public Delivery(String delCode, SupplyCompany supplyCompany, Software software, int supplyPrice, int supplyAmount,
			String orderDate) {
		super();
		this.delCode = delCode;
		this.supplyCompany = supplyCompany;
		this.software = software;
		this.supplyPrice = supplyPrice;
		this.supplyAmount = supplyAmount;
		this.orderDate = orderDate;
	}

	public String getDelCode() {
		return delCode;
	}

	public void setDelCode(String delCode) {
		this.delCode = delCode;
	}

	public SupplyCompany getSupplyCompany() {
		return supplyCompany;
	}

	public void setSupplyCompany(SupplyCompany supplyCompany) {
		this.supplyCompany = supplyCompany;
	}

	public Software getSoftware() {
		return software;
	}

	public void setSoftware(Software software) {
		this.software = software;
	}

	public int getSupplyPrice() {
		return supplyPrice;
	}

	public void setSupplyPrice(int supplyPrice) {
		this.supplyPrice = supplyPrice;
	}

	public int getSupplyAmount() {
		return supplyAmount;
	}

	public void setSupplyAmount(int supplyAmount) {
		this.supplyAmount = supplyAmount;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public boolean isDelIsExist() {
		return delIsExist;
	}

	public void setDelIsExist(boolean delIsExist) {
		this.delIsExist = delIsExist;
	}

	@Override
	public String toString() {
		return String.format("%s, %s, %s, %s, %s, %s, %s",
			delCode, supplyCompany, software, supplyPrice, supplyAmount, orderDate, delIsExist);
	}
	public Object[] toArray(){
		return new Object[]{delCode,supplyCompany.getCompName(),software.getSwName(),supplyPrice,supplyAmount,orderDate};
	}
}