package com.example.selfhelp.Repo;
import com.example.selfhelp.Entity.SelfHelpContent;
import com.example.selfhelp.SelfHelpApplication;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface SelfHelpContentRepository extends JpaRepository<SelfHelpContent, Long> {
    List<SelfHelpContent> findByLevelId(Long levelId);
}
