package com.example.selfhelp.Repo;
import com.example.selfhelp.Entity.SelfHelpUserProgress;
import com.example.selfhelp.SelfHelpApplication;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface SelfHelpUserProgressRepository extends JpaRepository<SelfHelpUserProgress, Long> {
    List<SelfHelpUserProgress> findByUserId(Long userId);
}

