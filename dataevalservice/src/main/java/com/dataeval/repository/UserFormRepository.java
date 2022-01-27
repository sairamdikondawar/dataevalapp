package com.dataeval.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dataeval.model.entity.Question;
import com.dataeval.model.entity.UserForm;

@Repository
public interface UserFormRepository extends JpaRepository<UserForm, Integer> {
	
	
	@Query("select userForm from UserForm userForm where ( ?2 is null or  userForm.user.userName like %?2%  ) and  (?1 is null or userForm.user.role.id = ?1) and  (?3 is null or userForm.creationDate  >= ?3)")
	public Page<UserForm> findAllUserFomrs(Integer roleId, String userName, Date startDate, Pageable page);


}
