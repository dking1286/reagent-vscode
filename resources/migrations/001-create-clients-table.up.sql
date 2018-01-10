CREATE TABLE clients (
  id serial PRIMARY KEY,
  client_id varchar(256) NOT NULL,
  client_secret varchar(256) NOT NULL,
  is_trusted boolean NOT NULL DEFAULT false
);