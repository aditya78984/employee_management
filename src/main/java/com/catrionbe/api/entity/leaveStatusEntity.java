package com.catrionbe.api.entity;

import javax.persistence.*;



@Entity
@Table(name = "aditya_LeaveStatus")
public class leaveStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "statusId")
    private int statusId;

    @Column(name = "LeaveId", nullable = false)
    private int leaveId;

    @Column(name = "empId", nullable = false)
    private int empId;

    @Column(name = "Approve_or_Reject", nullable = false)
    private String approve; // Updated property name

    @Column(name = "totalDays", nullable = false)
    private int totalDays;
    
    public int getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(int totalDays) {
		this.totalDays = totalDays;
	}

	public String getApprove() {
		return approve;
	}

	public void setApprove(String approve) {
		this.approve = approve;
	}

	// Getters and setters
    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(int leaveId) {
        this.leaveId = leaveId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    
}
