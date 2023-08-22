package com.main.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.hrms.model.PerformanceReview;

public interface PerformanceReviewRepository  extends JpaRepository<PerformanceReview, Integer > {

}
