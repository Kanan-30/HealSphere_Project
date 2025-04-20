package com.example.selfhelp.Services;
import com.example.selfhelp.Entity.*;
import com.example.selfhelp.SelfHelpApplication;

import java.util.*;

public interface SelfHelpService {
    List<SelfHelpToolkit> getAllToolkits();
    List<SelfHelpLevel> getLevelsByToolkit(Long toolkitId);
    List<SelfHelpContent> getContentByLevel(Long levelId);
    List<SelfHelpAssessment> getAssessmentsByLevel(Long levelId);
    void updateUserProgress(SelfHelpUserProgress progress);
    List<SelfHelpUserProgress> getProgressByUser(Long userId);

}
