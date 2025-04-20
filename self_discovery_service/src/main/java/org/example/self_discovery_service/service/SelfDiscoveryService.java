package org.example.self_discovery_service.service;


import org.example.self_discovery_service.entity.Question;
import org.example.self_discovery_service.entity.Story;
import org.example.self_discovery_service.repository.QuestionRepository;
import org.example.self_discovery_service.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelfDiscoveryService {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private StoryRepository storyRepository;

    public List<Question> getQuestionsByStep(String step) {
        return questionRepository.findByStep(step);
    }

    public List<Story> getStoriesByEmotion(String emotion) {
        return storyRepository.findByEmotion(emotion);
    }
}
