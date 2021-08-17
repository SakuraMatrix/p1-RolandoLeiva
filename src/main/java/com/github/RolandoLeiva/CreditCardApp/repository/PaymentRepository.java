package com.github.RolandoLeiva.CreditCardApp.repository;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.github.RolandoLeiva.CreditCardApp.domain.Payment;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class PaymentRepository {
    private CqlSession session = CqlSession.builder().build();

    public PaymentRepository(CqlSession cqlSession) {
        this.session = cqlSession;
    }

    public Mono<Payment> get(String id) {
        return Mono.from(session.executeReactive("SELECT * FROM credit_card.payment WHERE user = '" + id+"';"))
                .map(row -> new Payment(row.getString("user"), row.getString("card_num"),
                        row.getString("min_payment"), row.getString("payment_amount")));
    }

    public Flux<Payment> getAll() {
        return Flux.from(session.executeReactive("SELECT * FROM credit_card.payment;"))
                .map(row -> new Payment(row.getString("user"), row.getString("card_num"),
                        row.getString("min_payment"), row.getString("payment_amount")));
    }

    public Payment create(Payment pay) {
        SimpleStatement stmt = SimpleStatement.builder("INSERT INTO credit_card.payment(user,card_num,min_payment,payment_amount)"+
                "VALUES(?,?,?,?);")
                .addPositionalValues(pay.getUser(),pay.getCard_num(),pay.getMin_payment(),pay.getPayment_amount())
                .build();
        Flux.from(session.executeReactive(stmt)).subscribe();
        return pay;
    }
}
