package com.dongajul.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

public interface BaseAuditing {

    @Getter
    @Setter
    @ToString
    @MappedSuperclass
    @EntityListeners(AuditingEntityListener.class)
    class CreateAndUpdate {
        @CreatedBy
        @NotNull
        @Column(columnDefinition = "UUID", updatable = false)
        @Comment("생성자")
        private UUID createdUser;

        @CreatedDate
        @NotNull
        @Column(columnDefinition = "DATE", updatable = false)
        @Comment("생성일")
        private LocalDateTime createdDate;

        @LastModifiedBy
        @Column(columnDefinition = "UUID")
        @Comment("수정자")
        private UUID updatedUser;

        @LastModifiedDate
        @Column(columnDefinition = "DATE")
        @Comment("수정일")
        private LocalDateTime updatedDate;
    }

    @Getter
    @Setter
    @ToString
    @MappedSuperclass
    @EntityListeners(AuditingEntityListener.class)
    class Create {
        @CreatedBy
        @NotNull
        @Column(columnDefinition = "UUID", updatable = false)
        @Comment("생성자")
        private UUID createdUser;

        @CreatedDate
        @NotNull
        @Column(columnDefinition = "DATE", updatable = false)
        @Comment("생성일")
        private LocalDateTime createdDate;
    }
}