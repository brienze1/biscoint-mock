DROP TABLE IF EXISTS offers;
DROP TABLE IF EXISTS credentials;
DROP TABLE IF EXISTS clients;

CREATE TABLE clients
(
    api_key         varchar PRIMARY KEY,
    name            varchar        NOT NULL,
    bitcoin_balance DECIMAL(16, 8) NOT NULL,
    brl_balance     DECIMAL(16, 2) NOT NULL
);

CREATE TABLE credentials
(
    api_key    varchar PRIMARY KEY,
    api_secret varchar NOT NULL,
    CONSTRAINT fk_credentials_client foreign key (api_key) references clients (api_key) on delete CASCADE
);

CREATE TABLE offers
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
    expires_at    TIMESTAMP      NULL,
    confirmed_at  TIMESTAMP      NULL,
    api_key_id    varchar        NOT NULL,
    CONSTRAINT fk_offers_client foreign key (api_key_id) references clients (api_key) on delete CASCADE
);

INSERT INTO clients(api_key, bitcoin_balance, brl_balance, name)
VALUES ('3ddc931bf25c94ff0344a2e409aa37339e400b8d4da72265f97c3a31d0cfb36e', 0, 1000, 'Luis Felipe');

INSERT INTO credentials(api_key, api_secret)
VALUES ('3ddc931bf25c94ff0344a2e409aa37339e400b8d4da72265f97c3a31d0cfb36e', '123ASD');
