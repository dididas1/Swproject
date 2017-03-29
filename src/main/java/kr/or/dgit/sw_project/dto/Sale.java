package kr.or.dgit.sw_project.dto;

import java.util.Date;

public class Sale {
	//소프트웨어 주문
	private String saleCode;
	private String clntCode;
	private String clntName; 
	private String swCode;
	private String swName;
	private int saleAmount;
	private boolean isDeposit;
	private Date orderDate;
	private int supplyPrice;
	private int salePrice;
	private boolean saleIsExist;
}
