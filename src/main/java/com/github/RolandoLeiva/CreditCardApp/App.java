package com.github.RolandoLeiva.CreditCardApp;

import com.datastax.oss.driver.api.core.CqlSession;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.RolandoLeiva.CreditCardApp.domain.CreditCard;
import com.github.RolandoLeiva.CreditCardApp.repository.CQLConnect;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import reactor.netty.DisposableServer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;


public class App
{
    static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static void main(String[] args) throws URISyntaxException {
        Logger log = LoggerFactory.getLogger(App.class);
        log.info("Starting App");
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        applicationContext.getBean(DisposableServer.class).onDispose().block();
        //CreditCard c = new CreditCard();
       // CqlSession cqlSession = CqlSession.builder().build();
       // CQLConnect con = new CQLConnect(cqlSession);
        //c= c.applyMasterCard();
        //System.out.println(c.getCardNumber());
        //System.out.println(c.getType());
       // System.out.println(c.getCvv());
       // System.out.println(c.getExpDate());
       // con.insert(c);
        log.info("Exiting App");
    }
    static ByteBuf toByteBuf(Object o) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            OBJECT_MAPPER.writeValue(out, o);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return ByteBufAllocator.DEFAULT.buffer().writeBytes(out.toByteArray());
    }
    static CreditCard parseItem(String str) {
        CreditCard card = null;
        try {
            card = OBJECT_MAPPER.readValue(str, CreditCard.class);
        } catch (JsonProcessingException ex) {
            String[] params = str.split("&");
            String number = params[0].split("=")[1];
            String cvv = params[1].split("=")[1];
            String exp = params[2].split("=")[1];
            String type = params[3].split("=")[1];
            card = new CreditCard(number, cvv, exp,type);
        }
        return card;
    }
}
