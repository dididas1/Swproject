package kr.or.dgit.sw_project.dto;

public class Members {
	private String memId;
	private String memName;
	private String memPassword;
	private String memMail;
	private String memPermission;
	private Boolean memIsExist;

	public Members() {}
	
	public Members(String memId) {
		this.memId = memId;
	}

	public Members(String memId, String memPassword) {
		this.memId = memId;
		this.memPassword = memPassword;
	}
	
	public Members(String memId, String memName, String memPassword, String memMail) {
		super();
		this.memId = memId;
		this.memName = memName;
		this.memPassword = memPassword;
		this.memMail = memMail;
	}
	
	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemPassword() {
		return memPassword;
	}

	public void setMemPassword(String memPassword) {
		this.memPassword = memPassword;
	}

	public String getMemMail() {
		return memMail;
	}

	public void setMemMail(String memMail) {
		this.memMail = memMail;
	}
	
	public String getMemPermission() {
		return memPermission;
	}
	
	public void setMemPermission(String memPermission) {
		this.memPermission = memPermission;
	}
	
	public Boolean getMemIsExist() {
		return memIsExist;
	}

	public void setMemIsExist(Boolean memIsExist) {
		this.memIsExist = memIsExist;
	}
	@Override
	public String toString() {
		return String.format("%s, %s, %s, %s, %s, %s", 
				memId, memName, memPassword, memMail, memPermission, memIsExist);
	}
}