package kr.or.dgit.sw_project.dto;

public class JoinFromSoftware {
	private Software software;
	private SupplyCompany supplyCompany;
	private Client client;
	private Category category;
	private Delivery delivery;
	private Sale sale;
	private SaleDetail saleDetail;
	
	
	public JoinFromSoftware() {
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
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
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
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

	public SaleDetail getSaleDetail() {
		return saleDetail;
	}
	
	public void setSaleDetail(SaleDetail saleDetail) {
		this.saleDetail = saleDetail;
	}

	@Override
	public String toString() {
		return String.format("%s, %s, $s, %s, %s, %s, %s",
			software, supplyCompany, client, category, delivery, sale, saleDetail);
	}
	
	public Object[] toSoftLists() {
		return new Object[]{software.getSwCode(), category.getGroupName(), software.getSwName(), String.format("%d", sale.getSalePrice()) ,String.format("%,3d", software.getSwInven())};
	}
	
	public Object[] toSoftReport() {
		return new Object[]{sale.getSaleCode(),software.getSwName(), category.getGroupName(), supplyCompany.getCompName(),  
				String.format("%,d", saleDetail.getTotalSupplyPrice()),String.format("%,d", saleDetail.getTotalSalePrice()),String.format("%,d", saleDetail.getMargin())};
	}
	
	public Object[] toClntReport() {
		return new Object[]{sale.getClient().getClntName(),software.getSwName(), sale.getSaleAmount(), sale.isDeposit(),  
				String.format("%,d", sale.getSalePrice()),String.format("%,d", saleDetail.getTotalSalePrice()),String.format("%,d", saleDetail.getReceivablePrice())};
	}
}
