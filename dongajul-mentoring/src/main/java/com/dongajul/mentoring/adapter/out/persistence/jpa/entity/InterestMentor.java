package com.dongajul.mentoring.adapter.out.persistence.jpa.entity;

import com.dongajul.common.entity.BaseAuditing;
import com.dongajul.mentoring.adapter.out.persistence.jpa.entity.id.InterestMentorId;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.data.domain.Persistable;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table
@Comment("관심 멘토")
public class InterestMentor extends BaseAuditing.Create implements Persistable<InterestMentorId> {

    @EmbeddedId
    private InterestMentorId id;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(insertable=false, updatable=false)
    private Mentor mentor;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(insertable=false, updatable=false)
    private Mentee mentee;

    @Override
    public boolean isNew() {
        return getCreatedDate() == null;
    }
}
