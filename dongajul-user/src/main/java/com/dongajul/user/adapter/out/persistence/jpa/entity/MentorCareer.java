package com.dongajul.user.adapter.out.persistence.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
@Comment("멘토 경력")
public class MentorCareer {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(columnDefinition = "UUID")
    @Comment("경력 ID")
    private UUID id;

    @NotNull
    @Column(columnDefinition = "UUID")
    @Comment("멘토 ID")
    private UUID mentorId;

    @NotNull
    @Column(columnDefinition = "VARCHAR(100)")
    @Comment("인증 기업명")
    private String companyName;

    @Column(columnDefinition = "BOOLEAN")
    @Comment("대표 경력 여부")
    private boolean isRepresentation;

    @NotNull
    @Column(columnDefinition = "Date")
    @Comment("입사일")
    private LocalDate startWorkDate;

    @Column(columnDefinition = "Date")
    @Comment("퇴사일")
    private LocalDate endWorkDate;
}
