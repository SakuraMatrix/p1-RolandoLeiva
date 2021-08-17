package com.github.RolandoLeiva.CreditCardApp.service;

import com.github.RolandoLeiva.CreditCardApp.domain.CreditCard;
import com.github.RolandoLeiva.CreditCardApp.repository.CreditCardRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditCardService {

    private CreditCardRepository connect;

    public CreditCardService(CreditCardRepository connect)
    {
        this.connect = connect;
    }

    public Flux<CreditCard> getAll() {
        return connect.getAll();
    }

    public Mono<CreditCard> get(String id) { return connect.get(id);}

    public  CreditCard create(CreditCard card) {
        return connect.create(card);
    }

}
