package kr.or.dgit.sw_project.dto;

import java.util.List;

public class Category {
	//분류
	private String groupCode;
	private String groupName;
	private List<Software> software;
	
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
	
	public List<Software> getSoftware() {
		return software;
	}
	
	public void setSoftware(List<Software> software) {
		this.software = software;
	}

	@Override
	public String toString() {
		return String.format("%s, %s, %s", groupCode, groupName, software);
	}
}
