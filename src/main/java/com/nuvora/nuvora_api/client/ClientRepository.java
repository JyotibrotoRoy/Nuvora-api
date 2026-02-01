package com.nuvora.nuvora_api.client;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {

    List<Client> findAllByOrgId(UUID orgId);

    Optional<Client> findByIdAndOrgId(UUID id, UUID orgId);

    boolean existsByOrgIdAndEmail(UUID orgId, String email);
}
