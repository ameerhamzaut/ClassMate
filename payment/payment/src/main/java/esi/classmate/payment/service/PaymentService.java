package esi.classmate.payment.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import esi.classmate.payment.repository.PaymentRepository;
import esi.classmate.payment.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import esi.classmate.payment.dto.EnrollNotificationDto;
import esi.classmate.payment.dto.PCourseDto;
import esi.classmate.payment.dto.PUserDto;
import esi.classmate.payment.dto.PaymentDto;
import esi.classmate.payment.model.NotificationType;
import esi.classmate.payment.model.Payment;
import esi.classmate.payment.model.PaymentStatus;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor

public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    private final KafkaTemplate<String, EnrollNotificationDto> kafkaTemplate;

    public List<PaymentDto> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        paymentRepository.findAll().forEach(payments::add);
        return payments.stream().map(this::mapToPaymentDto).toList();
    }

    private PaymentDto mapToPaymentDto(Payment payment) {
        return PaymentDto.builder()
                .id(payment.getId())
                .payment_amount(payment.getPayment_amount())
                .payment_date(payment.getPayment_date())
                .status(payment.getStatus())
                .build();
    }

    public Optional<PaymentDto> getPayment(Long id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        return payment.map(this::mapToPaymentDto);
    }

    public void paymentforACourse(PaymentDto paymentDto) {
        log.info("service method");
        // Save the order to in its current state in the Database
        Payment pcourse = paymentRepository.save(mapToPaymentCourse(paymentDto));
        log.info("saved entity: {}", pcourse);
        // send notif
        sendPaymentNotif(pcourse);
    }

    private Payment mapToPaymentCourse(PaymentDto paymentDto) {
        String url = "http://localhost:8083/api/users/{id}";
        PUserDto userDto = webClientBuilder.build()
                .get()
                .uri(url, 252)
                .retrieve()
                .bodyToMono(PUserDto.class)
                .block();
        log.info("mapToPaymentCourse method: {}", userDto);

        BigDecimal userBalance = userDto.getUserbalance();
        PaymentStatus paymentStatus;
        log.info("userBalance method", userBalance);

        if (userBalance.compareTo(paymentDto.getPayment_amount()) < 0) {
            paymentStatus = PaymentStatus.UNPAID;
        } else if (paymentDto.getStatus() == PaymentStatus.FAILED) {
            paymentStatus = PaymentStatus.FAILED;
        } else {
            paymentStatus = PaymentStatus.PAID;
        }
        return Payment.builder()
                .courseId(paymentDto.getCourseId())
                .userId(paymentDto.getUserId())
                .payment_amount(paymentDto.getPayment_amount())
                .payment_date(paymentDto.getPayment_date())
                .status(paymentStatus)
                .build();
        // paymentCourseDto.setStatus(PaymentStatus.PAID);
    }

    public void sendPaymentNotif(Payment pcourse) {

        log.info("sendEnrollmentNotif ");
        String url = "http://localhost:8081/api/courses/{id}";
        PCourseDto courseDto = webClientBuilder.build()
                .get()
                .uri(url, pcourse.getCourseId())
                .retrieve()
                .bodyToMono(PCourseDto.class)
                .block();

        String url2 = "http://localhost:8083/api/users/{id}";
        PUserDto userDto = webClientBuilder.build()
                .get()
                .uri(url2, pcourse.getUserId())
                .retrieve()
                .bodyToMono(PUserDto.class)
                .block();

        log.info("course dto: {}", courseDto);

        EnrollNotificationDto notificationDto = new EnrollNotificationDto();
        notificationDto.setType(NotificationType.PAYMENT);
        notificationDto.setCourseName(courseDto.getCoursename());
        notificationDto.setUserId(pcourse.getUserId());
        notificationDto.setEmail(userDto.getEmail());

        log.info("sending the notif");
        kafkaTemplate.send("enrollmentTopic", notificationDto);
        // kafkaTemplate.send("enrollmentTopic", notificationDto);
        log.info("sending the notif DONE");
    }

    public void addpayment(PaymentDto paymentDto) {
        Payment payment = Payment.builder()
                .id(paymentDto.getId())
                .payment_amount(paymentDto.getPayment_amount())
                .payment_date(paymentDto.getPayment_date())

                .build();

        // Setting the Payment status to ACTIVE
        payment.setStatus(PaymentStatus.PAID);

        // Setting the Payment status to UNPAID
        // payment.setStatus(PaymentStatus.UNPAID);

        // // Setting the Payment status to FAILED
        // payment.setStatus(PaymentStatus.FAILED);

        // Save the Payment to in its current state in the Database
        paymentRepository.save(payment);

        // Setting the PaymentDto status to ACTIVE
        paymentDto.setStatus(PaymentStatus.PAID);

        // Push the PaymentDto to the PaymentCreatedTopic topic
        // kafkaTemplate.send(" PaymentCreatedTopic", paymentDto);

        log.info("Payment added Successfully", payment.getId());
    }

    public void updatePayment(Long id, PaymentDto paymentDto) {
        Payment payment = Payment.builder()
                .id(paymentDto.getId())
                .payment_amount(paymentDto.getPayment_amount())
                .payment_date(paymentDto.getPayment_date())
                .build();
        paymentRepository.save(payment);

    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
        log.info(" Payment {} is deleted", id);
    }
}
