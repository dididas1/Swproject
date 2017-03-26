package kr.or.digt.sw_project.dto;

public class SupplyCompany {
	private String compNo;
	private String compName;
	private String compAddr;
	private String compTel;
	
	public SupplyCompany() {}

	public SupplyCompany(String compNo, String compName, String compAddr, String compTel) {
		super();
		this.compNo = compNo;
		this.compName = compName;
		this.compAddr = compAddr;
		this.compTel = compTel;
	}

	public String getCompNo() {
		return compNo;
	}

	public void setCompNo(String compNo) {
		this.compNo = compNo;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getCompAddr() {
		return compAddr;
	}

	public void setCompAddr(String compAddr) {
		this.compAddr = compAddr;
	}

	public String getCompTel() {
		return compTel;
	}

	public void setCompTel(String compTel) {
		this.compTel = compTel;
	}

	@Override
	public String toString() {
		return String.format("supply_company [compNo=%s, compName=%s, compAddr=%s, compTel=%s]", compNo, compName,
				compAddr, compTel);
	}
	
	
}
