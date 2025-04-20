package org.example.self_discovery_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "options")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Column(nullable = false)
    private String optionText;

    // Constructors, Getters, and Setters
    public Option() {}

    public Option(Question question, String optionText) {
        this.question = question;
        this.optionText = optionText;
    }

}
