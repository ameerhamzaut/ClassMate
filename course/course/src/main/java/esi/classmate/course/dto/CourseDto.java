package esi.classmate.course.dto;

import java.math.BigDecimal;

import esi.classmate.course.model.CourseStatus;
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

public class CourseDto {
    @Id
    private Long id;
    private Long userId;
    private String coursename;
    private String courseinfo;
    private String courselanguage;
    private BigDecimal coursefees;

    @Enumerated(EnumType.STRING)
    private CourseStatus status;

    public CourseDto(Long id) {
        this.id = id;
    }
}
