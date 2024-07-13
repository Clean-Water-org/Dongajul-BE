package com.dongajul.mentoring.adapter.out.persistence.jpa.repository;

import com.dongajul.mentoring.adapter.out.persistence.jpa.entity.MentoringReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MentoringReviewRepository extends JpaRepository<MentoringReview, UUID> {
}
