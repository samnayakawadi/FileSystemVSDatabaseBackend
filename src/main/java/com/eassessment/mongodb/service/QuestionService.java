package com.eassessment.mongodb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eassessment.mongodb.exception.QuestionNotFoundException;
import com.eassessment.mongodb.model.QuestionModel;
import com.eassessment.mongodb.repository.QuestionRepository;
import com.eassessment.mongodb.response.StringResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class QuestionService {

	@Autowired
	QuestionRepository questionRepository;
	
	public StringResponse addQuestion(QuestionModel questionModel) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		
		questionModel.setCreatedDate(""+dtf.format(now)+"");
		questionModel.setModifiedDate("Not Modified Yet");
		
		QuestionModel document = questionRepository.save(questionModel);
		return new StringResponse(document.getQuestionId(), true, "Question Added to Database");
	}

	public QuestionModel getQuestion(String questionId) {
		QuestionModel document = questionRepository.findByQuestionId(questionId);
		
		if(document == null) {
			throw new QuestionNotFoundException();
		}
		
		return document;
	}

	public List<QuestionModel> getQuestions() {
		return questionRepository.findAll();
	}

	public StringResponse updateQuestion(QuestionModel questionModel) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		
		questionModel.setModifiedDate(""+dtf.format(now)+"");
		
		QuestionModel document = questionRepository.findByQuestionId(questionModel.getQuestionId());
		
		if(document == null) {
			throw new QuestionNotFoundException();
		}
		
		questionRepository.save(questionModel);
		
		return new StringResponse(document.getQuestionId(), true, "Question Updated to Database");
	}

	public StringResponse deleteQuestion(String questionId) {
		
		QuestionModel document = questionRepository.findByQuestionId(questionId);
		
		if(document == null) {
			throw new QuestionNotFoundException();
		}
		
		questionRepository.deleteById(questionId);
		return new StringResponse(questionId, true, "Question Deleted from Database");
	}

}
