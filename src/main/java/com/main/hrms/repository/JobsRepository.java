package com.main.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.hrms.model.Jobs;

public interface JobsRepository extends JpaRepository<Jobs, Integer > {

}
