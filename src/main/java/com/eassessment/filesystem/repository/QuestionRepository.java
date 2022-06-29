package com.eassessment.filesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eassessment.filesystem.entity.QuestionEntity;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, String> {
	
	QuestionEntity findByQuestionId(String questionId);

}
