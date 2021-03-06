package com.github.RolandoLeiva.CreditCardApp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.RolandoLeiva.CreditCardApp.domain.CreditCard;
import com.github.RolandoLeiva.CreditCardApp.domain.Payment;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import reactor.netty.DisposableServer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class App
{
    static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static void main(String[] args)  {
        Logger log = LoggerFactory.getLogger(App.class);
        log.info("Starting App");
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        applicationContext.getBean(DisposableServer.class)
                .onDispose()
                .block();
        log.info("Exiting App");
    }
    static ByteBuf toByteBuf(Object o) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            System.out.println("Inside try of toByteBuff");
            OBJECT_MAPPER.writeValue(out, o);
        } catch (IOException ex) {
            System.out.println("Error in toByteBuff");
            ex.printStackTrace();
        }
        return ByteBufAllocator.DEFAULT.buffer().writeBytes(out.toByteArray());
    }
    static CreditCard parseCard(String str) {
        CreditCard card;
        try {
            System.out.println("inside Parse Card try");
            System.out.println(str);
            System.out.println(CreditCard.class);
            card = OBJECT_MAPPER.readValue(str, CreditCard.class);
        } catch (JsonProcessingException ex) {
            System.out.println("inside Parse Card catch 1 ");
            String[] params = str.split("&");
            String number = params[0].split("=")[1];
            String cvv = params[1].split("=")[1];
            String exp = params[2].split("=")[1];
            String type = params[3].split("=")[1];
            card = new CreditCard(number, cvv, exp,type);
        }
        return card;
    }
    static Payment parsePay(String str) {
        Payment pay;
        try {
            System.out.println("inside Parse Pay try");
            System.out.println(str);
            System.out.println(Payment.class);
            pay = OBJECT_MAPPER.readValue(str, Payment.class);

        } catch (JsonProcessingException ex) {
            System.out.println("inside Parse Pay catch 1 ");
            System.out.println(str);
            String[] params = str.split("&");
           if(!(params[0].split("=")[0].equals("user")))
            {
                System.out.println("inside Parse Pay if ");
               params = str.split(",");
                String user = params[0].split(":")[1];
                String number = params[1].split(":")[1];
                String min = params[2].split(":")[1];
                String amount = params[3].split(":")[1];
                pay = new Payment(user.substring(1,user.length()-1), number.substring(1,number.length()-1),
                        min.substring(1,min.length()-1),amount.substring(1,amount.length()-2));
            }
            else {
                System.out.println("inside Parse Pay else");
                String user = params[0].split("=")[1];
                String number = params[1].split("=")[1];
                String min = params[2].split("=")[1];
                String amount = params[3].split("=")[1];
                pay = new Payment(user, number, min, amount);
            }
        }
        catch(Exception ex)
        {
            System.out.println("inside Parse Pay catch 2");
            System.out.println(str);
            pay =null;

        }
        return pay;
    }
}
