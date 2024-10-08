package com.dongajul.mentoring.adapter.out.persistence.jpa.repository;

import com.dongajul.mentoring.adapter.out.persistence.jpa.entity.InterestMentor;
import com.dongajul.mentoring.adapter.out.persistence.jpa.entity.id.InterestMentorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestMentorRepository extends JpaRepository<InterestMentor, InterestMentorId> {
}
