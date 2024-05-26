 
package com.catrionbe.api.entity;

import com.catrionbe.api.service.DateStringDeserializer;
import com.catrionbe.api.service.DateStringSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.Type;
 
@Entity
@Table(name = "aditya_applyLeave")
public class applyLeaveEntity {

    
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "LeaveId", nullable = false)
	private int leaveId;
	 
	 

	public int getLeaveId() {
		return leaveId;
	}

	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(int totalDays) {
		this.totalDays = totalDays;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
	@Type(type="date")
	@JsonSerialize(using=DateStringSerializer.class)
	@JsonDeserialize(using=DateStringDeserializer.class)
	public Date getStartDate() {
		return StartDate;
	}

	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}

	@Type(type="date")
	@JsonSerialize(using=DateStringSerializer.class)
	@JsonDeserialize(using=DateStringDeserializer.class)
	public Date getEndDate() {
		return EndDate;
	}

	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}

	@Column(name = "type", nullable = false) 
		private String  type;
		
		@Column(name = "empId", nullable = false) 
		private int  empId;
		
		@Column(name = "totalDays", nullable = false) 
		private int  totalDays;
		
		@Column(name = "reason", nullable = false) 
		private String  reason;
		
		@Column(name = "StartDate", nullable = false)
		private Date StartDate;
		
		@Column(name = "EndDate", nullable = false)
		private Date EndDate;

}