package com.github.RolandoLeiva.CreditCardApp.repository;


import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.github.RolandoLeiva.CreditCardApp.domain.CreditCard;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Repository
public class CQLConnect {

    private CqlSession cqlSession = CqlSession.builder().build();


    public CQLConnect(CqlSession cqlSession) {
        this.cqlSession = cqlSession;
    }

    public Mono<CreditCard> get(int id) {
        return Mono.from(cqlSession.executeReactive("SELECT * FROM credit_card.cards WHERE card_num = ?" ))
                .map(row -> new CreditCard(row.getString("card_num"), row.getString("type"),
                        row.getString("ccv"), row.getString("exp_date")));
    }
    public CreditCard insert(CreditCard creditCard)
    {
        BoundStatement boundStatement =
        cqlSession.prepare("INSERT INTO credit_card.cards(card_num,type,ccv,exp_date) " +
                "VALUES(?,?,?,?)").bind()
                .setString(0,creditCard.getCardNumber())
                .setString(1,creditCard.getType())
                .setString(2,creditCard.getCvv())
                .setString(3,creditCard.getExpDate());
        cqlSession.execute(boundStatement);
        return creditCard;

    }
    public CreditCard create(CreditCard card) {
        SimpleStatement stmt = SimpleStatement.builder("INSERT INTO credit_card.cards(card_num,type,ccv,exp_date) VALUES(?,?,?,?)")
                .addPositionalValues(card.getCardNumber(),card.getType(),card.getCvv(),card.getExpDate())
                .build();
        Flux.from(cqlSession.executeReactive(stmt)).subscribe();
        return card;
    }
    public Flux<CreditCard> getAll() {
        return Flux.from(cqlSession.executeReactive("SELECT * FROM credit_card.cards"))
                .map(row -> new CreditCard(row.getString("card_num"), row.getString("ccv"), row.getString("exp_date"), row.getString("type")));
    }
    public void createKeyspace()
    {
        String cql = "CREATE KEYSPACE IF NOT EXISTS credit_card WITH REPLICATION = {'class': 'SimpleStrategy', 'replication_factor': 1};";
        cqlSession.execute(cql);
        String cqlkey ="USE credit_card;";
        cqlSession.execute(cqlkey);
    }
    public void createTable()
    {
        String cql = "CREATE TABLE IF NOT EXISTS cards (\n" +
                "    card_num text,\n" +
                "    type text,\n" +
                "    ccv text,\n" +
                "    exp_date text,\n" +
                "    PRIMARY KEY (card_num, type)\n" +
                ");";
        cqlSession.execute(cql);
    }
}
