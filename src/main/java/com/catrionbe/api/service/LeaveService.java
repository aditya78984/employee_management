package com.catrionbe.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catrionbe.api.entity.leaveEntity;
import com.catrionbe.api.model.LeaveCodeRequest;
import com.catrionbe.api.model.LeaveRequestBody;
import com.catrionbe.api.repositories.LeaveDao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
public class LeaveService  {

	@Autowired
	private LeaveDao leaveDao;

	

	public leaveEntity createLeave(LeaveRequestBody leaveReqBody) {

		leaveEntity newleave = new leaveEntity();
		newleave.setType(leaveReqBody.getType());
		newleave.setDescription(leaveReqBody.getDescription());
		newleave.setTotalDays(leaveReqBody.getTotalDays());
		newleave.setLeaveCode(leaveReqBody.getLeaveCode());
		return leaveDao.save(newleave);		 
	}

	public leaveEntity updateLeave(LeaveRequestBody leaveReqBody) {
	    Optional<leaveEntity> existingLeaveOptional = leaveDao.findById(leaveReqBody.getLeaveCode());
	    
	    if (existingLeaveOptional.isPresent()) {
	        leaveEntity existingLeave = existingLeaveOptional.get();
	        existingLeave.setDescription(leaveReqBody.getDescription());
	        existingLeave.setTotalDays(leaveReqBody.getTotalDays());
	        existingLeave.setType(leaveReqBody.getType());
	        
	        // Save the updated leave
	        leaveEntity updatedLeave = leaveDao.save(existingLeave);
	        return updatedLeave;
	    } else {
	        // Handle the case where the leave to be updated is not found
	        return null; // Or you can throw an exception
	    }
	}

	
	public List<leaveEntity> listAllLeaves() {
        return (List<leaveEntity>) leaveDao.findAll();
	}
 
	public String deleteLeave(LeaveCodeRequest leave) {
		int leaveCode= leave.getLeaveCode();
		leaveDao.deleteById(leaveCode);
		return "Record Deleted";
	}
	
	
	public leaveEntity getLeaveByCode(int leaveCode) {
        Optional<leaveEntity> leaveOptional = leaveDao.findById(leaveCode);
        return leaveOptional.orElse(null);
    }

}

