CREATE TABLE IF NOT EXISTS users
(
    user_id   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    user_name VARCHAR(32)                             NOT NULL,
    email     VARCHAR(512)                            NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (user_id),
    CONSTRAINT UQ_USER_EMAIL UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS categories
(
    category_id   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    category_name VARCHAR(50)                             NOT NULL,
    CONSTRAINT pk_category PRIMARY KEY (category_id),
    CONSTRAINT uq_category_name UNIQUE (category_name)
);

CREATE TABLE IF NOT EXISTS locations
(
    location_id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    lat         DECIMAL,
    lon         DECIMAL,
    CONSTRAINT pk_location PRIMARY KEY (location_id)
);

CREATE TABLE IF NOT EXISTS events
(
    event_id           BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    title              VARCHAR(255)                            NOT NULL,
    annotation         VARCHAR(1000)                           NOT NULL,
    category_id        BIGINT                                  NOT NULL,
    confirmed_requests BIGINT                                  NOT NULL,
    created_on         TIMESTAMP                               NOT NULL,
    description        VARCHAR(2000)                           NOT NULL,
    event_date         TIMESTAMP WITHOUT TIME ZONE             NOT NULL,
    user_id            BIGINT                                  NOT NULL,
    location_id        BIGINT,
    paid               BOOLEAN                                 NOT NULL,
    participant_limit  BIGINT                                  NOT NULL,
    published_on       TIMESTAMP WITHOUT TIME ZONE,
    request_moderation BOOLEAN                                 NOT NULL,
    state              BIGINT                                  NOT NULL,
    views              BIGINT,
    CONSTRAINT pk_event PRIMARY KEY (event_id),
    CONSTRAINT fk_events_on_categories FOREIGN KEY (category_id) REFERENCES categories (category_id),
    CONSTRAINT fk_events_on_users FOREIGN KEY (user_id) REFERENCES users (user_id),
    CONSTRAINT fk_events_on_locations FOREIGN KEY (location_id) REFERENCES locations (location_id)
);

CREATE TABLE IF NOT EXISTS requests
(
    request_id   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    created      TIMESTAMP WITHOUT TIME ZONE             NOT NULL,
    event_id     BIGINT                                  NOT NULL,
    requester_id BIGINT                                  NOT NULL,
    status       BIGINT                                  NOT NULL,
    CONSTRAINT pk_request PRIMARY KEY (request_id),
    CONSTRAINT fk_request_on_events FOREIGN KEY (event_id) REFERENCES events (event_id),
    CONSTRAINT fk_request_on_users FOREIGN KEY (request_id) REFERENCES users (user_id)
);

CREATE TABLE IF NOT EXISTS compilations
(
    compilation_id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    title          VARCHAR(100)                            NOT NULL,
    pinned         BOOLEAN,
    CONSTRAINT pk_compilation PRIMARY KEY (compilation_id)
);

CREATE TABLE IF NOT EXISTS event_compilations
(
    event_compilation_id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    event_id             BIGINT                                  NOT NULL,
    compilation_id       BIGINT                                  NOT NULL,
    CONSTRAINT pk_event_compilation PRIMARY KEY (event_compilation_id),
    CONSTRAINT fk_compilation_event FOREIGN KEY (event_id)
        REFERENCES events (event_id) ON DELETE CASCADE,
    CONSTRAINT fk_event_compilation FOREIGN KEY (compilation_id)
        REFERENCES compilations (compilation_id) ON DELETE SET NULL
);