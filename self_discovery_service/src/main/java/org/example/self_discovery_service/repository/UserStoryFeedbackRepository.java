package org.example.self_discovery_service.repository;

import org.example.self_discovery_service.entity.UserStoryFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStoryFeedbackRepository extends JpaRepository<UserStoryFeedback, Long> {
}
