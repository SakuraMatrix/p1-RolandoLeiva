package com.github.RolandoLeiva.CreditCardApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServer;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App
{
    public static void main(String[] args) throws URISyntaxException {
        Path errorHTML = Paths.get(App.class.getResource("/errorHTML.html").toURI());
        Logger log = LoggerFactory.getLogger(App.class);
        log.info("Starting App");
        HttpServer.create()
                .port(8080)
                .route(routes ->
                        routes.get("/items", (request, response) ->
                                response.sendString(Mono.just("Hello World!")
                                        .log("http-server")))
                                .get("/items/{param}", (request, response) ->
                                        response.sendString(Mono.just(request.param("param"))
                                                .log("http-server")))
                                .get("/error", (request, response) ->
                                        response.status(404).addHeader("Message", "Goofed")
                                                .sendFile(errorHTML))
                )
                .bindNow()
                .onDispose()
                .block();
        log.info("Exiting App");
    }

}
