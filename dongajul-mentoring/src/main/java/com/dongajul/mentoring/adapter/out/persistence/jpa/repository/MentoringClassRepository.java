package com.dongajul.mentoring.adapter.out.persistence.jpa.repository;

import com.dongajul.mentoring.adapter.out.persistence.jpa.entity.MentoringClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MentoringClassRepository extends JpaRepository<MentoringClass, UUID> {
}
