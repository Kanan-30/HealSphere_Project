package org.example.coping_with_services.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "crisis_descriptions")
public class CrisisDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @JsonProperty("page")
    private int page;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;
}
