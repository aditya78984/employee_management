package com.catrionbe.api.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catrionbe.api.entity.applyLeaveEntity;
import com.catrionbe.api.entity.leaveStatusEntity;
import com.catrionbe.api.model.ApplyLeaveBodyRequest;
import com.catrionbe.api.model.ApplyLeaveIdRequest;
import com.catrionbe.api.repositories.ApplyLeaveDao;


@Service
public class ApplyLeaveService  {

	@Autowired
	private ApplyLeaveDao applyleaveDao;
    private final LeaveStatusService leaveStatusService;

    @Autowired
    public ApplyLeaveService(ApplyLeaveDao applyLeaveDao, LeaveStatusService leaveStatusService) {
        this.applyleaveDao = applyLeaveDao;
        this.leaveStatusService = leaveStatusService;
    }

	

//	public applyLeaveEntity createApplyLeave(ApplyLeaveBodyRequest applyleaveReqBody) {
//
//		applyLeaveEntity newapplyleave = new applyLeaveEntity();
//		newapplyleave.setType(applyleaveReqBody.getType());
//		newapplyleave.setLeaveId(applyleaveReqBody.getLeaveId());
//		newapplyleave.setTotalDays(applyleaveReqBody.getTotalDays());
//		newapplyleave.setEmpId(applyleaveReqBody.getEmpId());
//		newapplyleave.setStartDate(applyleaveReqBody.getStartDate());
//		newapplyleave.setEndDate(applyleaveReqBody.getEndDate());
//		newapplyleave.setReason(applyleaveReqBody.getReason());
//		return applyleaveDao.save(newapplyleave);		 
//	}
    
    public applyLeaveEntity createApplyLeave(ApplyLeaveBodyRequest applyLeaveReqBody) {
        applyLeaveEntity newApplyLeave = new applyLeaveEntity();
        // Set properties for applyLeaveEntity
        newApplyLeave.setType(applyLeaveReqBody.getType());
        newApplyLeave.setEmpId(applyLeaveReqBody.getEmpId());
        newApplyLeave.setTotalDays(applyLeaveReqBody.getTotalDays());
        newApplyLeave.setReason(applyLeaveReqBody.getReason());
        newApplyLeave.setStartDate(applyLeaveReqBody.getStartDate());
        newApplyLeave.setEndDate(applyLeaveReqBody.getEndDate());
        
        // Save applyLeaveEntity
        applyLeaveEntity savedApplyLeave = applyleaveDao.save(newApplyLeave);
        
        // Create corresponding entry in leaveStatus table
        leaveStatusEntity newLeaveStatus = new leaveStatusEntity();
        newLeaveStatus.setLeaveId(savedApplyLeave.getLeaveId());
        newLeaveStatus.setEmpId(savedApplyLeave.getEmpId());
        newLeaveStatus.setTotalDays(savedApplyLeave.getTotalDays());
        newLeaveStatus.setApprove("Pending");
        
        // Save leaveStatusEntity
        leaveStatusService.createLeaveStatus(newLeaveStatus);
        
        return savedApplyLeave;
    }
    
    public applyLeaveEntity updateApplyLeave(ApplyLeaveBodyRequest applyleaveReqBody) {
        int leaveId = applyleaveReqBody.getLeaveId();
        Optional<applyLeaveEntity> optionalApplyLeave = applyleaveDao.findById(leaveId);
        if (optionalApplyLeave.isPresent()) {
            applyLeaveEntity existingApplyLeave = optionalApplyLeave.get();
            // Update properties of existing applyLeaveEntity
            existingApplyLeave.setType(applyleaveReqBody.getType());
            existingApplyLeave.setTotalDays(applyleaveReqBody.getTotalDays());
            existingApplyLeave.setStartDate(applyleaveReqBody.getStartDate());
            existingApplyLeave.setEndDate(applyleaveReqBody.getEndDate());
            existingApplyLeave.setReason(applyleaveReqBody.getReason());
            // Save the updated applyLeaveEntity
            applyleaveDao.save(existingApplyLeave);

            // Get corresponding leaveStatusEntity
            leaveStatusEntity leaveStatus = leaveStatusService.getLeaveStatusByLeaveId(leaveId);
            // Update leaveStatusEntity properties
            leaveStatus.setTotalDays(existingApplyLeave.getTotalDays());
            // Save the updated leaveStatusEntity
            leaveStatusService.updateLeaveStatus(leaveStatus);

            // Return the updated applyLeaveEntity
            return existingApplyLeave;
        } else {
            // Handle case where applyLeaveEntity with given leaveId is not found
            return null;
        }
    }

    public String deleteApplyLeave(ApplyLeaveIdRequest applyleave) {
        int leaveId = applyleave.getLeaveId();
        applyleaveDao.deleteById(leaveId);

        // Delete corresponding entry in leaveStatus table
        leaveStatusService.deleteLeaveStatusByLeaveId(leaveId);

        return "Record Deleted";
    }

//	public applyLeaveEntity updateApplyLeave(ApplyLeaveBodyRequest applyleaveReqBody) {
//		applyLeaveEntity newapplyleave = new applyLeaveEntity();
//		newapplyleave.setType(applyleaveReqBody.getType());
//		newapplyleave.setTotalDays(applyleaveReqBody.getTotalDays());
//		newapplyleave.setEmpId(applyleaveReqBody.getEmpId());
//		newapplyleave.setStartDate(applyleaveReqBody.getStartDate());
//		newapplyleave.setEndDate(applyleaveReqBody.getEndDate());
//		newapplyleave.setReason(applyleaveReqBody.getReason());
//		return applyleaveDao.save(newapplyleave);		 
//	}
//
	public List<applyLeaveEntity> listallEntries() {
        return (List<applyLeaveEntity>) applyleaveDao.findAll();
	}
// 
//	public String deleteApplyLeave(ApplyLeaveIdRequest applyleave) {
//		int applyleaveCode= applyleave.getLeaveId();
//		applyleaveDao.deleteById(applyleaveCode);
//		return "Record Deleted";
//	}
			
	public List<applyLeaveEntity> getAppliedLeavesByEmployeeId(int empId) {
        return applyleaveDao.findByEmpId(empId);
    }
	
	public applyLeaveEntity getApplyLeaveById(int leaveId) {
        Optional<applyLeaveEntity> applyleaveOptional = applyleaveDao.findById(leaveId);
        return applyleaveOptional.orElse(null);
    }

}

