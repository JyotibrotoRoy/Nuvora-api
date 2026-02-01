package com.nuvora.nuvora_api.project;

import com.nuvora.nuvora_api.auth.dto.ProjectRequest;
import com.nuvora.nuvora_api.client.Client;
import com.nuvora.nuvora_api.client.ClientRepository;
import com.nuvora.nuvora_api.organization.OrganizationRepository;
import com.nuvora.nuvora_api.security.TenantContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ClientRepository clientRepository;

    @Transactional
    public Project createProject(ProjectRequest request) {

        UUID orgId = TenantContext.getTenantId();

        System.out.println("--------------------------------------------------");
        System.out.println("🚨 SECURITY DEBUG CHECK 🚨");
        System.out.println("1. Logged-in Org ID (from Token): " + orgId);
        System.out.println("2. Requested Client ID: " + request.getClientId());

        boolean existsGlobally = clientRepository.existsById(request.getClientId());
        System.out.println("3. Does this Client ID exist in DB? " + existsGlobally);

        Client client = clientRepository.findByIdAndOrgId(request.getClientId(), orgId)
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));

        Project project = new Project();
        project.setOrgId(orgId);
        project.setClient(client);
        project.setName(request.getName());
        project.setProjectType(request.getProjectType());
        project.setBudgetTotal(request.getBudgetTotal());
        project.setStartDate(request.getStartDate());
        project.setEndDate(request.getEndDate());
        project.setNotes(request.getNotes());

        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAllByOrgId(TenantContext.getTenantId());
    }

    public Project getProjectById(UUID projectId) {

        UUID orgId = TenantContext.getTenantId();

        return projectRepository.findById(projectId)
                .filter(project -> project.getOrgId().equals(orgId))
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));
    }
}
