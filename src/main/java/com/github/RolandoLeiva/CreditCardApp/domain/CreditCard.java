package com.github.RolandoLeiva.CreditCardApp.domain;

public class CreditCard  {
    private String cardNumber;
    private String cvv;
    private String expDate;
    private String type;

    public CreditCard()
    {
        super();
    }

    public CreditCard(String cardNumber, String cvv, String expDate, String type) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expDate = expDate;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Credit card{" +
                "number=" + cardNumber +
                ", cvv='" + cvv + '\'' +
                ", expiration Date=" + expDate +
                ", Type=" + type +
                '}';
    }

    public String getCardNumber() {
        return cardNumber;
    }


    public String getCvv() {
        return cvv;
    }


    public String getExpDate() {
        return expDate;
    }


    public String getType() {
        return type;
    }


}
