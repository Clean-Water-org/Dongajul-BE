package com.dongajul.mentoring.adapter.out.persistence.jpa.repository;

import com.dongajul.mentoring.adapter.out.persistence.jpa.entity.MentoringQuestionFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MentoringQuestionFileRepository extends JpaRepository<MentoringQuestionFile, UUID> {
}
