package kr.or.dgit.sw_project.dto;

import java.util.List;

public class Software {
	//소프트웨어 등록
	private String swCode;
	private String swName;
	private int salePrice;
	private int swInven;
	private boolean swIsSale;
	private String groupCode; 
	private List<Delivery> delivery;
	private List<Sale> sale;
	public Software() {}
	public Software(String swCode, String swName, int salePrice, int swInven, boolean swIsSale, String groupCode,
			List<Delivery> delivery, List<Sale> sale) {
		this.swCode = swCode;
		this.swName = swName;
		this.salePrice = salePrice;
		this.swInven = swInven;
		this.swIsSale = swIsSale;
		this.groupCode = groupCode;
		this.delivery = delivery;
		this.sale = sale;
	}
	public String getSwCode() {
		return swCode;
	}
	public void setSwCode(String swCode) {
		this.swCode = swCode;
	}
	public String getSwName() {
		return swName;
	}
	public void setSwName(String swName) {
		this.swName = swName;
	}
	public int getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}
	public int getSwInven() {
		return swInven;
	}
	public void setSwInven(int swInven) {
		this.swInven = swInven;
	}
	public boolean isSwIsSale() {
		return swIsSale;
	}
	public void setSwIsSale(boolean swIsSale) {
		this.swIsSale = swIsSale;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public List<Delivery> getDelivery() {
		return delivery;
	}
	public void setDelivery(List<Delivery> delivery) {
		this.delivery = delivery;
	}
	public List<Sale> getSale() {
		return sale;
	}
	public void setSale(List<Sale> sale) {
		this.sale = sale;
	}
	
	
	
	
}
