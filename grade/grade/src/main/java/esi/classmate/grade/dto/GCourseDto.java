package esi.classmate.grade.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GCourseDto {
    private Long id;
    private String coursename;
    private String courseinfo;
    private String courselanguage;
    private BigDecimal coursefees;

}
