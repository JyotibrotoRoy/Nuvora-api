package com.nuvora.nuvora_api.organization;

import org.springframework.stereotype.Service;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;
    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    public Organization getBySlug(String slug) {
        return organizationRepository
                .findBySlugAndIsActiveTrue(slug)
                .orElseThrow(() -> new RuntimeException("Organization not found or Inactive"));
    }
}
