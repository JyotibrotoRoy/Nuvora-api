package com.nuvora.nuvora_api.organization;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "organizations")
@Builder
public class Organization {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String slug;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

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
