CREATE TABLE IF NOT EXISTS oauth_access_token(
  token_id          character varying(255),
  token             BYTEA,
  authentication_id character varying(255),
  user_name         character varying(255),
  client_id         character varying(255),
  authentication    BYTEA,
  refresh_token character varying(255)
);

CREATE TABLE IF NOT EXISTS oauth_refresh_token(
  token_id        character varying(255),
  token           BYTEA,
  authentication  BYTEA
);

CREATE TABLE IF NOT EXISTS oauth_code(
  code            character varying(255),
  authentication  BYTEA
);

CREATE TABLE IF NOT EXISTS oauth_approvals(
  userId         character varying(255),
  clientId       character varying(255),
  scope          character varying(255),
  status         VARCHAR(10),
  expiresAt      TIMESTAMP,
  lastModifiedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS ClientDetails(
  appId                  character varying(255) PRIMARY KEY,
  resourceIds            character varying(255),
  appSecret              character varying(255),
  scope                  character varying(255),
  grantTypes             character varying(255),
  redirectUrl            character varying(255),
  authorities            character varying(255),
  access_token_validity  INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation  VARCHAR(4096),
  autoApproveScopes      character varying(255)
);

create index oauth_access_token_id on oauth_access_token(token_id);
create index oauth_refresh_token_id on oauth_access_token(token_id);

--START CLIENT CREDENTIAL TABLES--

CREATE TABLE IF NOT EXISTS oauth_client_details(
  client_id               VARCHAR(256) PRIMARY KEY,
  resource_ids            VARCHAR(256),
  client_secret           VARCHAR(256),
  scope                   VARCHAR(256),
  authorized_grant_types  VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities             VARCHAR(256),
  access_token_validity   INTEGER,
  refresh_token_validity  INTEGER,
  additional_information  VARCHAR(4096),
  autoapprove             VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS oauth_client_token(
  token_id          character varying(255),
  token             BYTEA,
  authentication_id character varying(255),
  user_name         character varying(255),
  client_id         character varying(255)
);

CREATE TABLE IF NOT EXISTS user_role (
  user_id bigint NOT NULL,
  role_id bigint NOT NULL
);

-- ############################################################################################# --
CREATE SEQUENCE users_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE SEQUENCE roles_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;


CREATE TABLE IF NOT EXISTS users (
    id bigint NOT NULL DEFAULT nextval('users_id_seq'::regclass),
    email character varying(255),
    mobile character varying(255),
    password character varying(255),
    user_name character varying(255),
    CONSTRAINT users_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS roles (
    id bigint NOT NULL DEFAULT nextval('roles_id_seq'::regclass),
    description character varying(255),
    role_name character varying(255),
    CONSTRAINT roles_pkey PRIMARY KEY (id)
);


-- BASIC AUTH with token time declarationuser_role
-- ACCESS TOKEN VALIDITY = 300 SECOND
-- REFRESH TOKEN VALIDITY = 1800 SECOND
-- insert client details [clientId = mobile & clientSecret = widya]
INSERT INTO oauth_client_details
(client_id, client_secret, scope, authorized_grant_types,
authorities, access_token_validity, refresh_token_validity)
VALUES ('mobile', '$2a$04$Vi2S7.Txl3V6bdsgMGNGFeUybqUBJSJn1G4BaPTfTx6vfEejCClhO',
'read,write', 'password,refresh_token,client_credentials,authorization_code',
'ROLE_TRUSTED_CLIENT', 300, 1800);


-- ##########################################################--
-- USER ROLES

INSERT INTO roles(id, role_name, description)
VALUES (1, 'ADMIN', 'Admin User - Has permission to perform admin tasks');

INSERT INTO roles(id, role_name, description)
VALUES (2, 'USER', 'CUSTOMER - Has no admin rights');