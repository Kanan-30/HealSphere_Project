package com.example.selfhelp.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "self_help_assessments")
public class SelfHelpAssessment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @Column(name = "options")
    private String optionsJson;  // raw JSON stored in DB, hidden in response

    private String question;
    private Long levelId;

    @Transient
    private List<String> options;

    @PostLoad
    private void loadOptions() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            this.options = mapper.readValue(optionsJson, new TypeReference<List<String>>() {});
        } catch (Exception e) {
            this.options = new ArrayList<>();
        }
    }

    @PrePersist
    @PreUpdate
    private void saveOptions() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            this.optionsJson = mapper.writeValueAsString(this.options);
        } catch (Exception e) {
            this.optionsJson = "[]";
        }
    }
}

