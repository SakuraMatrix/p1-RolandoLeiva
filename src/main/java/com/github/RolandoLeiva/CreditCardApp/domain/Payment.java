package com.github.RolandoLeiva.CreditCardApp.domain;

public class Payment {
    private String user;
    private String card_num;
    private String min_payment;
    private String payment_amount;

    public Payment(String user, String card_num, String min_payment, String payment_amount) {
        this.user = user;
        this.card_num = card_num;
        this.min_payment = min_payment;
        this.payment_amount = payment_amount;
    }
    @Override
    public String toString() {
        return "Item{" +
                "user=" + user +
                ", card number='" + card_num + '\'' +
                ", minimum payment=" + min_payment +
                ", payment amount=" + payment_amount +
                '}';
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCard_num() {
        return card_num;
    }

    public void setCard_num(String card_num) {
        this.card_num = card_num;
    }

    public String getMin_payment() {
        return min_payment;
    }

    public void setMin_payment(String min_payment) {
        this.min_payment = min_payment;
    }

    public String getPayment_amount() {
        return payment_amount;
    }

    public void setPayment_amount(String payment_amount) {
        this.payment_amount = payment_amount;
    }
}
