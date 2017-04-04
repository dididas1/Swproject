package kr.or.dgit.sw_project.dto;

import java.util.List;

public class Software {
	//소프트웨어 등록
	private String swCode;
	private String swName;
	private int salePrice;
	private int swInven;
	private boolean swIsSale;
	private Category groupCode; 
	private List<Delivery> delivery;
	private List<Sale> sale;
	public Software() {
		super();
	}
	public Software(String swCode, String swName, int salePrice, int swInven, boolean swIsSale, Category groupCode,
			List<Delivery> delivery, List<Sale> sale) {
		super();
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
	public Category getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(Category groupCode) {
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
	@Override
	public String toString() {
		return String.format(
				"Software [swCode=%s, swName=%s, salePrice=%s, swInven=%s, swIsSale=%s, groupCode=%s, delivery=%s, sale=%s]",
				swCode, swName, salePrice, swInven, swIsSale, groupCode, delivery, sale);
	}
	
	
	public String toComboitem() {
		return String.format("%s, (%s)",swCode, swName);
	}
}
