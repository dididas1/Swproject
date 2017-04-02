package kr.or.dgit.sw_project.dto;

public class JoinData {
	private Category category;
	private Client client;
	private Delivery delivey;
	private Sale sale;
	private SaleDetail saleDetail;
	private Software software;
	private SupplyCompany supplyCompany;
	
	public JoinData() {}
	
	public JoinData(Category category, Client client, Delivery delivey, Sale sale, SaleDetail saleDetail,
			Software software, SupplyCompany supplyCompany) {
		this.category = category;
		this.client = client;
		this.delivey = delivey;
		this.sale = sale;
		this.saleDetail = saleDetail;
		this.software = software;
		this.supplyCompany = supplyCompany;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Delivery getDelivey() {
		return delivey;
	}
	public void setDelivey(Delivery delivey) {
		this.delivey = delivey;
	}
	public Sale getSale() {
		return sale;
	}
	public void setSale(Sale sale) {
		this.sale = sale;
	}
	public SaleDetail getSaleDetail() {
		return saleDetail;
	}
	public void setSaleDetail(SaleDetail saleDetail) {
		this.saleDetail = saleDetail;
	}
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
	
	
	
	
}
