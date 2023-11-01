package esi.classmate.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import esi.classmate.payment.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
