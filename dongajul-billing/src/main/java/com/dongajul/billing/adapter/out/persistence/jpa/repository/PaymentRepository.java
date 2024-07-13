package com.dongajul.billing.adapter.out.persistence.jpa.repository;

import com.dongajul.billing.adapter.out.persistence.jpa.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
