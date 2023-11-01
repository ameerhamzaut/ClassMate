package esi.classmate.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import esi.classmate.payment.model.Payment;

@Repository
public interface UserRepository extends JpaRepository<Payment, Long> {

}
