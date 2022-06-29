package com.eassessment.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class QuestionModel {
	
	@Id
	private String questionId;
	private String question = "NOT PROVIDED";
	private String type = "NOT PROVIDED";
	private Integer score = 0;
	private String answer1 = "NOT PROVIDED";
	private String answer2 = "NOT PROVIDED";
	private String answer3 = "NOT PROVIDED";
	private String answer4 = "NOT PROVIDED";
	private String answer5 = "NOT PROVIDED";
	private String correctAnswer = "NOT PROVIDED";
	private String correctFeedback = "NOT PROVIDED";
	private String wrongFeedback = "NOT PROVIDED";
	private Boolean shuffleOptions = false;
	private String createdDate = "NOT PROVIDED";
	private String modifiedDate = "NOT PROVIDED";
	private Long learningTime = 60L;
	private String difficulty = "NOT PROVIDED";
	
	public QuestionModel() {
		super();
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getAnswer1() {
		return answer1;
	}
	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}
	public String getAnswer2() {
		return answer2;
	}
	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}
	public String getAnswer3() {
		return answer3;
	}
	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}
	public String getAnswer4() {
		return answer4;
	}
	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}
	public String getAnswer5() {
		return answer5;
	}
	public void setAnswer5(String answer5) {
		this.answer5 = answer5;
	}
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	public String getCorrectFeedback() {
		return correctFeedback;
	}
	public void setCorrectFeedback(String correctFeedback) {
		this.correctFeedback = correctFeedback;
	}
	public String getWrongFeedback() {
		return wrongFeedback;
	}
	public void setWrongFeedback(String wrongFeedback) {
		this.wrongFeedback = wrongFeedback;
	}
	public Boolean getShuffleOptions() {
		return shuffleOptions;
	}
	public void setShuffleOptions(Boolean shuffleOptions) {
		this.shuffleOptions = shuffleOptions;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Long getLearningTime() {
		return learningTime;
	}
	public void setLearningTime(Long learningTime) {
		this.learningTime = learningTime;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

}
