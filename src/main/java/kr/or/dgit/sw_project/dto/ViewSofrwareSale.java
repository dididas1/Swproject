package kr.or.dgit.sw_project.dto;

public class ViewSofrwareSale {
	private Software software;
	private Category category;
	private Sale sale;
	private SupplyCompany supplyCompany;
	
	public ViewSofrwareSale() {
		// TODO Auto-generated constructor stub
	}
	
	public Software getSoftware() {
		return software;
	}
	public void setSoftware(Software software) {
		this.software = software;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Sale getSale() {
		return sale;
	}
	public void setSale(Sale sale) {
		this.sale = sale;
	}
	public SupplyCompany getSupplyCompany() {
		return supplyCompany;
	}
	public void setSupplyCompany(SupplyCompany supplyCompany) {
		this.supplyCompany = supplyCompany;
	}
	
	//	return new String[]{"판매코드","품목명 ","분류","공급회사명","판매금액","공급금액","판매이윤"};
		
	
	
	public Object[] toArrayForTable(){
		return new Object[]{sale.getSaleCode(),software.getSwName(),category.getGroupName(),supplyCompany.getCompName(),
							String.format("%,d", sale.getSaleDetail().getTotalSalePrice()),
							String.format("%,d", sale.getSaleDetail().getTotalSupplyPrice()),
							String.format("%,d", sale.getSaleDetail().getMargin())};
	}

	@Override
	public String toString() {
		return String.format("ViewSofrwareSale [software=%s, category=%s, sale=%s, supplyCompany=%s]", software,
				category, sale, supplyCompany);
	}
	
	
}
