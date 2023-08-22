package com.main.hrms.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.hrms.model.Training;
import com.main.hrms.repository.TrainingRepository;

@Service
public class TrainingService {
	
	@Autowired
	private TrainingRepository trainingrepository;
	
	public List<Training> getData(){
		return trainingrepository.findAll();
	}
	
	public Optional<Training> getData(Integer id){
		return trainingrepository.findById(id);
	}
	
	public Optional<Training> insertData(Training training){
		if(trainingrepository.existsById(training.getTrainingid())){
			return Optional.empty();
		}
		else {
			return Optional.of(trainingrepository.save(training));
		}	
	}
	
	public Optional<Training> updateData(Training training){
		if(trainingrepository.existsById(training.getTrainingid())){
			return Optional.of(trainingrepository.save(training));
		}
		else{
			return Optional.empty();
		}	
	}
	
	public String deleteData(int id){
		if(trainingrepository.existsById(id)) { 
			trainingrepository.deleteById(id);
			return id +"Deleted successfully";
		}else {
			return "Data Does not exit in record";
		}
	}
}