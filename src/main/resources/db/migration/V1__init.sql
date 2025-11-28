CREATE TABLE venues (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        venue_name VARCHAR(255) NOT NULL,
                        location VARCHAR(255),
                        capacity INT NOT NULL
);

CREATE TABLE events (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        event_name VARCHAR(255) NOT NULL,
                        event_date DATE NOT NULL,
                        venue_id BIGINT,
                        CONSTRAINT fk_event_venue FOREIGN KEY (venue_id)
                            REFERENCES venues(id)
);
