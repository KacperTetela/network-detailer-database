package com.networkdetailerdatabase.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "app_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    @JsonIgnore
    private String password;

    @Column(unique = true, nullable = false)
    private String accessKey;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DeviceScan> scans = new ArrayList<>();

    /** Factory method to create user with random api key */
    public static User create(String username, String password) {
        return User.builder()
                .username(username)
                .password(password)
                .accessKey(UUID.randomUUID().toString())
                .build();
    }
}
