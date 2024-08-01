package com.dongajul.mentoring.adapter.out.persistence.jpa.entity;

import com.dongajul.common.entity.BaseAuditing;
import jakarta.persistence.*;
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
@Table(name = "account")
@Comment("회원")
public class User extends BaseAuditing.Create implements Persistable<UUID> {

    @Id
    @Column(columnDefinition = "UUID")
    @Comment("회원 ID")
    private UUID id;

    @Column(columnDefinition = "BOOLEAN")
    @Comment("멘토 설정 여부")
    private boolean isMentor;

    @Column(columnDefinition = "BOOLEAN")
    @Comment("멘티 설정 여부")
    private boolean isMentee;

    @Column(columnDefinition = "BOOLEAN")
    @Comment("휴면 계정 여부")
    private boolean isSleeper;

    @Column(columnDefinition = "BOOLEAN")
    @Comment("탈퇴 여부")
    private boolean isDeleted;

    @Override
    public boolean isNew() {
        return getCreatedDate() == null;
    }
}
