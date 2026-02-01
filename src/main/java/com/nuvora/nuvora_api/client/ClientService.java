package com.nuvora.nuvora_api.client;

import com.nuvora.nuvora_api.auth.dto.ClientRequest;
import com.nuvora.nuvora_api.security.TenantContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    @Transactional
    public Client createClient(ClientRequest request) {

        UUID orgId = TenantContext.getTenantId();

        if(clientRepository.existsByOrgIdAndEmail(orgId, request.getEmail())) {
            throw new IllegalArgumentException("Client already exists");
        }

        Client client = new Client();
        client.setOrgId(orgId);
        client.setName(request.getName());
        client.setEmail(request.getEmail());
        client.setContactPersonName(request.getContactPersonName());
        client.setPhoneNumber(request.getPhoneNumber());
        client.setCity(request.getCity());
        client.setCountry(request.getCountry());

        return clientRepository.save(client);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAllByOrgId(TenantContext.getTenantId());
    }

    public Client getClient(UUID clientId) {
        return clientRepository.findByIdAndOrgId(clientId, TenantContext.getTenantId())
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));
    }

}
