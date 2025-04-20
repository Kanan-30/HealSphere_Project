package org.example.self_discovery_service.repository;

import org.example.self_discovery_service.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByStep(String step);
}
