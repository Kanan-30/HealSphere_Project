package org.example.coping_with_services.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_selected_steps")
public class UserSelectedStep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("step_id")
    @Column(name = "step_id")
    private Long stepId; // Matches crisis_response_steps ID

    @JsonProperty("selected_option")
    @Column(name = "selected_option")
    private String selectedOption;
}