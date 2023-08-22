package com.main.hrms.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.hrms.model.TrackingTraining;
import com.main.hrms.repository.TrackingTrainingRepository;

@Service
public class TrackingTrainingService {
	
	@Autowired
	private TrackingTrainingRepository trackingtrainingrepository;
	
	public List<TrackingTraining> getData(){
		return trackingtrainingrepository.findAll();
	}
	
	public Optional<TrackingTraining> getData(Integer id){
		return trackingtrainingrepository.findById(id);
	}
	
	public Optional<TrackingTraining> insertData(TrackingTraining trackingtraining){
		if(trackingtrainingrepository.existsById(trackingtraining.getTrackingid())){
			return Optional.empty();
		}
		else {
			return Optional.of(trackingtrainingrepository.save(trackingtraining));
		}	
	}
	
	public Optional<TrackingTraining> updateData(TrackingTraining trackingtraining){
		if(trackingtrainingrepository.existsById(trackingtraining.getTrackingid())){
			return Optional.of(trackingtrainingrepository.save(trackingtraining));
		}
		else{
			return Optional.empty();
		}	
	}
	
	public String deleteData(int id){
		if(trackingtrainingrepository.existsById(id)) { 
			trackingtrainingrepository.deleteById(id);
			return id +"Deleted successfully";
		}else {
			return "Data Does not exit in record";
		}
	}
}