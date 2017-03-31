package kr.or.dgit.sw_project.dto;

import java.util.List;

public class Software {
	//소프트웨어 등록
	private String swCode;
	private String swName;
	private int salePrice;
	private int swInven;
	private boolean swIsSale;
	private String groupCode; 
	private List<Delivery> delivery;
	private List<Sale> sale;
}
