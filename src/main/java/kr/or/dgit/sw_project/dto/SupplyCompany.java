package kr.or.dgit.sw_project.dto;

import java.util.List;

public class SupplyCompany {
	//공급회사
	private String compCode;
	private String compName;
	private String compAddr;
	private String compTel;
	private boolean compIsExist;
	private List<Delivery> delivery;
}
