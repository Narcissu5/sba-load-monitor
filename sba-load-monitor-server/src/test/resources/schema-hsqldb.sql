DROP TABLE load_1m IF EXISTS;
CREATE TABLE load_1m   (
  id bigint IDENTITY PRIMARY KEY,
  app_name varchar(50) NOT NULL,
  port int DEFAULT NULL,
  host_name varchar(50) NOT NULL,
  count int NOT NULL,
  minute int DEFAULT NULL,
  created_at timestamp  DEFAULT now
);