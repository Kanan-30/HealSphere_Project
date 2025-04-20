package org.example.letters_service.service;

import org.example.letters_service.model.Letter;
import org.example.letters_service.repository.LetterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LetterService {
    @Autowired
    private LetterRepository letterRepository;

    public Letter saveLetter(Letter letter) {
        return letterRepository.save(letter);
    }

    public List<Letter> getAllLetters() {
        return letterRepository.findAll();
    }

    public List<Letter> getLettersByCategory(String category) {
        return letterRepository.findByCategory(category);
    }

    // LetterService.java
    public List<String> getAllCategories() {
        return letterRepository.findDistinctCategories();
    }

}
