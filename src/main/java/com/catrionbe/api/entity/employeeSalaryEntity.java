package com.catrionbe.api.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "aditya_employee_salary")
public class employeeSalaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salary_id")
    private int salaryId;

    @ManyToOne
    @JoinColumn(name = "EmployeeID", referencedColumnName = "empId")
    private EmployeeEntity employee;

    @Column(name = "basic_salary")
    private BigDecimal basicSalary;

    @Column(name = "allowances")
    private BigDecimal allowances;

    @Column(name = "deductions")
    private BigDecimal deductions;

    @Column(name = "payout_date")
    private Date payoutDate;

    @Column(name = "is_delayed")
    private boolean isDelayed;

    @Column(name = "salary_frequency")
    private String salaryFrequency;

	public employeeSalaryEntity(int salaryId, EmployeeEntity employee, BigDecimal basicSalary, BigDecimal allowances,
			BigDecimal deductions, Date payoutDate, boolean isDelayed, String salaryFrequency) {
		super();
		this.salaryId = salaryId;
		this.employee = employee;
		this.basicSalary = basicSalary;
		this.allowances = allowances;
		this.deductions = deductions;
		this.payoutDate = payoutDate;
		this.isDelayed = isDelayed;
		this.salaryFrequency = salaryFrequency;
	}
	
	public employeeSalaryEntity() {
		
	}

	public int getSalaryId() {
		return salaryId;
	}

	public void setSalaryId(int salaryId) {
		this.salaryId = salaryId;
	}

	public EmployeeEntity getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeEntity employee) {
		this.employee = employee;
	}

	public BigDecimal getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(BigDecimal basicSalary) {
		this.basicSalary = basicSalary;
	}

	public BigDecimal getAllowances() {
		return allowances;
	}

	public void setAllowances(BigDecimal allowances) {
		this.allowances = allowances;
	}

	public BigDecimal getDeductions() {
		return deductions;
	}

	public void setDeductions(BigDecimal deductions) {
		this.deductions = deductions;
	}

	public Date getPayoutDate() {
		return payoutDate;
	}

	public void setPayoutDate(Date payoutDate) {
		this.payoutDate = payoutDate;
	}

	public boolean isDelayed() {
		return isDelayed;
	}

	public void setDelayed(boolean isDelayed) {
		this.isDelayed = isDelayed;
	}

	public String getSalaryFrequency() {
		return salaryFrequency;
	}

	public void setSalaryFrequency(String salaryFrequency) {
		this.salaryFrequency = salaryFrequency;
	}

    // Constructor, getters, setters
	
    
}

