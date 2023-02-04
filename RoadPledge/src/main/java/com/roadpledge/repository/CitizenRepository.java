package com.roadpledge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.roadpledge.entity.Citizen;

@Repository 
public interface CitizenRepository extends JpaRepository<Citizen, Integer> {

}
