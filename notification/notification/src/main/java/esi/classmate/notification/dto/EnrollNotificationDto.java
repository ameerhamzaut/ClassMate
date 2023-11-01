package esi.classmate.notification.dto;

import esi.classmate.notification.model.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

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
