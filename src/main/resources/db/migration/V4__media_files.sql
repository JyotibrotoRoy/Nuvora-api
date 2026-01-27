CREATE TABLE media_files(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    org_id UUID NOT NULL REFERENCES organizations(id),

    uploaded_by_user_id UUID REFERENCES users(id),

    file_name VARCHAR(255) NOT NULL,
    content_type VARCHAR(120) NOT NULL,
    file_size_bytes BIGINT NOT NULL,

    storage_provider VARCHAR(30) NOT NULL,
    bucket VARCHAR(120),
    object_key TEXT,

    public_url TEXT NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_media_files_org_id ON media_files(org_id);
CREATE INDEX idx_media_files_uploaded_by_user_id ON media_files(uploaded_by_user_id);