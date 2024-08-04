package com.phatdo.authorizationserver.models.users;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "\"user\"")
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private final String fullName;

    @Column(unique = true)
    private final String username;

    @Enumerated(EnumType.STRING)
    private final Set<RoleE> roles = new HashSet<>();

    private final String password;

    private String avatarUrl;

}
