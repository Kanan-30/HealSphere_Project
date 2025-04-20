package com.example.selfhelp.Repo;

import com.example.selfhelp.Entity.SelfHelpAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface SelfHelpAssessmentRepository extends JpaRepository<SelfHelpAssessment, Long> {
    List<SelfHelpAssessment> findByLevelId(Long levelId);
}
