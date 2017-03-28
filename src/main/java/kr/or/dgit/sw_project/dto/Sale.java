package kr.or.dgit.sw_project.dto;

import java.util.Date;

public class Sale {
	private String saleCode;
	private Client client; 
	private Software software;
	private int saleAmount;
	private boolean isDeposit;
	private Date orderDate;
	private int supplyPrice;
	private int salePrice;
	private boolean saleIsExist;
	private SaleDetail saleDetail; 
}
