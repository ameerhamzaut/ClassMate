package esi.classmate.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import esi.classmate.notification.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
