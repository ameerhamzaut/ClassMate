package esi.classmate.notification.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import esi.classmate.notification.repository.NotificationRepository;
import esi.classmate.notification.dto.EnrollNotificationDto;
import esi.classmate.notification.dto.NotificationDto;
import esi.classmate.notification.model.Notification;
import esi.classmate.notification.model.NotificationType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor

public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private JavaMailSender mailSender;

    private final KafkaTemplate<String, NotificationDto> kafkaTemplate;

    public List<NotificationDto> getAllNotifications() {
        List<Notification> notifications = new ArrayList<>();
        notificationRepository.findAll().forEach(notifications::add);
        return notifications.stream().map(this::mapToNotificationDto).toList();
    }

    private NotificationDto mapToNotificationDto(Notification notification) {
        return NotificationDto.builder()
                .id(notification.getId())
                .content(notification.getContent())
                .date(notification.getDate())
                .type(notification.getType())
                .build();
    }

    public Optional<NotificationDto> getNotification(Long id) {
        Optional<Notification> notification = notificationRepository.findById(id);
        return notification.map(this::mapToNotificationDto);
    }

    public void addnotification(NotificationDto notificationDto) {
        Notification notification = Notification.builder()
                .id(notificationDto.getId())
                .content(notificationDto.getContent())
                .date(notificationDto.getDate())
                .build();

        // Setting the notificatio status to ACTIVE
        // notification.setStatus(NotificationType.ACTIVE);

        // Save the notificatio to in its current state in the Database
        notificationRepository.save(notification);

        // Setting the NotificatioDto status to ACTIVE
        // notificationDto.setStatus(NotificationType.ACTIVE);

        // Push the notificatioDto to the notificationCreatedTopic topic
        kafkaTemplate.send("notificationCreatedTopic", notificationDto);

        log.info("Notification added Successfully", notification.getId());
    }

    public void updateNotification(Long id, NotificationDto notificationDto) {
        Notification notification = Notification.builder()
                .id(notificationDto.getId())
                .content(notificationDto.getContent())
                .date(notificationDto.getDate())
                .build();
        notificationRepository.save(notification);
        log.info("Notification {} is updated", notification.getId());
    }

    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
        log.info("Notification {} is deleted", id);
    }

    @KafkaListener(topics = "enrollmentTopic", groupId = "enrollmentNotifyGroup")
    public void sendNotification(EnrollNotificationDto notificationDto) {

        Notification notification = new Notification();
        notification.setDate(LocalDate.now());

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(notificationDto.getEmail());

        if (notificationDto.getType().equals(NotificationType.ENROLL)) {
            notification.setType(NotificationType.ENROLL);
            notification.setContent("Enrolled successfully");
            message.setSubject("Enrollment");
            message.setText("Enrolled successfully");
        } else if (notificationDto.getType().equals(NotificationType.GRADE)) {
            notification.setType(NotificationType.GRADE);
            notification.setContent("Your Grades ...");
            message.setSubject("Grades");
            message.setText("You recieved your marks");

        } else {
            notification.setType(NotificationType.PAYMENT);
            notification.setContent("Payment successfull");
            message.setSubject("Payment");
            message.setText("Payment successfull");
        }
        notificationRepository.save(notification);
        mailSender.send(message);
    }

}
