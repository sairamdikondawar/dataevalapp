package com.dataeval.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dataeval.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUserName(String username);
	
	@Query("select patient from User patient where patient.role.roleName = 'PATIENT' and (?1 is null or ?1 ='' or patient.firstName like %?1% or patient.lastName like %?1%)")
	public Page<User> findAllPatinets(String userName,Pageable page);
	
	@Query("select patient from User patient where patient.role.roleName = 'PATIENT' and patient.id =?1")
	public Optional<User> findAllPatinets(Integer id);
	
	@Query("select user from User user where (?2 is null or ?2 = '' or  user.role.roleName = ?2) and (?1 is null or ?1 ='' or user.firstName like %?1% or user.lastName like %?1%)")
	public Page<User> findAllUsers(String userName, String roleName,Pageable page);
}
