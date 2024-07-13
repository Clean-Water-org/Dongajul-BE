package com.dongajul.mentoring.adapter.out.persistence.jpa.repository;

import com.dongajul.mentoring.adapter.out.persistence.jpa.entity.MentoringOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MentoringOrderRepository extends JpaRepository<MentoringOrder, UUID> {
}
