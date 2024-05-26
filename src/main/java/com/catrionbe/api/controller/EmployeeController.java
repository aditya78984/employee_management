package com.catrionbe.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.catrionbe.api.entity.EmployeeEntity;
import com.catrionbe.api.model.EmpIdRequest;
import com.catrionbe.api.model.EmployeeRequestBody;
import com.catrionbe.api.service.EmployeeService;

@RestController
@CrossOrigin
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/createEmployee", method = RequestMethod.POST)
	public ResponseEntity<?> createEmployee(@RequestBody EmployeeRequestBody employeeReqBody) throws Exception {
		return ResponseEntity.ok(employeeService.createEmployee(employeeReqBody));
	}

	@RequestMapping(value = "/updateEmployee", method = RequestMethod.PUT)
	public ResponseEntity<?> updateEmployee(@RequestBody EmployeeRequestBody employeeReqBody) throws Exception {
		return ResponseEntity.ok(employeeService.updateEmployee(employeeReqBody));
	}

	@RequestMapping(value = "/listAllEmployees", method = RequestMethod.GET)
	public ResponseEntity<?> listAllEmployees() throws Exception {
		return ResponseEntity.ok(employeeService.listAllEmployees());
	}

	@RequestMapping(value = "/deleteEmployee", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteEmployee(@RequestBody EmpIdRequest user) throws Exception {
		return ResponseEntity.ok(employeeService.deleteEmployee(user));
	}

//	@GetMapping("/employee/{empId}/balanceLeave")
//    public ResponseEntity<Integer> getEmployeeBalanceLeave(@PathVariable int empId) {
//        int balanceLeave = employeeService.getEmployeeBalanceLeave(empId);
//        return ResponseEntity.ok(balanceLeave);
//    }

	@GetMapping("/employee/id/{empId}")
	public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable int empId) {
		EmployeeEntity employee = employeeService.getEmployeeById(empId);
		if (employee != null) {
			return ResponseEntity.ok(employee);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(value = "/employeescount", method = RequestMethod.GET)
	public ResponseEntity<?> countNumberOfEmployees() throws Exception {
		return ResponseEntity.ok((employeeService.countNumberOfEmployees()));
	}

}
