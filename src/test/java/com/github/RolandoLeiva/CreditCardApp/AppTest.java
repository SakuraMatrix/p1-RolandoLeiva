package com.github.RolandoLeiva.CreditCardApp;

import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.Assert;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactor.netty.DisposableServer;

@SpringJUnitConfig(AppConfig.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppTest {
    @Autowired
    ApplicationContext context;

    DisposableServer server;

    WebTestClient rest;

    @BeforeAll
    public void setup()
    {
        this.server = context.getBean(DisposableServer.class);
    }
    @Test
    public void httpPort ()
    {
        this.server.onDispose();
        Assertions.assertEquals(this.server.port() , 8080);

    }

    @Test
    public void httpPortWithAddress()
    {
        Assertions.assertEquals(this.server.address().toString(),"/0:0:0:0:0:0:0:0:8080");
    }


}
