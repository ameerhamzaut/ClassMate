package esi.classmate.grade.dto;

import esi.classmate.grade.model.GradeStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class GradeDto {

    @Id
    private Long id;
    private Long userId;
    private Long courseId;
    private String student_grade;
    private String transcript;
    private String report;
    private String remarks;

    @Enumerated(EnumType.STRING)
    private GradeStatus status;

    public GradeDto(Long id) {
        this.id = id;
    }

}
