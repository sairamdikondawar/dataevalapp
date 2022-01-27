package com.dataeval.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dataeval.model.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	
	public List<Role> findAllByStatus(String status);

}
