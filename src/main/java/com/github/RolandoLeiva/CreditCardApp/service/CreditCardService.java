package com.github.RolandoLeiva.CreditCardApp.service;

import com.github.RolandoLeiva.CreditCardApp.domain.CreditCard;
import com.github.RolandoLeiva.CreditCardApp.repository.CQLConnect;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditCardService {

    private CQLConnect connect;

    public CreditCardService(CQLConnect connect)
    {
        this.connect = connect;
    }
    public Flux<CreditCard> getAll() {
        return connect.getAll();
    }

    public Mono<CreditCard> get(String id) {
        return connect.get(Integer.parseInt(id));
    }

    public  CreditCard create(CreditCard item) {
        return connect.insert(item);
    }

}
