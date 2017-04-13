package kr.or.dgit.sw_project.dto;

import java.util.List;

public class Category {
	//분류
	private String groupCode;
	private String groupName;
	private Software software;
	
	
	
	public Category() {
		super();
	}

	public Category(String groupCode) {
		this.groupCode = groupCode;
	}

	public Category(String groupCode, String groupName) {
		this.groupCode = groupCode;
		this.groupName = groupName;
	}

	public String getGroupCode() {
		return groupCode;
	}
	
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	
	public String getGroupName() {
		return groupName;
	}
	
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	

	public Software getSoftware() {
		return software;
	}

	public void setSoftware(Software software) {
		this.software = software;
	}

	@Override
	public String toString() {
		return String.format("%s, %s, %s", groupCode, groupName, software);
	}
	public String toCombobox(){
		return String.format("%s", groupName);
	}
	
	public String[] toArray() {
		return new String[]{groupCode,groupName};
	}
}
