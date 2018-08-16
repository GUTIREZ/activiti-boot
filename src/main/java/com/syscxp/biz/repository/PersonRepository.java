package com.syscxp.biz.repository;

import com.syscxp.biz.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: sunxuelong.
 * @Cretion Date: 2018-08-13.
 * @Description: .
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByUsername(String username);

}