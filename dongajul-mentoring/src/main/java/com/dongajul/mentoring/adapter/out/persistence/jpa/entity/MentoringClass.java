package com.dongajul.mentoring.adapter.out.persistence.jpa.entity;


import com.dongajul.common.entity.BaseAuditing;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigInteger;
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
@Comment("멘토링 클래스")
public class MentoringClass extends BaseAuditing.CreateAndUpdate {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(columnDefinition = "UUID")
    @Comment("클래스 ID")
    private UUID id;

    @Column(columnDefinition = "VARCHAR(20)")
    @Comment("멘토링 분야")
    private String mentoringTypeCode;

    @NotNull
    @Column(columnDefinition = "BIGINT")
    @Comment("가격")
    private BigInteger classPrice;

    @Column(columnDefinition = "BOOLEAN")
    @Comment("공휴일 제외")
    private boolean holidayYn;

    @Column(columnDefinition = "BOOLEAN")
    @Comment("사전 질문 필수 여부")
    private boolean questionYn;

    @Column(columnDefinition = "BOOLEAN")
    @Comment("삭제 여부")
    private boolean isDeleted;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @Comment("멘토 ID")
    private Mentor mentor;

    @ToString.Exclude
    @OneToMany(mappedBy = "mentoringClass", fetch = FetchType.LAZY)
    private Set<MentoringClassDate> mentoringClassDateSet = new LinkedHashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "mentoringClass", fetch = FetchType.LAZY)
    private Set<MentoringOrder> mentoringOrderSet = new LinkedHashSet<>();
}
