package org.example.coping_with_services.services;

import org.example.coping_with_services.model.Helpline;
import org.example.coping_with_services.repositories.HelplineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class HelplineService {
    @Autowired
    private HelplineRepository helplineRepository;

    public List<Helpline> getAllHelplines() {
        List<Helpline> helplines = helplineRepository.findAll();
        System.out.println("Fetched Helplines: " + helplines);
        return helplines;
    }
}
