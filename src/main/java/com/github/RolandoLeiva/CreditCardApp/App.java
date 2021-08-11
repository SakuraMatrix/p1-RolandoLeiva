package com.github.RolandoLeiva.CreditCardApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App
{
    public static void main(String[] args) throws URISyntaxException {
        Logger log = LoggerFactory.getLogger(App.class);
        log.info("Starting App");
        log.info("Exiting App");
    }

}
