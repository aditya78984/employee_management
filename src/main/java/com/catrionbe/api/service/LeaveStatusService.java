package com.catrionbe.api.service;

import com.catrionbe.api.entity.leaveEntity;
import com.catrionbe.api.entity.leaveStatusEntity;
import com.catrionbe.api.repositories.ApplyLeaveDao;
import com.catrionbe.api.repositories.LeaveStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeaveStatusService {

    @Autowired
    private LeaveStatusRepository leaveStatusRepository;


    public List<leaveStatusEntity> getApprovedLeavesForAllEmployees() {
        return leaveStatusRepository.findByApprove("Approve");
    }

    public List<leaveStatusEntity> getRejectedLeavesForAllEmployees() {
        return leaveStatusRepository.findByApproveNot("Approve"); // Get all leaves that are not approved
    }
    
    public int getBalanceLeave(int empId) {
        // Total available leave days for all employees
        int totalLeave = 25;

        // Query the repository to get the total approved leave days for the employee
        List<leaveStatusEntity> approvedLeaves = leaveStatusRepository.findByEmpIdAndApprove(empId, "approve");
        int totalApprovedDays = approvedLeaves.stream().mapToInt(leaveStatusEntity::getTotalDays).sum();

        // Calculate the remaining leave balance
        int remainingLeave = totalLeave - totalApprovedDays;

        return remainingLeave;
    }
    
    
    public void approveLeave(int statusId) {
        leaveStatusRepository.findById(statusId).ifPresent(leaveStatus -> {
            leaveStatus.setApprove("Approve");
            leaveStatusRepository.save(leaveStatus);
        });
    }

    public void rejectLeave(int statusId) {
        leaveStatusRepository.findById(statusId).ifPresent(leaveStatus -> {
            leaveStatus.setApprove("Reject");
            leaveStatusRepository.save(leaveStatus);
        });
    }
    
    public void createLeaveStatus(leaveStatusEntity leaveStatus) {
        leaveStatusRepository.save(leaveStatus);
    }
    
    public leaveStatusEntity getLeaveStatusByLeaveId(int leaveId) {
        Optional<leaveStatusEntity> optionalLeaveStatus = leaveStatusRepository.findById(leaveId);
        return optionalLeaveStatus.orElse(null);
    }
    
    public leaveStatusEntity updateLeaveStatus(leaveStatusEntity leaveStatus) {
        return leaveStatusRepository.save(leaveStatus);
    }
    
    public void deleteLeaveStatusByLeaveId(int leaveId) {
        // Find the leaveStatusEntity by leaveId
        leaveStatusEntity leaveStatus = leaveStatusRepository.findByLeaveId(leaveId);

        // Check if leaveStatus is not null
        if (leaveStatus != null) {
            // Get the statusId
            int statusId = leaveStatus.getStatusId();

            // Delete the leaveStatusEntity using statusId
            leaveStatusRepository.deleteById(statusId);
        }
    }





}

