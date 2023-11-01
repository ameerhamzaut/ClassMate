package esi.classmate.notification.dto;

import jakarta.persistence.Entity;

import java.time.LocalDate;

import esi.classmate.notification.model.NotificationType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class NotificationDto {
    @Id
    private Long id;
    private String content;
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    public NotificationDto(Long id) {
        this.id = id;
    }
}
