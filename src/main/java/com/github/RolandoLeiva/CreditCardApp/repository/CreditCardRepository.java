package com.github.RolandoLeiva.CreditCardApp.repository;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.github.RolandoLeiva.CreditCardApp.domain.CreditCard;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@Repository
public class CreditCardRepository {
    private CqlSession session = CqlSession.builder().build();


    public CreditCardRepository(CqlSession cqlSession) {
        this.session = cqlSession;
    }

    public Mono<CreditCard> get(String id) {
        return Mono.from(session.executeReactive("SELECT * FROM credit_card.cards WHERE card_num = '" + id+"'"))
                .map(row -> new CreditCard(row.getString("card_num"), row.getString("ccv"),
                        row.getString("exp_date"), row.getString("type")));
    }

    public CreditCard create(CreditCard card) {
        SimpleStatement stmt = SimpleStatement.builder("INSERT INTO credit_card.cards(card_num,type,ccv,exp_date) VALUES(?,?,?,?)")
                .addPositionalValues(card.getCardNumber(),card.getType(),card.getCvv(),card.getExpDate())
                .build();
        Flux.from(session.executeReactive(stmt)).subscribe();
        return card;
    }
    public Flux<CreditCard> getAll() {
        return Flux.from(session.executeReactive("SELECT * FROM credit_card.cards"))
                .map(row -> new CreditCard(row.getString("card_num"), row.getString("ccv"),
                        row.getString("exp_date"), row.getString("type")));
    }
}
