package com.github.RolandoLeiva.CreditCardApp;

import com.datastax.oss.driver.api.core.CqlSession;
import com.github.RolandoLeiva.CreditCardApp.service.CreditCardService;
import com.github.RolandoLeiva.CreditCardApp.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
@ComponentScan
public class AppConfig {

    @Autowired
    CreditCardService creditCardService;

    @Autowired
    PaymentService paymentService;

    @Bean
    public CqlSession session() {
        return CqlSession.builder().build();
    }

    @Bean
    public DisposableServer server() throws URISyntaxException {
        Path errorHTML = Paths.get(App.class.getResource("/errorHTML.html").toURI());
        Path indexHTML = Paths.get(App.class.getResource("/index.html").toURI());
        Path paymentHTML = Paths.get(App.class.getResource("/payment.html").toURI());
        return HttpServer.create()
                .port(8080)
                .route(routes ->
                        routes.get("/cards", (request, response) ->
                                response.send(creditCardService.getAll()
                                        .map(App::toByteBuf)
                                        .log("http-server")))
                                .post("/cards", (request, response) ->
                                        response.send(request.receive().asString()
                                                .map(App::parseCard)
                                                .map(creditCardService::create)
                                                .map(App::toByteBuf)
                                                .log("http-server")))
                                .get("/cards/{param}", (request, response) ->
                                        response.send(creditCardService.get(request.param("param"))
                                                .map(App::toByteBuf)
                                                .log("http-server")))
                                .get("/error", (request, response) ->
                                        response.status(404).addHeader("Message", "Goofed")
                                                .sendFile(errorHTML))
                                .post("/submit", (request, response) ->
                                        response.send(request.receive().asString()
                                                .map(App::parseCard)
                                                .map(creditCardService::create)
                                                .map(App::toByteBuf)
                                                .log("http-server")))
                                .get("/submit", (request, response) ->
                                        response.sendFile(indexHTML))
                                .get("/payment", (request, response) ->
                                        response.send(paymentService.getAll()
                                                .map(App::toByteBuf)
                                                .log("http-server")))
                                .get("/payment/{param}", (request, response) ->
                                        response.send(paymentService.get(request.param("param"))
                                                .map(App::toByteBuf)
                                                .log("http-server")))
                                .post("/payed", (request, response) ->
                                        response.send(request.receive().asString()
                                                .map(App::parsePay)
                                                .map(paymentService::create)
                                                .map(App::toByteBuf)
                                                .log("http-server")))
                                .get("/pay", (request, response) ->
                                        response.sendFile(paymentHTML))



                )
                .bindNow();

    }

}
