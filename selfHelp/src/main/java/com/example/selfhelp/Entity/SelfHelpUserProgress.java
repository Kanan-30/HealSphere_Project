package com.example.selfhelp.Entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "self_help_user_progress")
public class SelfHelpUserProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private Long toolkitId;
    private Long levelId;
    private boolean isCompleted;
    private Integer score;
}
