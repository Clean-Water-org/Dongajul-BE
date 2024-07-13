package com.dongajul.mentoring.adapter.out.persistence.jpa.repository;

import com.dongajul.mentoring.adapter.out.persistence.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
