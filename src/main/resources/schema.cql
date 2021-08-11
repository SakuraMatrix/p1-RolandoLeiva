CREATE KEYSPACE [IF NOT EXISTS] creditCard WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};

USE creditCard;

CREATE TABLE IF NOT EXISTS cards (
    item_id int,
    name text,
    cardType text,
    minPay double,
    creditLimit double,
    PRIMARY KEY (item_id, name)
);