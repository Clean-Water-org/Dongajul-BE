package com.dongajul.mentoring.adapter.out.persistence.jpa.entity;

import com.dongajul.common.entity.BaseAuditing;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table
@Comment("멘토링 신청 시간")
public class MentoringOrderDate extends BaseAuditing.Create {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(columnDefinition = "UUID")
    @Comment("신청 시간 ID")
    private UUID id;

    @NotNull
    @Column(columnDefinition = "UUID")
    @Comment("멘토링 신청 ID")
    private UUID mentoringOrderId;

    @NotNull
    @Column(columnDefinition = "DATE")
    @Comment("멘토링 시작 시간")
    private LocalDateTime startDate;

    @NotNull
    @Column(columnDefinition = "DATE")
    @Comment("멘토링 종료 시간")
    private LocalDateTime endDate;

    @Column(columnDefinition = "BOOLEAN")
    @Comment("수락 여부")
    private boolean acceptYn;
}