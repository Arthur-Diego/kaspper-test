package com.kaspper.teste.repository;

import com.kaspper.teste.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JobRespository extends JpaRepository<Job, Long>, JpaSpecificationExecutor<Job> {

}
