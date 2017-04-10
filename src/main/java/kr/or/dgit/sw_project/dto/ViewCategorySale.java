package kr.or.dgit.sw_project.dto;

public class ViewCategorySale {

	private Category category;
	private int cSalePrice;
	private int cAmount;
	public ViewCategorySale() {
		super();
	}
	
	
	public ViewCategorySale(Category category) {
		super();
		this.category = category;
	}


	public ViewCategorySale(Category category, int cSalePrice, int cAmount) {
		super();
		this.category = category;
		this.cSalePrice = cSalePrice;
		this.cAmount = cAmount;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public int getcSalePrice() {
		return cSalePrice;
	}


	public void setcSalePrice(int cSalePrice) {
		this.cSalePrice = cSalePrice;
	}


	public int getcAmount() {
		return cAmount;
	}


	public void setcAmount(int cAmount) {
		this.cAmount = cAmount;
	}
	
	
	public Object[] toArrayForTable(){
		return new Object[]{category.getGroupCode(),category.getGroupName(),String.format("%,d", cSalePrice),String.format("%,d", cAmount)};
	}
	
	
}
