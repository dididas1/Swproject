package kr.or.dgit.sw_project.dto;

public class ViewOrderDateSale {
	private Sale sale;
	private Client client;
	private Software software;
	
	
	public ViewOrderDateSale() {
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
	
	public Object[] toArrayForTable(){
		return new Object[]{sale.getOrderDate(),sale.getSaleCode(),client.getClntName(),software.getSwName(),
				String.format("%,d", software.getSalePrice()),String.format("%,d",sale.getSaleAmount()),sale.isDeposit()?"입금완료":"미입금",
						String.format("%,d",sale.getSaleDetail().getTotalSalePrice())};
	}


	
	
	
	
	   
}
