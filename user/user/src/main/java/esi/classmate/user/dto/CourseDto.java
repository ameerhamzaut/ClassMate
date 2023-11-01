package esi.classmate.user.dto;

import java.math.BigDecimal;
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
    private Long id;
    private String coursename;
    private String courseinfo;
    private String courselanguage;
    private BigDecimal coursefees;

}
