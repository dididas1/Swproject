package kr.or.dgit.sw_project.dto;

import java.util.List;

public class Software {
	//소프트웨어 등록
	private String swCode;
	private String swName;
	private Category category;
	private int salePrice;
	private int swInven;
	private String swImg;
	private boolean swIsSale;
	private Delivery delivery;
	private Sale sale;
	
	
	public Software() {
		super();
	}

	public String getSwCode() {
		return swCode;
	}
	
	public String getSwName() {
		return swName;
	}
	
	public void setSwName(String swName) {
		this.swName = swName;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
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
	
	public String getSwImg() {
		return swImg;
	}
	
	public void setSwImg(String swImg) {
		this.swImg = swImg;
	}
	
	public boolean isSwIsSale() {
		return swIsSale;
	}
	
	public void setSwIsSale(boolean swIsSale) {
		this.swIsSale = swIsSale;
	}
	
	
	public void setSwCode(String swCode) {
		this.swCode = swCode;
	}
	
	
	
	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	@Override
	public String toString() {
		return String.format("%s, %s, %s, %s, %s, %s, %s, %s, %s",
				swCode, swName, category, salePrice, swInven, swImg, swIsSale, delivery, sale);
	}
	
	public String toCombobox(){
		return String.format("%s %s", swCode,swName);
	}
}
