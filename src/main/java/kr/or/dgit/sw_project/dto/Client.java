package kr.or.dgit.sw_project.dto;

import java.util.List;

public class Client {
	//고객사
	private String clntCode;
	private String clntName;
	private String clntAddr;
	private String clntTel;
	private boolean clntIsExist;
	private List<Sale> sale;
	
	public Client() {}
	
	
	
	

	public Client(String clntCode, String clntName) {
		super();
		this.clntCode = clntCode;
		this.clntName = clntName;
	}





	public Client(String clntCode, String clntName, String clntAddr, String clntTel) {
		this.clntCode = clntCode;
		this.clntName = clntName;
		this.clntAddr = clntAddr;
		this.clntTel = clntTel;
	}

	public String getClntCode() {
		return clntCode;
	}

	public void setClntCode(String clntCode) {
		this.clntCode = clntCode;
	}

	public String getClntName() {
		return clntName;
	}

	public void setClntName(String clntName) {
		this.clntName = clntName;
	}

	public String getClntAddr() {
		return clntAddr;
	}

	public void setClntAddr(String clntAddr) {
		this.clntAddr = clntAddr;
	}

	public String getClntTel() {
		return clntTel;
	}

	public void setClntTel(String clntTel) {
		this.clntTel = clntTel;
	}

	public boolean isClntIsExist() {
		return clntIsExist;
	}

	public void setClntIsExist(boolean clntIsExist) {
		this.clntIsExist = clntIsExist;
	}

	public List<Sale> getSale() {
		return sale;
	}

	public void setSale(List<Sale> sale) {
		this.sale = sale;
	}

	public Object[] toArrayForTable(){
		return new Object[]{clntCode,clntName,clntTel,clntAddr};
	}
	
	public String toCombobox(){
		return String.format("%s", clntName);
	}
	
	@Override
	public String toString() {
		return String.format("%s, %s, %s, %s, %s, %s",
				clntCode, clntName, clntAddr, clntTel, clntIsExist, sale);
	}
}
