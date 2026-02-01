package com.nuvora.nuvora_api.organization;

import com.nuvora.nuvora_api.auth.dto.OrgRegisterRequest;
import com.nuvora.nuvora_api.auth.dto.OrgRegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/org")
@RequiredArgsConstructor
public class OrgController {

    private final OrganizationService organizationService;

    @PostMapping("/register")
    public ResponseEntity<OrgRegisterResponse> register(
            @RequestBody OrgRegisterRequest request) {

        OrgRegisterResponse response = organizationService.registerOrganization(request);
        return ResponseEntity.ok(response);
    }
}
