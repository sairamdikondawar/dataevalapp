package com.dataeval.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dataeval.model.entity.UserSection;

@Repository
public interface UserSectionsRepository extends JpaRepository<UserSection, Integer> {

}
