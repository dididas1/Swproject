package kr.or.dgit.sw_project.dto;

public class SaleDetail {
	//판매 세부정보
	private String saleCode;
	private int totalSalePrice;
	private int totalSupplyPrice;
	private	int margin; 
	private int tax;
	private int taxSaleprice;
	private int receivablePrice;
	public String getSaleCode() {
		return saleCode;
	}
	public void setSaleCode(String saleCode) {
		this.saleCode = saleCode;
	}
	public int getTotalSalePrice() {
		return totalSalePrice;
	}
	public void setTotalSalePrice(int totalSalePrice) {
		this.totalSalePrice = totalSalePrice;
	}
	public int getTotalSupplyPrice() {
		return totalSupplyPrice;
	}
	public void setTotalSupplyPrice(int totalSupplyPrice) {
		this.totalSupplyPrice = totalSupplyPrice;
	}
	public int getMargin() {
		return margin;
	}
	public void setMargin(int margin) {
		this.margin = margin;
	}
	public int getTax() {
		return tax;
	}
	public void setTax(int tax) {
		this.tax = tax;
	}
	public int getTaxSaleprice() {
		return taxSaleprice;
	}
	public void setTaxSaleprice(int taxSaleprice) {
		this.taxSaleprice = taxSaleprice;
	}
	public int getReceivablePrice() {
		return receivablePrice;
	}
	public void setReceivablePrice(int receivablePrice) {
		this.receivablePrice = receivablePrice;
	}
	
	@Override
	public String toString() {
		return String.format("%s, %s, %s, %s, %s, %s, %s",
			saleCode, totalSalePrice, totalSupplyPrice, margin, tax, taxSaleprice, receivablePrice);
	}
}