
package esi.classmate.payment.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import esi.classmate.payment.model.PaymentStatus;
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

public class PaymentDto {
    @Id
    private Long id;
    private Long userId;
    private Long courseId;
    private BigDecimal payment_amount;
    private LocalDate payment_date;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    public PaymentDto(Long id) {
        this.id = id;
    }
}
