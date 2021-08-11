package com.github.RolandoLeiva.CreditCardApp;

public class CreditCard {
    private String cardNumber;
    private String cvv;
    private String expDate;
    private String type;

    public CreditCard() {
    }

    public CreditCard(String cardNumber, String cvv, String expDate, String type) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expDate = expDate;
        this.type = type;
    }

    public CreditCard applyVisa()
    {
        System.out.println("Congratulations. You have been accepted for a Visa credit card");
        String number = "4485 0854 3388 1882";
        String ccv = "570";
        String exp = "09-2025";
        String t = "Visa";

        return new CreditCard(number,ccv,exp,t);
    }

    public CreditCard applyMasterCard()
    {
        System.out.println("Congratulations. You have been accepted for a MasterCard credit card");
        String number = "5371 0275 9728 5736";
        String ccv = "976";
        String exp = "08-2023";
        String t = "MasterCard";
        return new CreditCard(number,ccv,exp,t);
    }
    public CreditCard applyAmericanExpress()
    {
        System.out.println("Congratulations. You have been accepted for a American Express credit card");
        String number = "372223048613796";
        String ccv = "371";
        String exp = "08-2023";
        String t = "American Express";
        return new CreditCard(number,ccv,exp,t);
    }
    public CreditCard applyDiscover()
    {
        System.out.println("Congratulations. You have been accepted for a Discover credit card");
        String number = "6011 0726 6472 0631";
        String ccv = "164";
        String exp = "08-2023";
        String t = "Discover";
        return new CreditCard(number,ccv,exp,t);
    }

}
