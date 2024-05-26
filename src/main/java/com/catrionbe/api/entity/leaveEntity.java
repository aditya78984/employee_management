package com.catrionbe.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.catrionbe.api.service.DateStringDeserializer;

@Entity
@Table(name = "aditya_leave")
public class leaveEntity{
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leaveCode", nullable = false, unique = true)
    private int leaveCode;

    public int getLeaveCode() {
		return leaveCode;
	}

	public void setLeaveCode(int leaveCode) {
		this.leaveCode = leaveCode;
	}

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

	@Column(name = "type", nullable = false)
    private String type;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "totalDays", nullable = false)
    private int totalDays;
    
    

}
