package kr.or.dgit.sw_project.dto;

public class JoinFromSale {
	private Sale sale;
	private Client client;
	private Software software;
	private Category category;
	private SaleDetail saleDetail;
	
	public JoinFromSale() {
	}

	public JoinFromSale(Sale sale) {
		this.sale = sale;
	}

	public Sale getSale() {
		return sale;
	}
	
	public void setSale(Sale sale) {
		this.sale = sale;
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
	
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public SaleDetail getSaleDetail() {
		return saleDetail;
	}
	
	public void setSaleDetail(SaleDetail saleDetail) {
		this.saleDetail = saleDetail;
	}
	
	@Override
	public String toString() {
		return String.format("%s, %s, %s, %s, %s", sale, client,
				software, category, saleDetail);
	}
	
	public Object[] toArrayForTable(){
		return new Object[]{sale.getSaleCode(),client.getClntName(),software.getSwName(),String.format("%,d",software.getSalePrice()),
							sale.getSaleAmount(),String.format("%,d", sale.getSaleDetail().getTotalSalePrice()),sale.getOrderDate(),sale.isDeposit()?"입금완료":"미입금"};
	}

}
