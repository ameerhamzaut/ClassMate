package esi.classmate.notification.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import esi.classmate.notification.dto.NotificationDto;
import esi.classmate.notification.service.NotificationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")

public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notifications")
    public List<NotificationDto> getNotifications() {
        return notificationService.getAllNotifications();
    }

    @GetMapping("/notifications/{id}")
    public Optional<NotificationDto> getNotification(@PathVariable Long id) {
        return notificationService.getNotification(id);
    }

    @PostMapping(value = "/notifications")
    public ResponseEntity<String> addnotification(@RequestBody NotificationDto notificationDto) {
        notificationService.addnotification(notificationDto);
        return ResponseEntity.ok("Added Successfully");

    }

    @PutMapping("/notifications/{id}")
    public void updateNotification(@RequestBody NotificationDto notificationDto, @PathVariable Long id) {
        notificationService.updateNotification(id, notificationDto);
    }

    @DeleteMapping("/notifications/{id}")
    public void deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
    }

}
