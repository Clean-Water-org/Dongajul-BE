package com.dongajul.user.adapter.out.persistence.jpa.repository;

import com.dongajul.user.adapter.out.persistence.jpa.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByEmail(String email);
}
