package com.catrionbe.api.entity;

 import javax.persistence.*;
 

@Entity
@Table(name = "aditya_employee")
public class EmployeeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "empId", nullable = false)
	private int empId;
	
	@Column(name = "firstName", nullable = false) 
	private String  firstName;
	
	@Column(name = "lastName", nullable = false) 
	private String  lastName;
	
	@Column(name = "department", nullable = false) 
	private String  department;
	
	@Column(name = "managerName", nullable = false) 
	private String  managerName;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	 
	

}
