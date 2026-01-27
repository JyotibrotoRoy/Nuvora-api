ALTER TABLE users
ADD COLUMN profile_media_id UUID REFERENCES media_files(id);