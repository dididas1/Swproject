package kr.or.dgit.sw_project.dto;

public class ViewClientSale {
	private Client client;
	private Sale sale;
	private Software software;
	
	
	
	public ViewClientSale() {
	}
	
	
	
	public ViewClientSale(Client client, Sale sale, Software software) {
		super();
		this.client = client;
		this.sale = sale;
		this.software = software;
	}



	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Sale getSale() {
		return sale;
	}
	public void setSale(Sale sale) {
		this.sale = sale;
	}
	public Software getSoftware() {
		return software;
	}
	public void setSoftware(Software software) {
		this.software = software;
	}
	
	
	public Object[] getColummForClient() {
		return new String[]{"고객상호명","풍목명 ","주문수량","단가","매출금","입금여부","미수금"};
		
	}
	
	public Object[] toArrayForTable(){
		return new Object[]{client.getClntName(),software.getSwName(),String.format("%,d", sale.getSaleAmount()),
				String.format("%,d",sale.getSalePrice()),String.format("%,d",sale.getSaleDetail().getTotalSalePrice()),sale.isDeposit()?"입금완료":"미입금",
						String.format("%,d",sale.getSaleDetail().getReceivablePrice())};
	}



	
	
	
}
