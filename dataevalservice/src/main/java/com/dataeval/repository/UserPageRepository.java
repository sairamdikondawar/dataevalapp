package com.dataeval.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dataeval.model.entity.UserPage;

@Repository
public interface UserPageRepository extends JpaRepository<UserPage, Integer> {
	
	
	@Query("select page from UserPage page where page.userForm.id =?1")
	public List<UserPage> findAllByFormId(Integer formId);

}
