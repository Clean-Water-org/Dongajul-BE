package com.dongajul.mentoring.adapter.out.persistence.jpa.entity;

import com.dongajul.common.entity.BaseAuditing;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.UuidGenerator;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table
@Comment("멘토링 신청")
public class MentoringOrder extends BaseAuditing.CreateAndUpdate {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(columnDefinition = "UUID")
    @Comment("신청 ID")
    private UUID id;

    @Column(columnDefinition = "VARCHAR(200)")
    @Comment("사전 질문")
    private String question;

    @NotNull
    @Column(columnDefinition = "VARCHAR(20)")
    @Comment("멘토링 진행 상태")
    private String mentoringStatusCode;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "UUID")
    @Comment("멘티 ID")
    private Mentee mentee;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "UUID")
    @Comment("멘토링 클래스 ID")
    private MentoringClass mentoringClass;

    @ToString.Exclude
    @OneToMany(mappedBy = "mentoringOrder", fetch = FetchType.LAZY)
    private Set<MentoringReview> mentoringReviewSet = new LinkedHashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "mentoringOrder", fetch = FetchType.LAZY)
    private Set<MentoringOrderDate> mentoringOrderDateSet = new LinkedHashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "mentoringOrder", fetch = FetchType.LAZY)
    private Set<MentoringQuestionFile> mentoringQuestionFileSet = new LinkedHashSet<>();
}
