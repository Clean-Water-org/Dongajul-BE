package com.dongajul.billing.adapter.out.persistence.jpa.entity;

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
@Comment("정산 계좌")
public class SettlementAccount {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(columnDefinition = "UUID")
    @Comment("정산 계좌 ID")
    private UUID id;

    @NotNull
    @Column(columnDefinition = "UUID")
    @Comment("멘토 ID")
    private UUID mentorId;

    @NotNull
    @Column(columnDefinition = "VARCHAR(255)")
    @Comment("계약자명")
    private String contractorName;

    @NotNull
    @Column(columnDefinition = "VARCHAR(255)")
    @Comment("정산자명")
    private String settlerName;

    @NotNull
    @Column(columnDefinition = "NUMERIC(2,0)") // TODO ?? 데이터 타입 재확인 필요
    @Comment("주민등록번호") // TODO ?? 주민등록번호 아닌 것 같은데?
    private String identificationName;

    @NotNull
    @Column(columnDefinition = "VARCHAR(255)") // TODO ?? 데이터 타입 재확인 필요
    @Comment("은행")
    private String bankCode;

    @NotNull
    @Column(columnDefinition = "NUMERIC(2,0)") // TODO ?? 데이터 타입 재확인 필요
    @Comment("계좌번호")
    private String accountNumber;

    @ToString.Exclude
    @OneToMany(mappedBy = "settlementAccount", fetch = FetchType.LAZY)
    private Set<SettlementHistory> settlementHistorySet = new LinkedHashSet<>();
}
