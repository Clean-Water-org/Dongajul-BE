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
@Comment("멘토링 리뷰")
public class MentoringReview extends BaseAuditing.CreateAndUpdate {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(columnDefinition = "UUID")
    @Comment("리뷰 ID")
    private UUID id;

    @Column(columnDefinition = "UUID")
    @Comment("부모 리뷰 ID")
    private UUID parentId;

    @NotNull
    @Column(columnDefinition = "UUID")
    @Comment("멘토링 신청 ID")
    private UUID orderId;

    @NotNull
    @Column(columnDefinition = "UUID")
    @Comment("작성자 ID")
    private UUID userId;

    @NotNull
    @Column(columnDefinition = "VARCHAR(200)")
    @Comment("내용")
    private String content;

    @NotNull
    @Column(columnDefinition = "SMALLINT") // TODO NUMERIC(2, 1)은 어떠한가..
    @Comment("평점")
    private int rating;

    @Column(columnDefinition = "BOOLEAN")
    @Comment("삭제 여부")
    private boolean isDeleted;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    @ToString.Exclude
    @OneToMany(mappedBy = "mentoringReview", fetch = FetchType.LAZY)
    private Set<MentoringReview> mentoringReviewSet = new LinkedHashSet<>();

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private MentoringReview mentoringReview;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private MentoringOrder mentoringOrder;
}
