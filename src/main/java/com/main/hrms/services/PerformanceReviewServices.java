package com.main.hrms.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.hrms.model.PerformanceReview;
import com.main.hrms.repository.PerformanceReviewRepository;

@Service
public class PerformanceReviewServices {
	
	@Autowired
	private PerformanceReviewRepository reviewrepository;
	
	public List<PerformanceReview> getData(){
		return reviewrepository.findAll();
	}
	
	public Optional<PerformanceReview> getData(Integer id){
		return reviewrepository.findById(id);
	}
	
	public Optional<PerformanceReview> insertData(PerformanceReview review){
		if(reviewrepository.existsById(review.getReviewid())){
			return Optional.empty();
		}
		else {
			return Optional.of(reviewrepository.save(review));
		}	
	}
	
	public Optional<PerformanceReview> updateData(PerformanceReview review){
		if(reviewrepository.existsById(review.getReviewid())){
			return Optional.of(reviewrepository.save(review));
		}
		else{
			return Optional.empty();
		}	
	}
	
	public String deleteData(int id){
		if(reviewrepository.existsById(id)) { 
			reviewrepository.deleteById(id);
			return id +"Deleted successfully";
		}else {
			return "Data Does not exit in record";
		}
	}
}