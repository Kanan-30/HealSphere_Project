package org.example.coping_with_services.services;

import org.example.coping_with_services.model.CrisisDescription;
import org.example.coping_with_services.repositories.CrisisDescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrisisDescriptionService {
    @Autowired
    private CrisisDescriptionRepository crisisDescriptionRepository;
    public List<CrisisDescription> getCrisisDescriptions() { return crisisDescriptionRepository.findAll(); }
}