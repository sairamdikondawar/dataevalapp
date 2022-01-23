package com.dataeval.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dataeval.model.entity.UserQuestion;

@Repository
public interface UserQuestionRepository extends JpaRepository<UserQuestion, Integer> {

}
