CREATE KEYSPACE IF NOT EXISTS credit_card WITH REPLICATION = {'class': 'SimpleStrategy', 'replication_factor': 1};

USE credit_card;

CREATE TABLE IF NOT EXISTS cards (
    card_num text,
    type text,
    ccv text,
    exp_date text,
    PRIMARY KEY (card_num, type)
);
INSERT INTO cards(card_num,type,ccv,exp_date) VALUES('12345678912345','Visa','987','08-25');
INSERT INTO cards(card_num,type,ccv,exp_date) VALUES('987654321012','MasterCard','357','02-25');

CREATE TABLE IF NOT EXISTS payment(
    user text,
    card_num text,
    min_payment text,
    payment_amount text,
    PRIMARY KEY (user, card_num));
INSERT INTO payment(user,card_num,min_payment,payment_amount) VALUES('KOD','12345678912345','20.00','25.00');