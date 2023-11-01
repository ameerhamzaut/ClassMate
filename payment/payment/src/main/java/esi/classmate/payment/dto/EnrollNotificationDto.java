package esi.classmate.payment.dto;

import esi.classmate.payment.model.NotificationType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EnrollNotificationDto {

    private Long userId;
    private String email;
    private String courseName;
    @Enumerated(EnumType.STRING)
    private NotificationType type;

}
