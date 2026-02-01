package com.nuvora.nuvora_api.client;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "clients",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_clients_org_name", columnNames = {"org_id", "name"})
        },
        indexes = {
                @Index(name = "idx_clients_org", columnList = "org_id")
        }
)
public class Client {

    @Id
    @GeneratedValue
    private UUID id;


    @Column(name = "org_id", nullable = false)
    private UUID orgId;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(name = "contact_person_name", length = 150)
    private String contactPersonName;

    @Column(length = 200)
    private String email;

    @Column(name = "phone_number", length = 30)
    private String phoneNumber;

    @Column(length = 100)
    private String city;

    @Column(length = 100)
    private String country;

    @Column(length = 30)
    private String status = "ACTIVE";

    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
        if(this.status == null) this.status = "ACTIVE";
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Instant.now();
    }
}
