package com.dongajul.mentoring.adapter.out.persistence.jpa.entity;

import com.dongajul.common.entity.BaseAuditing;
import jakarta.persistence.*;
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
@Comment("멘토링 사전 질문 파일")
public class MentoringQuestionFile extends BaseAuditing.Create {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(columnDefinition = "UUID")
    @Comment("파일 ID")
    private UUID fileId;

    @NotNull
    @Column(columnDefinition = "UUID")
    @Comment("멘토링 신청 ID")
    private UUID mentoringOrderId;

    @NotNull
    @Column(columnDefinition = "VARCHAR(200)")
    @Comment("원본 파일명")
    private String originalFileName;

    @NotNull
    @Column(columnDefinition = "VARCHAR(200)")
    @Comment("파일명")
    private String fileName;

    @Column(columnDefinition = "BIGINT")
    @Comment("파일 사이즈")
    private long fileSize;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private MentoringOrder mentoringOrder;
}
