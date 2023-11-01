package esi.classmate.grade.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "gradetable")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private Long courseId;
    private String student_grade;
    private String transcript;
    private String report;
    private String remarks;

    @Enumerated(EnumType.STRING)
    private GradeStatus status;

    public Grade(Long id) {
        this.id = id;
    }

}
