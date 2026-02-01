package com.nuvora.nuvora_api.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID> {

    List<Project> findAllByOrgId(UUID orgId);

    List<Project> findAllByOrgIdAndClientId(UUID orgId, UUID clientId);
}
