package com.nuvora.nuvora_api.auth.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class OrgRegisterResponse {

    private String accessToken;

    private UUID orgId;
    private UUID userId;

    private String role;
}
