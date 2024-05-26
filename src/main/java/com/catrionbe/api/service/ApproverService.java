package com.catrionbe.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import com.catrionbe.api.entity.approverEntity;
import com.catrionbe.api.entity.leaveEntity;
import com.catrionbe.api.model.ApproverIdRequest;
import com.catrionbe.api.model.ApproverRequestBody;
import com.catrionbe.api.repositories.ApproverDao;

@Service
public class ApproverService  {

	@Autowired
	private ApproverDao approverDao;

	

	public approverEntity createApprover(ApproverRequestBody approverReqBody) {

		approverEntity newApprover = new approverEntity();
		newApprover.setApproverId(approverReqBody.getApproverId());
		newApprover.setFirstName(approverReqBody.getFirstName());
		newApprover.setLastName(approverReqBody.getLastName());
		newApprover.setDepartment(approverReqBody.getDepartment());
		newApprover.setManagerName(approverReqBody.getManagerName());
		newApprover.setEmpId(approverReqBody.getEmpId());
		return approverDao.save(newApprover);		 
	}

	public approverEntity updateApprover(ApproverRequestBody approverReqBody) {
	    Optional<approverEntity> existingApproverOptional = approverDao.findById(approverReqBody.getApproverId());

	    if (existingApproverOptional.isPresent()) {
	        approverEntity existingApprover = existingApproverOptional.get();
	        existingApprover.setEmpId(approverReqBody.getEmpId());
	        existingApprover.setFirstName(approverReqBody.getFirstName());
	        existingApprover.setLastName(approverReqBody.getLastName());
	        existingApprover.setDepartment(approverReqBody.getDepartment());
	        existingApprover.setManagerName(approverReqBody.getManagerName());

	        // Save the updated entity
	        return approverDao.save(existingApprover);
	    } else {
	        // Handle the case where the approver to be updated is not found
	        // You can throw an exception or return null, depending on your application's logic
	        return null;
	    }
	}


	public List<approverEntity> listallApprovers() {
		return (List<approverEntity>)approverDao.findAll();
	}
 
	public String deleteApprover(ApproverIdRequest approver) {
		int approverId= approver.getApproverId();
		approverDao.deleteById(approverId);
		return "Record Deleted";
	}
	
	public approverEntity getApproverById(int approverId) {
        Optional<approverEntity> employeeOptional = approverDao.findById(approverId);
        return employeeOptional.orElse(null);
    }

}
