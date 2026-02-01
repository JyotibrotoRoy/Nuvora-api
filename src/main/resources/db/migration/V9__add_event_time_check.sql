ALTER TABLE events
ADD CONSTRAINT chk_event_times CHECK (end_time > start_time);