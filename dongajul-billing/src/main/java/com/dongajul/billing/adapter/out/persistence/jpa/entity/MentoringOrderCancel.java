package com.dongajul.billing.adapter.out.persistence.jpa.entity;

import com.dongajul.common.entity.BaseAuditing;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigInteger;
import java.util.UUID;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table
@Comment("결제 취소")
public class MentoringOrderCancel extends BaseAuditing.Create {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(columnDefinition = "UUID")
    @Comment("정산 계좌 ID")
    private UUID id;

    @NotNull
    @Column(columnDefinition = "BIGINT")
    @Comment("취소 금액")
    private BigInteger cancelAmount;

    @ToString.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @Comment("결제 ID")
    @JoinColumn
    private Payment payment;
}
