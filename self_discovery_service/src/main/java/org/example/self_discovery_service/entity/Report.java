package org.example.self_discovery_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question_id", nullable = false)
    private Long questionId;

    @Column(name = "selected_option", nullable = false)
    private String selectedOption;

    @Column(name = "report_text", nullable = false, length = 1000)
    private String reportText;

    // Constructors
    public Report() {}

    public Report(Long questionId, String selectedOption, String reportText) {
        this.questionId = questionId;
        this.selectedOption = selectedOption;
        this.reportText = reportText;
    }


}
