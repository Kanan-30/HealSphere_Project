package org.example.self_discovery_service.entity;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "stories")
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String emotion;

    @Column(nullable = false, length = 2000)
    private String storyText;

    // Constructors, Getters, and Setters
    public Story() {}

    public Story(String emotion, String storyText) {
        this.emotion = emotion;
        this.storyText = storyText;
    }

}
