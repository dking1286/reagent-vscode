CREATE TABLE access_tokens (
  id serial PRIMARY KEY,
  token varchar(256) NOT NULL,
  user_id integer REFERENCES users ON DELETE CASCADE,
  client_id integer REFERENCES clients ON DELETE CASCADE
);