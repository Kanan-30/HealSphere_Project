package org.example.coping_with_services.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Setter;
import lombok.Getter;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "crisis_response_steps")
public class CrisisResponseStep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @JsonProperty("step")
    private int step;

    @JsonProperty("question")
    private String question;

    @ElementCollection
    @JsonProperty("options")
    private List<String> options;

    @JsonProperty("pdf_link")
    private String pdfLink;
}
