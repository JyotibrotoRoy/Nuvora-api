CREATE TABLE invitations(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    org_id UUID NOT NULL REFERENCES organizations(id) ON DELETE CASCADE,

    email VARCHAR(200) NOT NULL,
    role VARCHAR(30) NOT NULL,

    token VARCHAR(255) UNIQUE NOT NULL,

    is_claimed BOOLEAN NOT NULL DEFAULT FALSE,
    invited_by UUID NOT NULL REFERENCES users(id),

    expires_at TIMESTAMP WITH TIME ZONE NOT NULL,

    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),

    CONSTRAINT chk_invitation_role CHECK (role IN ('OWNER', 'EMPLOYEE', 'CLIENT', 'FREELANCER'))
);

CREATE INDEX idx_invitation_token ON invitations(token);
CREATE INDEX idx_invitation_email_org ON invitations(email, org_id);