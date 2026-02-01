package com.nuvora.nuvora_api.auth.dto;

import lombok.Data;

@Data
public class LoginRequest {

    private String orgSlug;

    private String email;

    private String password;
}
