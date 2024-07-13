package com.dongajul.user.adapter.out.persistence.jpa.repository;

import com.dongajul.user.adapter.out.persistence.jpa.entity.InterestMentoringType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InterestMentoringTypeRepository extends JpaRepository<InterestMentoringType, UUID> {
}
