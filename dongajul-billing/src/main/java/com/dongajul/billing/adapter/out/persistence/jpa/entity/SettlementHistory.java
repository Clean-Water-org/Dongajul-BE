package com.dongajul.billing.adapter.out.persistence.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table
@Comment("")
public class SettlementHistory {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(columnDefinition = "UUID")
    @Comment("정산 내역 ID")
    private UUID id;

    @NotNull
    @Column(columnDefinition = "UUID")
    @Comment("정산 계좌 ID")
    private UUID accountId;

    @NotNull
    @Column(columnDefinition = "UUID")
    @Comment("멘토링 신청 ID")
    private UUID mentoringOrderId;

    @NotNull
    @Column(columnDefinition = "DATE")
    @Comment("결제 날짜")
    private LocalDateTime paymentDate;

    @NotNull
    @Column(columnDefinition = "BIGINT")
    @Comment("정산 금액")
    private BigInteger settlementPrice;

    @NotNull
    @Column(columnDefinition = "VARCHAR(20)")
    @Comment("상태")
    private String settlementStateCode;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private SettlementAccount settlementAccount;
}
