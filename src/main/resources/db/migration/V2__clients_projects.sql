CREATE TABLE clients(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    org_id UUID NOT NULL REFERENCES organizations(id),

    name VARCHAR(150) NOT NULL,
    contact_person_name VARCHAR(150),
    email VARCHAR(200),
    phone_number VARCHAR(30),

    city VARCHAR(100),
    country VARCHAR(100),

    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_clients_org ON clients(org_id);
CREATE UNIQUE INDEX uq_clients_org_name ON clients(org_id, name);

CREATE TABLE projects(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    org_id UUID NOT NULL REFERENCES organizations(id),
    client_id UUID NOT NULL REFERENCES clients(id),

    name VARCHAR(200) NOT NULL,
    project_type VARCHAR(50) NOT NULL,
    status VARCHAR(30) NOT NULL DEFAULT 'UPCOMING',

    budget_total NUMERIC(12,2),
    start_date DATE,
    end_date DATE,

    notes TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_projects_org_id ON projects(org_id);
CREATE INDEX idx_projects_clients ON projects(client_id);
CREATE UNIQUE INDEX uq_projects_org_client_name on projects(org_id, client_id, name);