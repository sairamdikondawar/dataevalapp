package com.dataeval.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dataeval.model.entity.FlowConfig;

@Repository
public interface FlowConfigRepository extends JpaRepository<FlowConfig, Integer> {

}
