package com.dongajul.user.adapter.out.persistence.jpa.entity;

import com.dongajul.common.entity.BaseAuditing;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.data.domain.Persistable;

import java.util.UUID;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table
@Comment("멘티")
public class Mentee extends BaseAuditing.CreateAndUpdate implements Persistable<UUID> {

    @Id
    @Column(columnDefinition = "UUID")
    @Comment("멘티 ID")
    private UUID id;

    @Column(columnDefinition = "BOOLEAN")
    @Comment("구직 상태")
    private boolean isWorking;

    @NotNull
    @Column(columnDefinition = "VARCHAR(20)")
    @Comment("최종학력")
    private String educationCode;

    @NotNull
    @Column(columnDefinition = "VARCHAR(200)")
    @Comment("졸업학교")
    private String school;

    @Column(columnDefinition = "VARCHAR(20)")
    @Comment("전공")
    private String major;

    @Column(columnDefinition = "VARCHAR(20)")
    @Comment("업무 분야")
    private String workTypeCode;

    @Column(columnDefinition = "VARCHAR(20)")
    @Comment("업무 분야 상세")
    private String workTypeDetailCode;

    @Column(columnDefinition = "VARCHAR(20)")
    @Comment("연차")
    private String careerYearCode;

    @Column(columnDefinition = "VARCHAR(200)")
    @Comment("소개서")
    private String description;

    @Override
    public boolean isNew() {
        return getCreatedDate() == null;
    }
}
