package com.example.selfhelp.Entity;
import com.example.selfhelp.SelfHelpApplication;

import jakarta.persistence.*;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "self_help_levels")
public class SelfHelpLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_unlocked")
    private boolean isUnlocked;

    @Column(name = "level_name")
    private String levelName;

    @Column(name = "level_order")
    private Integer levelOrder;

    @Column(name = "toolkit_id")
    private Long toolkitId;
}
