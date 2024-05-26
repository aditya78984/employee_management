package com.catrionbe.api.controller;

import com.catrionbe.api.entity.leaveStatusEntity;
import com.catrionbe.api.repositories.LeaveStatusRepository;
import com.catrionbe.api.service.LeaveStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class LeaveStatusController {
	
    private final LeaveStatusRepository leaveStatusRepository;


    @Autowired
    private LeaveStatusService leaveStatusService;
    
    public LeaveStatusController(LeaveStatusRepository repository) {
        this.leaveStatusRepository = repository;
    }

    @RequestMapping(value = "/approvedLeaves", method = RequestMethod.GET)
    public ResponseEntity<List<leaveStatusEntity>> getApprovedLeavesForAllEmployees() {
        List<leaveStatusEntity> approvedLeavesList = leaveStatusService.getApprovedLeavesForAllEmployees();
        return ResponseEntity.ok(approvedLeavesList);
    }

    @RequestMapping(value = "/rejectedLeaves", method = RequestMethod.GET)
    public ResponseEntity<List<leaveStatusEntity>> getRejectedLeavesForAllEmployees() {
        List<leaveStatusEntity> rejectedLeavesList = leaveStatusService.getRejectedLeavesForAllEmployees();
        return ResponseEntity.ok(rejectedLeavesList);
    }
    
    @GetMapping("/balanceLeave/{empId}")
    public ResponseEntity<Integer> getBalanceLeaveForEmployee(@PathVariable int empId) {
        int balanceLeave = leaveStatusService.getBalanceLeave(empId);
        return ResponseEntity.ok(balanceLeave);
    }
    
    // Endpoint to approve leave
    @PutMapping("/approve/{statusId}")
    public ResponseEntity<String> approveLeave(@PathVariable int statusId) {
        Optional<leaveStatusEntity> leaveStatusOptional = leaveStatusRepository.findById(statusId);
        if (leaveStatusOptional.isPresent()) {
            leaveStatusService.approveLeave(statusId);
            return ResponseEntity.ok("Leave request approved successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Leave request not found");
        }
    }

 // Endpoint to reject leave
    @PutMapping("/reject/{statusId}")
    public ResponseEntity<String> rejectLeave(@PathVariable int statusId) {
        Optional<leaveStatusEntity> leaveStatusOptional = leaveStatusRepository.findById(statusId);
        if (leaveStatusOptional.isPresent()) {
            leaveStatusService.rejectLeave(statusId);
            return ResponseEntity.ok("Leave request rejected successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Leave request not found");
        }
    }
}
