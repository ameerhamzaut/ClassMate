package esi.classmate.grade.dto;

import java.time.LocalDate;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GUserDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String roles;
    private String personal_code;
    private LocalDate DOB;
    private String email;
    private int phone_no;
    @Enumerated(EnumType.STRING)
    private GUserStatus status;

}
