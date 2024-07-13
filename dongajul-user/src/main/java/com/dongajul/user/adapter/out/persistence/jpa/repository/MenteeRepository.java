package com.dongajul.user.adapter.out.persistence.jpa.repository;

import com.dongajul.user.adapter.out.persistence.jpa.entity.Mentee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MenteeRepository extends JpaRepository<Mentee, UUID> {
}
