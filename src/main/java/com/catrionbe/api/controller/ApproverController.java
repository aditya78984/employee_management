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

import com.catrionbe.api.entity.approverEntity;
import com.catrionbe.api.model.ApproverIdRequest;
import com.catrionbe.api.model.ApproverRequestBody;
import com.catrionbe.api.repositories.ApproverDao;
import com.catrionbe.api.service.ApproverService;
import com.google.common.base.Optional;

@RestController
@CrossOrigin
public class ApproverController {
	
	@Autowired
	private ApproverService approverService;	
	
	@Autowired
	private ApproverDao approverDao;	
	
	@RequestMapping(value = "/createApprover", method = RequestMethod.POST)
	public ResponseEntity<?> createApprover(@RequestBody ApproverRequestBody approverReqBody) throws Exception {
		return ResponseEntity.ok(approverService.createApprover(approverReqBody));
	}
	
	@RequestMapping(value = "/updateApprover", method = RequestMethod.PUT)
	public ResponseEntity<?> updateApprover(@RequestBody ApproverRequestBody approverReqBody) throws Exception {
	    java.util.Optional<approverEntity> existingApproverOptional = approverDao.findById(approverReqBody.getApproverId());
	    
	    if (existingApproverOptional.isPresent()) {
	        approverEntity existingApprover = existingApproverOptional.get();
	        existingApprover.setEmpId(approverReqBody.getEmpId());
	        existingApprover.setFirstName(approverReqBody.getFirstName());
	        existingApprover.setLastName(approverReqBody.getLastName());
	        existingApprover.setDepartment(approverReqBody.getDepartment());
	        existingApprover.setManagerName(approverReqBody.getManagerName());
	        
	        approverEntity updatedApprover = approverDao.save(existingApprover);
	        return ResponseEntity.ok(updatedApprover);
	    } else {
	        // Handle the case where the approver to be updated is not found
	        return ResponseEntity.notFound().build();
	    }
	}
	
	
	@RequestMapping(value = "/listallApprovers", method = RequestMethod.GET)
	public ResponseEntity<?> listallApprovers()  throws Exception {
		return ResponseEntity.ok(approverService.listallApprovers());
	} 	
	
	
	@RequestMapping(value = "/deleteApprover", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteApprover(@RequestBody ApproverIdRequest approver) throws Exception {
		return ResponseEntity.ok(approverService.deleteApprover(approver));
	}		
	
	@GetMapping("/approver/{empId}")
    public ResponseEntity<approverEntity> getApproverById(@PathVariable int empId) {
    	approverEntity approver = approverService.getApproverById(empId);
        if (approver != null) {
            return ResponseEntity.ok(approver);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	
}
