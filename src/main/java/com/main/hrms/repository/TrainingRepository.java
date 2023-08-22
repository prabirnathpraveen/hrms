package com.main.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.hrms.model.Training;

public interface TrainingRepository extends JpaRepository<Training, Integer > {

}
