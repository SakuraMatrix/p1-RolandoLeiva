package com.github.RolandoLeiva.CreditCardApp;

import com.datastax.oss.driver.api.core.CqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;


public class App
{
    public static void main(String[] args) throws URISyntaxException {
        Logger log = LoggerFactory.getLogger(App.class);
        log.info("Starting App");
        CreditCard c = new CreditCard();
        CqlSession cqlSession = CqlSession.builder().build();
        CQLConnect con = new CQLConnect(cqlSession);
        //con.createKeyspace();
        //con.createTable();
        c= c.applyMasterCard();
        System.out.println(c.getCardNumber());
        System.out.println(c.getType());
        System.out.println(c.getCvv());
        System.out.println(c.getExpDate());
        con.insert(c);
        log.info("Exiting App");
    }

}
