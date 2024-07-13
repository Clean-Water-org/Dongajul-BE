package com.dongajul.billing.adapter.out.persistence.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Comment("결제")
public class Payment {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(columnDefinition = "UUID")
    @Comment("결제 ID")
    private UUID id;

    @NotNull
    @Column(columnDefinition = "UUID")
    @Comment("멘토링 신청 ID")
    private UUID mentoringOrderId;

    @NotNull
    @Column(columnDefinition = "VARCHAR(50)")
    @Comment("주문번호")
    private String merchantUid;

    @NotNull
    @Column(columnDefinition = "BIGINT")
    @Comment("총 가격")
    private BigInteger totalPrice;

    @NotNull
    @Column(columnDefinition = "VARCHAR(255)")
    @Comment("결제 수단")
    private String payMethod;

    @Column(columnDefinition = "VARCHAR(255)")
    @Comment("카드사 표준 코드")
    private String cardCode;

    @Column(columnDefinition = "NUMERIC(2,0)")
    @Comment("할부 개월 수")
    private String quota;

    @Column(columnDefinition = "VARCHAR(255)")
    @Comment("입금자명")
    private String payerName;

    @NotNull
    @Column(columnDefinition = "VARCHAR(255)")
    @Comment("결제 상태")
    private String payState;

    @Column(columnDefinition = "VARCHAR(255)")
    @Comment("포트원 결제 번호")
    private String impUid;

    @NotNull
    @Column(columnDefinition = "TIMESTAMP")
    @Comment("결제 승인 날짜")
    private LocalDateTime approvalDate;
}
