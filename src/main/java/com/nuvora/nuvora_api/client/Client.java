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
    private String contact_person_name;

    @Column(length = 200)
    private String email;

    @Column(name = "phone_number", length = 30)
    private String phone_number;

    @Column(length = 100)
    private String city;

    @Column(length = 100)
    private String country;

    @Column(name = "created_at", nullable = false)
    private Instant created_at;

    @Column(name = "updated_at", nullable = false)
    private Instant updated_at;
}
