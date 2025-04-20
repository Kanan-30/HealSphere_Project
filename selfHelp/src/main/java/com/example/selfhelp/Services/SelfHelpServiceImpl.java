package com.example.selfhelp.Services;
import com.example.selfhelp.Entity.*;
import com.example.selfhelp.Repo.*;
import com.example.selfhelp.SelfHelpApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SelfHelpServiceImpl implements SelfHelpService {

    @Autowired
    private SelfHelpToolkitRepository toolkitRepo;
    @Autowired
    private SelfHelpLevelRepository levelRepo;
    @Autowired
    private SelfHelpContentRepository contentRepo;
    @Autowired
    private SelfHelpAssessmentRepository assessmentRepo;
    @Autowired
    private SelfHelpUserProgressRepository progressRepo;

    @Override
    public List<SelfHelpToolkit> getAllToolkits() {
        return toolkitRepo.findAll();
    }

    @Override
    public List<SelfHelpLevel> getLevelsByToolkit(Long toolkitId) {
        return levelRepo.findByToolkitId(toolkitId);
    }

    @Override
    public List<SelfHelpContent> getContentByLevel(Long levelId) {
        return contentRepo.findByLevelId(levelId);
    }

    @Override
    public List<SelfHelpAssessment> getAssessmentsByLevel(Long levelId) {
        return assessmentRepo.findByLevelId(levelId);
    }

    @Override
    public void updateUserProgress(SelfHelpUserProgress progress) {
        progressRepo.save(progress);
    }

    @Override
    public List<SelfHelpUserProgress> getProgressByUser(Long userId) {
        return progressRepo.findByUserId(userId);
    }


}
