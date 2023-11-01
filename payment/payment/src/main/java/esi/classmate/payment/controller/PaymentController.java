package esi.classmate.payment.controller;

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

import esi.classmate.payment.dto.PaymentDto;
import esi.classmate.payment.service.PaymentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/payments")
    public List<PaymentDto> getPayments() {
        return paymentService.getAllPayments();
    }

    @GetMapping("/payments/{id}")
    public Optional<PaymentDto> getPayment(@PathVariable Long id) {
        return paymentService.getPayment(id);
    }

    @PostMapping(value = "/payments")
    public ResponseEntity<String> addpayment(@RequestBody PaymentDto paymentDto) {
        paymentService.addpayment(paymentDto);
        return ResponseEntity.ok("payment added");

    }

    @PostMapping(value = "/enroll/payment")
    public ResponseEntity<String> paymentforACourse(@RequestBody PaymentDto paymentDto) {
        log.info("enrolltoACourse: {}", paymentDto);
        paymentService.paymentforACourse(paymentDto);
        return ResponseEntity.ok("Payment done Successfully");

    }

    @PutMapping("/payments/{id}")
    public void updatePayment(@RequestBody PaymentDto paymentDto, @PathVariable Long id) {
        paymentService.updatePayment(id, paymentDto);
    }

    @DeleteMapping("/payments/{id}")
    public void deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
    }
}
