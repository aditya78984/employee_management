package com.catrionbe.api.repositories;

import com.catrionbe.api.entity.approverEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

 
@Repository
public interface  ApproverDao extends CrudRepository<approverEntity,Integer> {

	@Query(value = "select * from  aditya_approver ", nativeQuery = true)
	Page<approverEntity> listallusersfromdb(Pageable pageable);



}