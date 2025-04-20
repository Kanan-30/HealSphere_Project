package org.example.self_discovery_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_responses")
public class UserResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Foreign key linking to users table

    @Column(nullable = false)
    private Long questionId;

    @Column(nullable = false)
    private String selectedOption;
}
