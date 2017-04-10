package kr.or.dgit.sw_project.dto;

import java.util.List;

public class SupplyCompany {
	//공급회사
	private String compCode;
	private String compName;
	private String address;
	private String compTel;
	private boolean compIsExist;
	private List<Delivery> delivery;


	public SupplyCompany() {
	}




	public SupplyCompany(String compCode) {
		this.compCode = compCode;
	}




	public SupplyCompany(String compCode, String compName, String address, String compTel) {
		this.compCode = compCode;
		this.compName = compName;
		this.address = address;
		this.compTel = compTel;
	}


	public String getCompCode() {
		return compCode;
	}
	
	

	public void setCompCode(String compCode) {
		this.compCode = compCode;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	

	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getCompTel() {
		return compTel;
	}

	public void setCompTel(String compTel) {
		this.compTel = compTel;
	}

	public boolean isCompIsExist() {
		return compIsExist;
	}

	public void setCompIsExist(boolean compIsExist) {
		this.compIsExist = compIsExist;
	}

	public List<Delivery> getDelivery() {
		return delivery;
	}

	public void setDelivery(List<Delivery> delivery) {
		this.delivery = delivery;
	}

	@Override
	public String toString() {
		return String.format("%s, %s, %s, %s, %s, %s",
			compCode, compName, address, compTel, compIsExist, delivery);
	}
	
	public Object[] toArrayForTable(){
		return new Object[]{compCode,compName,address,compTel};
		
	}
	
	public String toCombobox(){
		return String.format("%s %s", compCode,compName);
	}
	public String comboForOnlyName(){
		return String.format("%s", compName);
	}
}
