package com.nuvora.nuvora_api.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.UUID;

@RequestMapping
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByOrgIdAndEmailAndIsActiveTrue(UUID orgId, String email);
}
