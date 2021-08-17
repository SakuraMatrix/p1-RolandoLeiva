package com.github.RolandoLeiva.CreditCardApp.service;
import com.github.RolandoLeiva.CreditCardApp.domain.Payment;
import com.github.RolandoLeiva.CreditCardApp.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PaymentService {

    private PaymentRepository connect;

    public  PaymentService(PaymentRepository connect)
    {
        this.connect = connect;
    }

    public Flux<Payment> getAll() {
        return connect.getAll();
    }

    public Mono<Payment> get(String id) {return connect.get(id); }

    public  Payment create(Payment payment) { return connect.create(payment); }
}
