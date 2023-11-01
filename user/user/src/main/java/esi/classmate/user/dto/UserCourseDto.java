package esi.classmate.user.dto;

import java.time.LocalDate;

import esi.classmate.user.model.EnrollmentStatus;
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
public class UserCourseDto {

    private Long id;
    private Long userId;
    private Long courseId;
    @Enumerated(EnumType.STRING)
    private EnrollmentStatus enrollment_status;
    private LocalDate enrollment_date;

    public UserCourseDto(Long id) {
        this.id = id;
    }

}
