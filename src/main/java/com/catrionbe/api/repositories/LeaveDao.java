package com.catrionbe.api.repositories;

import com.catrionbe.api.entity.leaveEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveDao extends CrudRepository<leaveEntity, Integer> {

 
    @Query(value = "SELECT *  FROM aditya_leave ", nativeQuery = true)
	Page<leaveEntity> listallusersfromdb( Pageable pageable);
 
    
    }
