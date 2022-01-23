package com.dataeval.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dataeval.model.entity.UserForm;

@Repository
public interface UserFormRepository extends JpaRepository<UserForm, Integer> {

}
