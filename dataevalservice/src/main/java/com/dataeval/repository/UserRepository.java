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
	
	@Query("select patient from User patient where patient.role.roleName = 'PATIENT' and (?1 is null or ?1 ='' or patient.userName like %?1% ) and (?2 is null or ?2 =''  or patient.firstName like %?2%) and (?3 is null or ?3 ='' or patient.lastName like %?3%)")
	public Page<User> findAllPatinets(String userName,String firstName,String lastName,Pageable page);
	
	@Query("select patient from User patient where patient.role.roleName = 'PATIENT' and patient.id =?1")
	public Optional<User> findAllPatinets(Integer id);
	
	@Query("select user from User user where (?2 is null or ?2 = '' or  user.role.roleName = ?2) and (?1 is null or ?1 ='' or user.userName like %?1% ) and (?3 is null or ?3 ='' or user.firstName like %?3% ) and (?4 is null or ?4 ='' or user.lastName like %?4%)")
	public Page<User> findAllUsers(String userName, String roleName,String firstName,String lastName,Pageable page);
}
