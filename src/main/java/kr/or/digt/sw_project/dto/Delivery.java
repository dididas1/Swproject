package kr.or.digt.sw_project.dto;

import java.util.Date;

public class Delivery {
	private String delCode; 
	private SupplyCompany supplyCompany;
	private Software software;
	private int supplyPrice;
	private int supplyAmount;
	private Date orderDate;
	private boolean delIsExist;
}
