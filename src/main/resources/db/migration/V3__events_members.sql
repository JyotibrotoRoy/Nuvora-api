CREATE TABLE events(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    org_id UUID NOT NULL REFERENCES organizations(id),
    project_id UUID NOT NULL REFERENCES projects(id),

    title VARCHAR(200) NOT NULL,
    event_type VARCHAR(50) NOT NULL DEFAULT 'SHOOT',
    status VARCHAR(30) NOT NULL DEFAULT 'SCHEDULED',

    location_name VARCHAR(200),
    address TEXT,

    start_time TIMESTAMP NOT NULL DEFAULT NOW(),
    end_time TIMESTAMP NOT NULL DEFAULT NOW(),

    notes TEXT,

    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_events_org_id ON events(org_id);
CREATE INDEX idx_events_project_id ON events(project_id);

CREATE TABLE event_members(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    org_id UUID NOT NULL REFERENCES organizations(id),
    event_id UUID NOT NULL REFERENCES events(id),
    user_id UUID NOT NULL REFERENCES users(id),

    role_on_event VARCHAR(50) NOT NULL,

    is_confirmed BOOLEAN NOT NULL DEFAULT FALSE,
    confirmed_at TIMESTAMP,

    assigned_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_event_members_event_id ON event_members(event_id);
CREATE INDEX idx_event_members_user_id ON event_members(user_id);

CREATE UNIQUE INDEX uq_event_members_event_user ON event_members(event_id, user_id);