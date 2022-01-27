package com.dataeval.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dataeval.model.entity.FlowPage;

@Repository
public interface PageRepository extends JpaRepository<FlowPage, Integer> {
	
	
	public List<FlowPage> findAllByStatusOrderBySequenceAsc(String status);
	
	@Query("select new FlowPage(fp.id, fp.name) from FlowPage fp where fp.status  like 'Active' order by fp.sequence asc")
	public List<FlowPage> lookupPages();
	
	 

}
