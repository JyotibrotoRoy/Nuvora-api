package com.nuvora.nuvora_api.project;

import com.nuvora.nuvora_api.client.Client;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "projects", indexes = {
        @Index(name = "idx_projects_org_id", columnList = "org_id"),
        @Index(name = "idx_projects_clients", columnList = "client_id")
})
public class Project {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "org_id", nullable = false)
    private UUID orgId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(name = "project_type", nullable = false, length = 50)
    private String projectType;

    @Column(nullable = false, length = 30)
    private String status="UPCOMING";

    @Column(name = "budget_total", precision = 12, scale = 2)
    private BigDecimal budgetTotal;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
        if(this.status == null) this.status = "UPCOMING";
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Instant.now();
    }
}
