package com.syscxp.biz.entity.activiti;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-08-28.
 * @Description: .
 */
@Entity
public class VacationApplication {

    @Id
    @GeneratedValue
    private Long id;

    private String applicantId;

    private String vacationMotivation;

    private Timestamp startDate;

    private Long numberOfDays;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public String getVacationMotivation() {
        return vacationMotivation;
    }

    public void setVacationMotivation(String vacationMotivation) {
        this.vacationMotivation = vacationMotivation;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Long getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(Long numberOfDays) {
        this.numberOfDays = numberOfDays;
    }
}
