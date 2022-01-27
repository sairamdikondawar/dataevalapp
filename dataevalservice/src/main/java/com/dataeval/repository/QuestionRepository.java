package com.dataeval.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dataeval.model.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
	
	
	@Query("select question from Question question where ( ?2 is null or  question.name like %?2%  ) and  (?1 is null or question.section.id = ?1)")
	public Page<Question> findAllQuestions(Integer sectionName, String questionName, Pageable page);

}
