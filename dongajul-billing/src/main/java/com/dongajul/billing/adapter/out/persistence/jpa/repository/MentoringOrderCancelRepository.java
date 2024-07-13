package com.dongajul.billing.adapter.out.persistence.jpa.repository;

import com.dongajul.billing.adapter.out.persistence.jpa.entity.MentoringOrderCancel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MentoringOrderCancelRepository extends JpaRepository<MentoringOrderCancel, UUID> {
}
