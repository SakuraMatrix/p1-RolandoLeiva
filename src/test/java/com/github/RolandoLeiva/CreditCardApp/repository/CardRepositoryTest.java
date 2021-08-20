package com.github.RolandoLeiva.CreditCardApp.repository;

import com.datastax.oss.driver.api.core.CqlSession;
import com.github.RolandoLeiva.CreditCardApp.domain.CreditCard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


public class CardRepositoryTest {

    CqlSession session = CqlSession.builder().build();
    CreditCardRepository creditCardRepository = new CreditCardRepository(session);

    @Test
    public void testAddCard()
    {
        Mono<CreditCard> source = Mono.just(new CreditCard("10000000000000000","654","02-24","Visa"));
       StepVerifier.create(source)
                .consumeNextWith(CreditCard::getCardNumber)
                .verifyComplete();
    }
    @Test
    public void testGetCard()
    {
        Mono<CreditCard> source = (creditCardRepository.get("10000000000000000"));
        StepVerifier.create(source)
                .consumeNextWith(CreditCard::getCardNumber)
                .verifyComplete();

    }
}
