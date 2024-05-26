package com.catrionbe.api.model;

public class LeaveRequestBody {
	
 	private String  type;
	
 	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(int totalDays) {
		this.totalDays = totalDays;
	}

	public int getLeaveCode() {
		return leaveCode;
	}

	public void setLeaveCode(int leaveCode) {
		this.leaveCode = leaveCode;
	}

	private String  description;
	
 	private int  totalDays;
	
 	private int  leaveCode;
}
