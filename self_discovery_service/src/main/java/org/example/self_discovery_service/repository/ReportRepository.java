package org.example.self_discovery_service.repository;



import org.example.self_discovery_service.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    Optional<Report> findByQuestionIdAndSelectedOption(Long questionId, String selectedOption);
}
