-- idea 连接
jdbc:cassandra://192.168.71.187:9042/person

-- cassandra
DESCRIBE keyspaces;
CREATE KEYSPACE IF NOT EXISTS person WITH replication = {'class':'SimpleStrategy', 'replication_factor':1}

use person;
CREATE TABLE IF NOT EXISTS info (id varchar, name text, age int, PRIMARY KEY (id)) WITH CLUSTERING ORDER BY (age DESC);
CREATE INDEX name_inx ON info (name);

INSERT INTO info (id, name, age) VALUES('222','ram', 5);