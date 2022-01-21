package com.dataeval.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dataeval.model.entity.PageSection;

@Repository
public interface PageSectionRepository extends JpaRepository<PageSection, Integer> {

}
