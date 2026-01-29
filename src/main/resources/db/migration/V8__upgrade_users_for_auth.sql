ALTER TABLE users
RENAME COLUMN password TO password_hash;

ALTER TABLE users
ADD COLUMN auth_provider VARCHAR(20) NOT NULL
DEFAULT 'LOCAL';

ALTER TABLE USERS
ADD COLUMN client_id UUID;

ALTER TABLE users
ADD CONSTRAINT chk_users_role
CHECK ( auth_provider IN ('OWNER', 'EMPLOYEE', 'CLIENT') );

ALTER TABLE users
ADD CONSTRAINT chk_users_auth_provider
CHECK ( auth_provider IN ('LOCAL', 'GOOGLE') );

ALTER TABLE users
ADD CONSTRAINT fk_users_client
FOREIGN KEY (client_id) REFERENCES clients(id);