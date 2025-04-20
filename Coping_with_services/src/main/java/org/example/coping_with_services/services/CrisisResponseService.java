package org.example.coping_with_services.services;

import org.example.coping_with_services.model.CrisisResponseStep;
import org.example.coping_with_services.model.UserCrisisPlan;
import org.example.coping_with_services.repositories.CrisisResponseStepRepository;
import org.example.coping_with_services.repositories.UserCrisisPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrisisResponseService {
    @Autowired
    private UserCrisisPlanRepository userCrisisPlanRepository;

    @Autowired
    private CrisisResponseStepRepository crisisResponseStepRepository; // ✅ Add this

    public void saveUserCrisisPlan(UserCrisisPlan plan) {
        userCrisisPlanRepository.save(plan);
    }

    // ✅ Add this method to fetch all crisis response steps
    public List<CrisisResponseStep> getCrisisSteps() {
        return crisisResponseStepRepository.findAll();
    }
}
