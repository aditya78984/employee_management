package com.catrionbe.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import com.catrionbe.api.entity.EmployeeEntity;
import com.catrionbe.api.model.EmpIdRequest;
import com.catrionbe.api.model.EmployeeRequestBody;
import com.catrionbe.api.repositories.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public EmployeeEntity createEmployee(EmployeeRequestBody empReqBody) {

		EmployeeEntity newEmployee = new EmployeeEntity();
		newEmployee.setFirstName(empReqBody.getFirstName());
		newEmployee.setLastName(empReqBody.getLastName());
		newEmployee.setDepartment(empReqBody.getDepartment());
		newEmployee.setManagerName(empReqBody.getManagerName());
		return employeeRepository.save(newEmployee);
	}

	public EmployeeEntity updateEmployee(EmployeeRequestBody empReqBody) {
		EmployeeEntity newEmployee = new EmployeeEntity();
		newEmployee.setEmpId(empReqBody.getEmpId());
		newEmployee.setFirstName(empReqBody.getFirstName());
		newEmployee.setLastName(empReqBody.getLastName());
		newEmployee.setDepartment(empReqBody.getDepartment());
		newEmployee.setManagerName(empReqBody.getManagerName());
		return employeeRepository.save(newEmployee);
	}

	public List<EmployeeEntity> listAllEmployees() {
		return (List<EmployeeEntity>) employeeRepository.findAll();
	}

	public String deleteEmployee(EmpIdRequest user) {
		int employeeId = user.getEmpId();
		employeeRepository.deleteById(employeeId);
		return "Record Deleted";
	}

//	public int getEmployeeBalanceLeave(int empId) {
//        EmployeeEntity employee = getEmployeeById(empId);
//        if (employee != null) {
//            int totalLeaves = employee.getTotalLeaves();
//            int leavesTaken = employee.getLeavesTaken();
//            return totalLeaves - leavesTaken;
//        } else {
//            return -1; // Or any other error code to indicate that the employee was not found
//        }
//    }

	public EmployeeEntity getEmployeeById(int empId) {
		Optional<EmployeeEntity> employeeOptional = employeeRepository.findById(empId);
		return employeeOptional.orElse(null);
	}

	public String countNumberOfEmployees() {

		return employeeRepository.countNumberOfEmployees();
	}
}
