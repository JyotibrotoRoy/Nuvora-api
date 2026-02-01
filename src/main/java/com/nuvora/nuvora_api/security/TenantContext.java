package com.nuvora.nuvora_api.security;

import java.util.UUID;

public class TenantContext {
    private static final ThreadLocal<UUID> currentTenant = new ThreadLocal<>();

    public static UUID getTenantId() {
        return currentTenant.get();
    }

    public static void setTenantId(UUID tenantId) {
        TenantContext.currentTenant.set(tenantId);
    }

    public static void clear() {
        currentTenant.remove();
    }
}
