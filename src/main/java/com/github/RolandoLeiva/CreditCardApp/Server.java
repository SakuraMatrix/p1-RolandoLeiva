package com.github.RolandoLeiva.CreditCardApp;

import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServer;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Server {


    public void start() throws URISyntaxException {
        Path errorHTML = Paths.get(App.class.getResource("/errorHTML.html").toURI());
        HttpServer.create()
                .port(8080)
                .route(routes ->
                        routes.get("/cards", (request, response) ->
                                response.sendString(Mono.just("List of Credit Cards you can apply for" +
                                        "\nVisa" +
                                        "\nMasterCard" +
                                        "\nAmerican Express" +
                                        "\nDiscover")
                                        .log("http-server")))
                                .get("/cards/{param}", (request, response) ->
                                        response.sendString(Mono.just(request.param("param"))
                                                .log("http-server")))
                                .get("/error", (request, response) ->
                                        response.status(404).addHeader("Message", "Goofed")
                                                .sendFile(errorHTML))
                )
                .bindNow()
                .onDispose()
                .block();
    }
}
