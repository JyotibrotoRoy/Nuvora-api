package com.nuvora.nuvora_api.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class ProjectRequest {

    @NotNull(message = "Client ID is required")
    private UUID clientId;

    @NotBlank(message = "Project name is required")
    private String name;

    @NotBlank(message = "Project type is required")
    private String projectType;

    private BigDecimal budgetTotal;
    private LocalDate startDate;
    private LocalDate endDate;
    private String notes;
}
