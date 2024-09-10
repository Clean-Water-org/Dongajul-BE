package com.dongajul.user.adapter.out.persistence.jpa.entity;

import com.dongajul.common.entity.BaseAuditing;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "account")
@Comment("회원")
public class UserEntity extends BaseAuditing.CreateAndUpdate {

    @Id
//    @Tsid // TSID (Time-Sorted Unique Identifier), 이거 사용하면 Bigint 타입임
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Column(columnDefinition = "UUID")
    @Comment("회원 ID")
    private UUID id;

    // TODO 화면에 회원가입 시 닉네임 입력 받음, 근데 엔티티에는 없음

    @NotNull
    @Column(columnDefinition = "VARCHAR(16)")
    @Comment("회원 이름")
    private String userName;

    @Column(columnDefinition = "BOOLEAN")
    @Comment("인증 여부")
    private boolean isAuthenticatedPhone;

    @NotNull
    @Column(columnDefinition = "VARCHAR(200)")
    @Comment("이메일(로그인 ID)")
    private String email;

    @NotNull
    @Column(columnDefinition = "VARCHAR(255)")
    @Comment("비밀번호")
    private String password;

    @Column(columnDefinition = "VARCHAR(15)")
    @Comment("휴대폰번호")
    private String phone;

    @Column(columnDefinition = "BOOLEAN")
    @Comment("휴면계정 여부")
    private boolean isSleeper;

    @Column(columnDefinition = "BOOLEAN")
    @Comment("탈퇴 여부")
    private boolean isDeleted;
}
