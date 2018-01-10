CREATE TABLE users (
  id serial PRIMARY KEY,
  email varchar(256) NOT NULL UNIQUE,
  first_name varchar(256),
  last_name varchar(256)
);