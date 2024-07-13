package com.dongajul.mentoring.adapter.out.persistence.jpa.repository;

import com.dongajul.mentoring.adapter.out.persistence.jpa.entity.MentoringClassDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MentoringClassDateRepository extends JpaRepository<MentoringClassDate, UUID> {
}
