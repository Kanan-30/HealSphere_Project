package org.example.self_discovery_service.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String step;

    @Column(nullable = false)
    private String questionText;

    @Column(nullable = false)
    private String questionType;

    public Question() {}
    public Question(String questionText, String questionType, String step) {
        this.questionText = questionText;
        this.questionType = questionType;
        this.step = step;

    }
    public Long getId() { return id; }

    public String getQuestionText() { return questionText; }

    public String getQuestionType() { return questionType; }

    public String getStep() { return step; }
}
