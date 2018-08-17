package com.syscxp.biz.repository;

import com.syscxp.biz.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

}