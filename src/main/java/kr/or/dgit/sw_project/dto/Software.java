package kr.or.dgit.sw_project.dto;

public class Software {
	//소프트웨어 등록
	private String swCode;
	private String groupCode;
	private String swName;
	private Category category;
	private int salePrice;
	private int swInven;
	private byte[] swImg;
	private boolean swIsSale;
	private Delivery delivery;
	private Sale sale;
	
	public Software() {}
	
	public Software(String swCode, String swName) {
		super();
		this.swCode = swCode;
		this.swName = swName;
	}

	public Software(String swCode) {
		this.swCode = swCode;
	}
	
	public String getGroupCode() {
		return groupCode;
	}
	
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
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
	
	public byte[] getSwImg() {
		return swImg;
	}
	
	public void setSwImg(byte[] swImg) {
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
		return String.format("%s, %s, %s, %s, %s, %s, %s, %s, %s, %s",
				swCode, groupCode, swName, category, salePrice, swInven, swImg, swIsSale, delivery, sale);
	}
	
	public String toReportCombobox(){
		return String.format("%s", swName);
	}
	
	public String toCombobox(){
		return String.format("%s (재고: %s)", swName,swInven);
	}
}
