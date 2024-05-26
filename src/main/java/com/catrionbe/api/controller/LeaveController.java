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

import com.catrionbe.api.entity.leaveEntity;
import com.catrionbe.api.model.LeaveCodeRequest;
import com.catrionbe.api.model.LeaveRequestBody;
import com.catrionbe.api.repositories.LeaveDao;
import com.catrionbe.api.service.LeaveService;
import com.google.common.base.Optional;

@RestController
@CrossOrigin
public class LeaveController {

	
	@Autowired
	private LeaveService leaveService;
	
	@Autowired
	private LeaveDao leaveDao;
	
	// Create Leave
	@RequestMapping(value = "/createLeave", method = RequestMethod.POST)
	public ResponseEntity<?> createLeave(@RequestBody LeaveRequestBody leaveReqBody) throws Exception {
		return ResponseEntity.ok(leaveService.createLeave(leaveReqBody));
	}
	
	//Update Leave
	@RequestMapping(value = "/updateLeave", method = RequestMethod.PUT)
	public ResponseEntity<?> updateLeave(@RequestBody LeaveRequestBody leaveReqBody) throws Exception {
	    java.util.Optional<leaveEntity> existingLeaveOptional = leaveDao.findById(leaveReqBody.getLeaveCode());
	    
	    if (existingLeaveOptional.isPresent()) {
	        leaveEntity existingLeave = existingLeaveOptional.get();
	        existingLeave.setDescription(leaveReqBody.getDescription());
	        existingLeave.setTotalDays(leaveReqBody.getTotalDays());
	        existingLeave.setLeaveCode(leaveReqBody.getLeaveCode());
	        existingLeave.setType(leaveReqBody.getType());
	        // Set other properties as needed
	        
	        // Save the updated leave
	        leaveEntity updatedLeave = leaveDao.save(existingLeave);
	        return ResponseEntity.ok(updatedLeave);
	    } else {
	        // Handle the case where the leave to be updated is not found
	        return ResponseEntity.notFound().build();
	    }
	}

	
	//Read leave
	@RequestMapping(value = "/listAllLeaves", method = RequestMethod.GET)
    public ResponseEntity<?> listAllLeaves() throws Exception {
        return ResponseEntity.ok(leaveService.listAllLeaves());
    }  	
	
	//Delete leave
	@RequestMapping(value = "/deleteLeave", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteLeave(@RequestBody LeaveCodeRequest leave) throws Exception {
		return ResponseEntity.ok(leaveService.deleteLeave(leave));
	}	
	
	@GetMapping("/leave/{leaveCode}")
    public ResponseEntity<leaveEntity> getLeaveByCode(@PathVariable int leaveCode) {
        leaveEntity leave = leaveService.getLeaveByCode(leaveCode);
        if (leave != null) {
            return ResponseEntity.ok(leave);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
