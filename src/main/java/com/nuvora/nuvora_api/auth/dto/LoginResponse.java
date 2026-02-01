package com.nuvora.nuvora_api.auth.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class LoginResponse {

    private String accessToken;

    private UUID userId;

    private UUID orgId;

    private String fullName;

    private String email;

    private String role;
}
