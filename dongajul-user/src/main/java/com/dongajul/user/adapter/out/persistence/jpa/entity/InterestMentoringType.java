package com.dongajul.user.adapter.out.persistence.jpa.entity;

import com.dongajul.common.entity.BaseAuditing;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

/**
 * TODO 엔티티 질의 필요. (엔티티 불분명)
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table
@Comment("관심 멘토링 타입")
public class InterestMentoringType extends BaseAuditing.Create {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(columnDefinition = "UUID")
    @Comment("타입 ID")
    private UUID id;

    @NotNull
    @Column(columnDefinition = "UUID")
    @Comment("멘티 ID")
    private UUID menteeID;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Mentee mentee;

    @NotNull
    @Column(columnDefinition = "VARCHAR(20)")
    private String mentoringTypeCode;
}
