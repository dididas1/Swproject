package kr.or.dgit.sw_project.dto;

import java.util.Date;
import java.util.List;

public class Sale {
	//소프트웨어 주문
	private String saleCode;
	private String clntCode;
	private String swCode;
	private int saleAmount;
	private boolean isDeposit;
	private Date orderDate;
	private int supplyPrice;
	private int salePrice;
	private boolean saleIsExist;
	private SaleDetail saleDetail;
	
	
	public Sale() {
	}
	public Sale(String saleCode, String clntCode, String swCode, int saleAmount, boolean isDeposit, Date orderDate,
			int supplyPrice, int salePrice, boolean saleIsExist) {
		this.saleCode = saleCode;
		this.clntCode = clntCode;
		this.swCode = swCode;
		this.saleAmount = saleAmount;
		this.isDeposit = isDeposit;
		this.orderDate = orderDate;
		this.supplyPrice = supplyPrice;
		this.salePrice = salePrice;
		this.saleIsExist = saleIsExist;
	}
	public String getSaleCode() {
		return saleCode;
	}
	public void setSaleCode(String saleCode) {
		this.saleCode = saleCode;
	}
	public String getClntCode() {
		return clntCode;
	}
	public void setClntCode(String clntCode) {
		this.clntCode = clntCode;
	}
	public String getSwCode() {
		return swCode;
	}
	public void setSwCode(String swCode) {
		this.swCode = swCode;
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
	
	
}
