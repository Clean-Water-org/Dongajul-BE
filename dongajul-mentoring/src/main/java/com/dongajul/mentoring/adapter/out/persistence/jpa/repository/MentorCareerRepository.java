package com.dongajul.mentoring.adapter.out.persistence.jpa.repository;

import com.dongajul.mentoring.adapter.out.persistence.jpa.entity.MentorCareer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MentorCareerRepository extends JpaRepository<MentorCareer, UUID> {
}
