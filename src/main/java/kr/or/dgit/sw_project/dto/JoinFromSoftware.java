package kr.or.dgit.sw_project.dto;

import java.util.List;

public class JoinFromSoftware {
	private Software software;
	private SupplyCompany supplyCompany;
	private Category category;
	private List<Delivery> delivery;
	private List<Sale> sale;
	private SaleDetail saleDetail;
	
	public Software getSoftware() {
		return software;
	}
	
	public void setSoftware(Software software) {
		this.software = software;
	}
	
	public SupplyCompany getSupplyCompany() {
		return supplyCompany;
	}
	
	public void setSupplyCompany(SupplyCompany supplyCompany) {
		this.supplyCompany = supplyCompany;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
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
	
	public SaleDetail getSaleDetail() {
		return saleDetail;
	}
	
	public void setSaleDetail(SaleDetail saleDetail) {
		this.saleDetail = saleDetail;
	}

	@Override
	public String toString() {
		return String.format("%s, %s, %s, %s, %s, %s",
			software, supplyCompany, category, delivery, sale, saleDetail);
	}
}
