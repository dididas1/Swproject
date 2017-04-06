package kr.or.dgit.sw_project.dto;

import java.util.Date;
import java.util.List;

public class Sale {
	//소프트웨어 주문
	private String saleCode;
	private Client client;
	private Software software;
	private int saleAmount;
	private boolean isDeposit;
	private Date orderDate;
	private int supplyPrice;
	private int salePrice;
	private boolean saleIsExist;
	private SaleDetail saleDetail;
	
	
	
	public Sale(String saleCode, Client client, Software software, int saleAmount, boolean isDeposit, Date orderDate,
			int supplyPrice, int salePrice, boolean saleIsExist, SaleDetail saleDetail) {
		super();
		this.saleCode = saleCode;
		this.client = client;
		this.software = software;
		this.saleAmount = saleAmount;
		this.isDeposit = isDeposit;
		this.orderDate = orderDate;
		this.supplyPrice = supplyPrice;
		this.salePrice = salePrice;
		this.saleIsExist = saleIsExist;
		this.saleDetail = saleDetail;
	}
	
	

	public String getSaleCode() {
		return saleCode;
	}
	
	public void setSaleCode(String saleCode) {
		this.saleCode = saleCode;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Software getSoftware() {
		return software;
	}
	public void setSoftware(Software software) {
		this.software = software;
	}
	public int getSaleAmount() {
		return saleAmount;
	}
	public void setSaleAmount(int saleAmount) {
		this.saleAmount = saleAmount;
	}
	public boolean isDeposit() {
		return isDeposit;
	}
	public void setDeposit(boolean isDeposit) {
		this.isDeposit = isDeposit;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public int getSupplyPrice() {
		return supplyPrice;
	}
	public void setSupplyPrice(int supplyPrice) {
		this.supplyPrice = supplyPrice;
	}
	public int getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}
	public boolean isSaleIsExist() {
		return saleIsExist;
	}
	public void setSaleIsExist(boolean saleIsExist) {
		this.saleIsExist = saleIsExist;
	}
	public SaleDetail getSaleDetail() {
		return saleDetail;
	}
	public void setSaleDetail(SaleDetail saleDetail) {
		this.saleDetail = saleDetail;
	}
	@Override
	public String toString() {
		return String.format("%s, %s, %s, %s, %s, %s, %s, %s, %s, %s",
				saleCode, client, software, saleAmount, isDeposit, orderDate, supplyPrice, salePrice, saleIsExist,
				saleDetail);
	}
	

	
}
