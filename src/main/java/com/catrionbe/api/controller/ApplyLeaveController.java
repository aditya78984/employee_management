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

import com.catrionbe.api.entity.applyLeaveEntity;
import com.catrionbe.api.model.ApplyLeaveBodyRequest;
import com.catrionbe.api.model.ApplyLeaveIdRequest;
import com.catrionbe.api.repositories.ApplyLeaveDao;
import com.catrionbe.api.service.ApplyLeaveService;
import com.google.common.base.Optional;

import java.util.List;

@RestController
@CrossOrigin
public class ApplyLeaveController {

    @Autowired
    private ApplyLeaveService applyleaveService;

    @Autowired
    private ApplyLeaveDao applyLeaveDao; // Autowire ApplyLeaveDao

    // Create Leave
    @RequestMapping(value = "/applyLeave", method = RequestMethod.POST)
    public ResponseEntity<?> createApplyLeave(@RequestBody ApplyLeaveBodyRequest applyleaveReqBody) throws Exception {
        return ResponseEntity.ok(applyleaveService.createApplyLeave(applyleaveReqBody));
    }

    // Update Leave
    @RequestMapping(value = "/updateAppliedLeave", method = RequestMethod.PUT)
    public ResponseEntity<?> updateApplyLeave(@RequestBody ApplyLeaveBodyRequest applyleaveReqBody) throws Exception {
        java.util.Optional<applyLeaveEntity> existingLeaveOptional = applyLeaveDao.findById(applyleaveReqBody.getLeaveId());

        if (existingLeaveOptional.isPresent()) {
            applyLeaveEntity existingLeave = existingLeaveOptional.get();
            existingLeave.setType(applyleaveReqBody.getType());
            existingLeave.setTotalDays(applyleaveReqBody.getTotalDays());
            existingLeave.setEmpId(applyleaveReqBody.getEmpId());
            existingLeave.setStartDate(applyleaveReqBody.getStartDate());
            existingLeave.setEndDate(applyleaveReqBody.getEndDate());
            existingLeave.setReason(applyleaveReqBody.getReason());

            applyLeaveEntity updatedLeave = applyLeaveDao.save(existingLeave);
            return ResponseEntity.ok(updatedLeave);
        } else {
            // Handle the case where the leave to be updated is not found
            return ResponseEntity.notFound().build();
        }
    }

    // Read leave
    @RequestMapping(value = "/listAllAppliedLeaves", method = RequestMethod.GET)
    public ResponseEntity<?> listallEntries() throws Exception {
        return ResponseEntity.ok(applyleaveService.listallEntries());
    }

    // Delete leave
    @RequestMapping(value = "/deleteAppliedLeave", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteApplyLeave(@RequestBody ApplyLeaveIdRequest applyleave) throws Exception {
        return ResponseEntity.ok(applyleaveService.deleteApplyLeave(applyleave));
    }
    
//    @GetMapping("/employee/{empId}")
//    public ResponseEntity<List<applyLeaveEntity>> getAppliedLeavesByEmployeeId(@PathVariable int empId) {
//        Optional<List<applyLeaveEntity>> appliedLeavesOptional = applyleaveService.getAppliedLeavesByEmployeeId(empId);
//        if (appliedLeavesOptional.isPresent()) {
//            return ResponseEntity.ok(appliedLeavesOptional.get());
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
    
    @GetMapping("/employee/{empId}")
    public ResponseEntity<List<applyLeaveEntity>> getAppliedLeavesByEmployeeId(@PathVariable int empId) {
        List<applyLeaveEntity> appliedLeaves = applyleaveService.getAppliedLeavesByEmployeeId(empId);
        if (!appliedLeaves.isEmpty()) {
            return ResponseEntity.ok(appliedLeaves);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/{leaveId}")
    public ResponseEntity<applyLeaveEntity> getAppliedLeaveById(@PathVariable int leaveId) {
        applyLeaveEntity applyleave = applyleaveService.getApplyLeaveById(leaveId);
        if (applyleave != null) {
            return ResponseEntity.ok(applyleave);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
