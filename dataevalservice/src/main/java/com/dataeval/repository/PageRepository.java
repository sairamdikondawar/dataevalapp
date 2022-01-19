package com.dataeval.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dataeval.model.entity.FlowPage;

@Repository
public interface PageRepository extends JpaRepository<FlowPage, Integer> {
	
	 

}
