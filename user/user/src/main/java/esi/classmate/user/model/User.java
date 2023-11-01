package esi.classmate.user.model;

import java.math.BigDecimal;
import java.time.LocalDate;

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
@Table(name = "usertable")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public User(Long id) {
        this.id = id;
    }
}
