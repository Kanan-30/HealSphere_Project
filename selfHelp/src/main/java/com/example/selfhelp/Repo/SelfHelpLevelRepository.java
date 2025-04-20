package com.example.selfhelp.Repo;

import com.example.selfhelp.Entity.SelfHelpLevel;
import com.example.selfhelp.SelfHelpApplication;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface SelfHelpLevelRepository extends JpaRepository<SelfHelpLevel, Long> {
    List<SelfHelpLevel> findByToolkitId(Long toolkitId);
}

