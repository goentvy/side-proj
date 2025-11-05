package com.entvy.openbidhub.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 20)
    private String role; // 예: "admin", "user", "partner"

    @Column(nullable = false)
    @Builder.Default
    private boolean active = true;

    // 추가 예정 필드
//    private String name;
//    private String phone;
}
