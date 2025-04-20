package com.example.selfhelp.Entity;
import com.example.selfhelp.SelfHelpApplication;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "self_help_content")
public class SelfHelpContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "level_id")
    private Long levelId;
}
