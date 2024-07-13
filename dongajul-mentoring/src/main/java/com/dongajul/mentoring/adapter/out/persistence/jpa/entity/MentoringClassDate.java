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

import java.util.UUID;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table
@Comment("멘토링 진행 가능 시간")
public class MentoringClassDate extends BaseAuditing.CreateAndUpdate {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(columnDefinition = "UUID")
    @Comment("멘토링 진행 가능 시간 ID")
    private UUID id;

    @NotNull
    @Column(columnDefinition = "UUID")
    @Comment("클래스 ID")
    private UUID classId;

    @NotNull
    @Column(columnDefinition = "VARCHAR(5)")
    @Comment("시작 시간")
    private String startTime;;

    @NotNull
    @Column(columnDefinition = "VARCHAR(5)")
    @Comment("종료 시간")
    private String endTime;

    @NotNull
    @Column(columnDefinition = "VARCHAR(20)")
    @Comment("요일")
    private String dayOfWeekCode;
}