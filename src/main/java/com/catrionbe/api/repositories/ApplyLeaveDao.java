package com.catrionbe.api.repositories;

import com.catrionbe.api.entity.applyLeaveEntity;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplyLeaveDao extends JpaRepository<applyLeaveEntity, Integer> {

 
    @Query(value = "SELECT *  FROM aditya_applyLeave ", nativeQuery = true)
	Page<applyLeaveEntity> listallEntries( Pageable pageable);

    List<applyLeaveEntity> findByEmpId(int empId);



    
    }