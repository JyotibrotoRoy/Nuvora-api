package com.nuvora.nuvora_api.user;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "org_id", nullable = false)
    private UUID orgId;

    @Column(name = "client_id")
    private UUID clientId;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String email;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(nullable = false)
    private String role;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "auth_provider", nullable = false)
    private String authProvider;

    @Column(name = "profile_media_id")
    private UUID profileMediaId;

    @Column(name = "created_at", updatable = false)
    private Instant created_at;

    @Column(name = "updated_at")
    private Instant updated_at;

    @PrePersist
    protected void onCreate() {
        created_at = Instant.now();
        updated_at = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updated_at = Instant.now();
    }
}
