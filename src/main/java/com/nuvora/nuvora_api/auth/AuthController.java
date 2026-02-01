package com.nuvora.nuvora_api.auth;

import com.nuvora.nuvora_api.auth.dto.LoginRequest;
import com.nuvora.nuvora_api.auth.dto.LoginResponse;
import com.nuvora.nuvora_api.organization.Organization;
import com.nuvora.nuvora_api.organization.OrganizationService;
import com.nuvora.nuvora_api.security.JwtService;
import com.nuvora.nuvora_api.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final OrganizationService organizationService;
    private final JwtService jwtService;


    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        //Get org by their slug name
        Organization org = organizationService.getBySlug(request.getOrgSlug());

        //Authenticate user
        User user = authService.authenticate(
                org.getId(),
                request.getEmail(),
                request.getPassword()
        );

        String token = jwtService.generateToken(user);

        return LoginResponse.builder()
                .accessToken(token)
                .userId(user.getId())
                .orgId(user.getOrgId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

}
