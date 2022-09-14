CREATE TABLE IF NOT EXISTS clients
(
    api_key         varchar PRIMARY KEY,
    name            varchar        NOT NULL,
    bitcoin_balance DECIMAL(16, 8) NOT NULL,
    brl_balance     DECIMAL(16, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS credentials
(
    api_key    varchar PRIMARY KEY,
    api_secret varchar NOT NULL,
    CONSTRAINT fk_credentials_client foreign key (api_key) references clients (api_key) on delete CASCADE
);

CREATE TABLE IF NOT EXISTS offers
(
    offer_id      varchar PRIMARY KEY,
    base          DECIMAL(16, 8) NOT NULL,
    quote         DECIMAL(16, 8) NOT NULL,
    operation     varchar(4)     NOT NULL,
    quoted_in_brl BOOLEAN        NOT NULL,
    base_amount   DECIMAL(16, 8) NOT NULL,
    unitary_value DECIMAL(16, 2) NOT NULL,
    quote_amount  DECIMAL(16, 8) NOT NULL,
    created_at    TIMESTAMP      NOT NULL,
    expires_at    TIMESTAMP      NOT NULL,
    confirmed_at  TIMESTAMP      NOT NULL,
    api_key_id    varchar        NOT NULL,
    CONSTRAINT fk_offers_client foreign key (api_key_id) references clients (api_key) on delete CASCADE
);