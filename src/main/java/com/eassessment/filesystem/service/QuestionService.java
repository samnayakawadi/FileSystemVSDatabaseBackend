package com.eassessment.filesystem.service;

//Additional Imports
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eassessment.filesystem.entity.QuestionEntity;
import com.eassessment.filesystem.exception.QuestionNotFoundException;
import com.eassessment.filesystem.model.QuestionModel;
import com.eassessment.filesystem.repository.QuestionRepository;
import com.eassessment.filesystem.response.StringResponse;

@Service
public class QuestionService {

	private static final String FILEPATH = "C:/Users/samnayakawadi/Documents/Spring Boot Workspace/filesystem/src/main/resources/questions/";

	@Autowired
	private QuestionRepository questionRepository;

	// jsonObject.put extends HashMap but doesn't support Generic, so Eclipse gives
	// warnings.
	@SuppressWarnings("unchecked")
	public StringResponse addQuestion(QuestionModel questionModel) {

		// Getting current date & time
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		// creating the object of QuestionEntity format
		QuestionEntity entity = new QuestionEntity();

		// first creating the entity in DB so that we can get the generated ID
		QuestionEntity dbEntity = questionRepository.save(entity);

		// Creating a JSONObject object
		JSONObject jsonObject = new JSONObject();
		// Inserting key-value pairs into the JSON object
		jsonObject.put("questionId", dbEntity.getQuestionId()); // We are using that id here
		jsonObject.put("question", questionModel.getQuestion());
		jsonObject.put("type", questionModel.getType());
		jsonObject.put("score", questionModel.getScore());
		jsonObject.put("answer1", questionModel.getAnswer1());
		jsonObject.put("answer2", questionModel.getAnswer2());
		jsonObject.put("answer3", questionModel.getAnswer3());
		jsonObject.put("answer4", questionModel.getAnswer4());
		jsonObject.put("answer5", questionModel.getAnswer5());
		jsonObject.put("correctAnswer", questionModel.getCorrectAnswer());
		jsonObject.put("correctFeedback", questionModel.getCorrectFeedback());
		jsonObject.put("wrongFeedback", questionModel.getWrongFeedback());
		jsonObject.put("shuffleOptions", questionModel.getShuffleOptions());
		jsonObject.put("createdDate", dtf.format(now));
		jsonObject.put("modifiedDate", "Not Modified Yet");
		jsonObject.put("learningTime", questionModel.getLearningTime());
		jsonObject.put("difficulty", questionModel.getDifficulty());

		try {
			// creating file.json
			// we will be using that id here in file name also
			FileWriter file = new FileWriter(FILEPATH + dbEntity.getQuestionId() + ".json");
			// adding the content
			file.write(jsonObject.toJSONString());
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// adding other details to DB along with above id. (We are using the save but it
		// will update as this OBJ has id inside)
		dbEntity.setFileName("" + dbEntity.getQuestionId() + ".json");
		dbEntity.setFilePath(FILEPATH + dbEntity.getQuestionId() + ".json");
		dbEntity.setCreatedDate(dtf.format(now));
		questionRepository.save(dbEntity);

		return new StringResponse(dbEntity.getQuestionId(), true, "Question Created Successfully");
	}

	public QuestionModel getQuestion(String questionId) {
		// Creating a QuestionModel object so that we can set the data from the file & return to controller
		QuestionModel entity = new QuestionModel();
		QuestionEntity dbEntity = questionRepository.findByQuestionId(questionId);
		
		if(dbEntity == null) {
			// Throwing Exception when dbEntity is NULL
			throw new QuestionNotFoundException();
		}
	
		// Creating a JSONParser object
		JSONParser jsonParser = new JSONParser();
		try {

			// Parsing the contents of the JSON file
			FileReader file = new FileReader(FILEPATH + questionId + ".json");
			JSONObject jsonObject = (JSONObject) jsonParser.parse(file);
			file.close();
			// Parsing the contents of the JSON file
			entity.setQuestionId((String) jsonObject.get("questionId"));
			entity.setQuestion((String) jsonObject.get("question"));
			entity.setType((String) jsonObject.get("type"));
			// jsonObject was return an Integer number as LONG by default but score needed Integer & Not LONG.
			// But, Integer.parseInt(Needs String here...) Hence, we converted that LONG number to String by + ""
			entity.setScore(Integer.parseInt(jsonObject.get("score")+""));
			entity.setAnswer1((String) jsonObject.get("answer1"));
			entity.setAnswer2((String) jsonObject.get("answer2"));
			entity.setAnswer3((String) jsonObject.get("answer3"));
			entity.setAnswer4((String) jsonObject.get("answer4"));
			entity.setAnswer5((String) jsonObject.get("answer5"));
			entity.setCorrectAnswer((String) jsonObject.get("correctAnswer"));
			entity.setCorrectFeedback((String) jsonObject.get("correctFeedback"));
			entity.setWrongFeedback((String) jsonObject.get("wrongFeedback"));
			entity.setShuffleOptions((Boolean) jsonObject.get("shuffleOptions"));
			entity.setCreatedDate((String) jsonObject.get("createdDate"));
			entity.setModifiedDate((String) jsonObject.get("modifiedDate"));
			entity.setLearningTime((Long) jsonObject.get("learningTime"));
			entity.setDifficulty((String) jsonObject.get("difficulty"));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return entity;
	}

	public List<QuestionModel> getQuestions() {
		
		List<QuestionEntity> questionEntities = questionRepository.findAll();
		List<QuestionModel> questionModels = new ArrayList<QuestionModel>();
		
		for(QuestionEntity qe : questionEntities) {
			questionModels.add(getQuestion(qe.getQuestionId()));
		}
		
		return questionModels;
	}

	@SuppressWarnings("unchecked")
	public StringResponse updateQuestion(QuestionModel questionModel) throws ParseException {
		
		System.out.println("Inside Update Q Service");
		
		QuestionEntity dbQuestionEntity = questionRepository.findByQuestionId(questionModel.getQuestionId());
		
		if(dbQuestionEntity == null) {
			throw new QuestionNotFoundException();
		}
		
		// Getting current date & time
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String createdDate = "Not Found";
		
		try {
			FileReader reader = new FileReader(FILEPATH + questionModel.getQuestionId() + ".json");
			JSONParser parser = new JSONParser();
			JSONObject data = (JSONObject) parser.parse(reader);
			reader.close();
			createdDate = (String) data.get("createdDate");
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		// Creating a JSONObject object
		JSONObject jsonObject = new JSONObject();
		// Inserting key-value pairs into the JSON object
		jsonObject.put("questionId", questionModel.getQuestionId()); // We are using that id here
		jsonObject.put("question", questionModel.getQuestion());
		jsonObject.put("type", questionModel.getType());
		jsonObject.put("score", questionModel.getScore());
		jsonObject.put("answer1", questionModel.getAnswer1());
		jsonObject.put("answer2", questionModel.getAnswer2());
		jsonObject.put("answer3", questionModel.getAnswer3());
		jsonObject.put("answer4", questionModel.getAnswer4());
		jsonObject.put("answer5", questionModel.getAnswer5());
		jsonObject.put("correctAnswer", questionModel.getCorrectAnswer());
		jsonObject.put("correctFeedback", questionModel.getCorrectFeedback());
		jsonObject.put("wrongFeedback", questionModel.getWrongFeedback());
		jsonObject.put("shuffleOptions", questionModel.getShuffleOptions());
		jsonObject.put("createdDate", createdDate);
		jsonObject.put("modifiedDate", dtf.format(now));
		jsonObject.put("learningTime", questionModel.getLearningTime());
		jsonObject.put("difficulty", questionModel.getDifficulty());

		try {
			// creating file.json
			// we will be using that id here in file name also
			FileWriter file = new FileWriter(FILEPATH + questionModel.getQuestionId() + ".json");
			// adding the content
			file.write(jsonObject.toJSONString());
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new StringResponse(questionModel.getQuestionId(), true, "Question Updated Successfully");
	}

	public StringResponse deleteQuestion(String questionId) {
		
		QuestionEntity questionEntity = questionRepository.findByQuestionId(questionId);
		
		if(questionEntity == null) {
			throw new QuestionNotFoundException();
		}
		
		try {
			File file = new File(FILEPATH + questionId + ".json");
			if(file.delete()) {
				questionRepository.deleteById(questionId);
				return new StringResponse(questionId, true, "Question Deleted Successfully");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return new StringResponse(questionId, false, "Question Deletion Failed");
	}

}
