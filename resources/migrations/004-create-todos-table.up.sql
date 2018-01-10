CREATE TABLE todos (
  id serial PRIMARY KEY,
  title varchar(256) NOT NULL,
  body text NOT NULL,
  is_done boolean NOT NULL DEFAULT false
);