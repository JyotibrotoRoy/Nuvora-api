package com.nuvora.nuvora_api.auth.dto;


import lombok.Data;

@Data
public class OrgRegisterRequest {
    private String orgName;
    private String orgSlug;

    private String ownerFullName;
    private String ownerEmail;
    private String ownerPassword;

    private String city;
}
