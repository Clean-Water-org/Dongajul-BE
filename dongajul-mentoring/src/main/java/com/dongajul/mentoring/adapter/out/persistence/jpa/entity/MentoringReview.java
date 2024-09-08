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

    @NotNull
    @Column(columnDefinition = "UUID")
    @Comment("작성자 ID")
    private UUID menteeId;

    @NotNull
    @Column(columnDefinition = "VARCHAR(200)")
    @Comment("내용")
    private String content;

    @NotNull
    @Column(columnDefinition = "NUMERIC(1, 0)")
    @Comment("평점")
    private int rating;

    @Column(columnDefinition = "BOOLEAN")
    @Comment("삭제 여부")
    private boolean isDeleted;

    @ToString.Exclude
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private Set<MentoringReview> mentoringReviewSet = new LinkedHashSet<>();

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @Comment("부모 리뷰 ID")
    private MentoringReview parent;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @Comment("멘토링 신청 ID")
    private MentoringOrder mentoringOrder;
}
