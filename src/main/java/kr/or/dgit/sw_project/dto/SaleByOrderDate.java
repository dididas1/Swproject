package kr.or.dgit.sw_project.dto;

import java.util.Date;

public class SaleByOrderDate {
	//날짜별 판매현황 조회
	private Date orderDate;
	private String saleCode;
	private String clntName;
	private String swName;
	private int saleAmount;
	private boolean isDeposit;
}
