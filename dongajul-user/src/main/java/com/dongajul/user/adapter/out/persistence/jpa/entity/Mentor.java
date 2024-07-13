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
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
@Comment("멘토")
public class Mentor extends BaseAuditing.CreateAndUpdate implements Persistable<UUID> {

    @Id
    @Column(columnDefinition = "UUID")
    @Comment("멘토 ID")
    private UUID id;

    @NotNull
    @Column(columnDefinition = "VARCHAR(20)")
    @Comment("업무 분야")
    private String workTypeCode;

    @NotNull
    @Column(columnDefinition = "VARCHAR(20)")
    @Comment("업무 분야 상세")
    private String workTypeDetailCode;

    @NotNull
    @Column(columnDefinition = "VARCHAR(20)")
    @Comment("연차")
    private String careerYearCode;

    @NotNull
    @Column(columnDefinition = "VARCHAR(200)")
    @Comment("소개서")
    private String description;

    @Override
    public boolean isNew() {
        return getCreatedDate() == null;
    }
}
