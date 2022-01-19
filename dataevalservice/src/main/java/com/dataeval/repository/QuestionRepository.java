package com.dataeval.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dataeval.model.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

}
