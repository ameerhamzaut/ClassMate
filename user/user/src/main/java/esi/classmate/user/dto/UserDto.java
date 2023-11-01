package esi.classmate.user.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import esi.classmate.user.model.UserStatus;
import esi.classmate.user.model.UserType;
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
public class UserDto {
    private Long id;
    private String name;
    private String lastname;
    @Enumerated(EnumType.STRING)
    private UserType roles;
    private int personal_code;
    private LocalDate DOB;
    private String email;
    private int phone_no;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserStatus status;
    private BigDecimal userbalance;

    public UserDto(Long id) {
        this.id = id;
    }

}
