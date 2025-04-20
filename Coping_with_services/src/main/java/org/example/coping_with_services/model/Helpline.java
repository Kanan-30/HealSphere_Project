package org.example.coping_with_services.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "helplines")
public class Helpline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @JsonProperty("region")
    private String region;

    @JsonProperty("name")
    private String name;

    @JsonProperty("timings")
    private String timings;

    @JsonProperty("number")
    private String number;
}
