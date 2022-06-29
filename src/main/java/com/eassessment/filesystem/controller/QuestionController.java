package com.eassessment.filesystem.controller;

import java.util.List;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eassessment.filesystem.model.QuestionModel;
import com.eassessment.filesystem.response.StringResponse;
import com.eassessment.filesystem.service.QuestionService;

@RestController
@CrossOrigin(origins = "*")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;

	@GetMapping("/")
	public String getWelcomeMessage() {
		return "Welcome to eAssessment using File System + SQL";
	}
	
	@PostMapping("/questions/post")
	public StringResponse addQuestion(@RequestBody QuestionModel questionModel) {
		return questionService.addQuestion(questionModel);
	}
	
	@GetMapping("/questions/get/single/{questionId}")
	public QuestionModel getQuestion(@PathVariable String questionId) {
		return questionService.getQuestion(questionId);
	}
	
	@GetMapping("/questions/get/all")
	public List<QuestionModel> getQuestions(){
		return questionService.getQuestions();
	}
	
	@PutMapping("/questions/put/single")
	public StringResponse updateQuestion(@RequestBody QuestionModel questionModel) throws ParseException {
		return questionService.updateQuestion(questionModel);
	}
	
	@DeleteMapping("/questions/delete/single/{questionId}")
	public StringResponse deleteQuestion(@PathVariable String questionId) {
		return questionService.deleteQuestion(questionId);
	}
}
