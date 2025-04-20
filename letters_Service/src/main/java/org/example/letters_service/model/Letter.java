package org.example.letters_service.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "letters")
public class Letter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "heading", nullable = false, length = 255)
    private String heading;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;
}
