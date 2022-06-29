package com.eassessment.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.eassessment.mongodb.model.QuestionModel;

@Repository
public interface QuestionRepository extends MongoRepository<QuestionModel, String> {
	QuestionModel findByQuestionId(String questionId);
}
