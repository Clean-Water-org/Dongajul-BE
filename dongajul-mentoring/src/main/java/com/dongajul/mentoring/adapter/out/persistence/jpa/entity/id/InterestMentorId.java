package com.dongajul.mentoring.adapter.out.persistence.jpa.entity.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Embeddable
public class InterestMentorId implements Serializable {

    @NotNull
    @Column(name = "mentor_id",
            columnDefinition = "UUID")
    @Comment("멘토 ID")
    private UUID mentorId;

    @NotNull
    @Column(name = "mentee_id",
            columnDefinition = "UUID")
    @Comment("멘티 ID")
    private UUID menteeId;
}
