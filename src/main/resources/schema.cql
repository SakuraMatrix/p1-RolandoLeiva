CREATE KEYSPACE IF NOT EXISTS credit_card WITH REPLICATION = {'class': 'SimpleStrategy', 'replication_factor': 1};

USE credit_card;

CREATE TABLE IF NOT EXISTS cards (
    card_num text,
    type text,
    ccv text,
    exp_date text,
    PRIMARY KEY (card_num, type)
);
INSERT INTO card()