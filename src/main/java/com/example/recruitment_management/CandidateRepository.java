package com.example.recruitment_management;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    List<Candidate> findByStatus(String status);

}