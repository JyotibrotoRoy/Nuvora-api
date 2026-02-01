package com.nuvora.nuvora_api.organization;

import com.nuvora.nuvora_api.auth.dto.OrgRegisterRequest;
import com.nuvora.nuvora_api.auth.dto.OrgRegisterResponse;
import com.nuvora.nuvora_api.security.JwtService;
import com.nuvora.nuvora_api.user.User;
import com.nuvora.nuvora_api.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public Organization getBySlug(String slug) {
        return organizationRepository
                .findBySlugAndIsActiveTrue(slug)
                .orElseThrow(() -> new RuntimeException("Organization not found or Inactive"));
    }

    @Transactional
    public OrgRegisterResponse registerOrganization(OrgRegisterRequest request) {

        //create org
        Organization org = Organization.builder()
                .name(request.getOrgName())
                .slug(request.getOrgSlug())
                .isActive(true)
                .build();

        org =  organizationRepository.save(org);

        //Create Owner User
        User owner = User.builder()
                .orgId(org.getId())
                .fullName(request.getOwnerFullName())
                .email(request.getOwnerEmail())
                .passwordHash(passwordEncoder.encode(request.getOwnerPassword()))
                .role("OWNER")
                .isActive(true)
                .authProvider("LOCAL")
                .build();

        owner = userRepository.save(owner);

        String token = jwtService.generateToken(owner);

        return OrgRegisterResponse.builder()
                .accessToken(token)
                .orgId(org.getId())
                .userId(owner.getId())
                .role(owner.getRole())
                .build();

    }
}
