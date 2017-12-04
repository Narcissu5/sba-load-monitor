DROP TABLE load_1m IF EXISTS;
CREATE TABLE load_1m   (
  id bigint IDENTITY PRIMARY KEY,
  app_name varchar(50) NOT NULL,
  port int DEFAULT NULL,
  host_name varchar(50) NOT NULL,
  count int NOT NULL,
  minute int DEFAULT NULL,
  s1xx int NOT NULL,
  s2xx int NOT NULL,
  s3xx int NOT NULL,
  s4xx int NOT NULL,
  s5xx int NOT NULL,
  created_at timestamp  DEFAULT now
);

CREATE TABLE load_1m_aggr (
  id bigint IDENTITY  PRIMARY KEY,
  app_name varchar(50) NOT NULL,
  count int NOT NULL,
  minute int NOT NULL,
  s1xx int NOT NULL,
  s2xx int NOT NULL,
  s3xx int NOT NULL,
  s4xx int NOT NULL,
  s5xx int NOT NULL,
  created_at timestamp DEFAULT now
);
