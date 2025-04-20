package org.example.self_discovery_service.repository;


import org.example.self_discovery_service.entity.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StoryRepository extends JpaRepository<Story, Long> {
    List<Story> findByEmotion(String emotion);
}
