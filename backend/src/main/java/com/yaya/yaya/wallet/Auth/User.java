package com.yaya.yaya.wallet.Auth;

public class User {
    private String query;
   private String Transaction_ID;
    private String Sender;
      private String Receiver;
   private Float Amount;
           private String Currency;
    private  String Cause;
   private String  Created_At;

    public User() {
    }

    public User(String query, String transaction_ID, String sender, String receiver, Float amount, String currency, String cause, String created_At) {
        this.query = query;
        Transaction_ID = transaction_ID;
        Sender = sender;
        Receiver = receiver;
        Amount = amount;
        Currency = currency;
        Cause = cause;
        Created_At = created_At;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getTransaction_ID() {
        return Transaction_ID;
    }

    public void setTransaction_ID(String transaction_ID) {
        Transaction_ID = transaction_ID;
    }

    public String getSender() {
        return Sender;
    }

    public void setSender(String sender) {
        Sender = sender;
    }

    public String getReceiver() {
        return Receiver;
    }

    public void setReceiver(String receiver) {
        Receiver = receiver;
    }

    public Float getAmount() {
        return Amount;
    }

    public void setAmount(Float amount) {
        Amount = amount;
    }

    public String getCurrency() {
        return Currency;
    }

    public void setCurrency(String currency) {
        Currency = currency;
    }

    public String getCause() {
        return Cause;
    }

    public void setCause(String cause) {
        Cause = cause;
    }

    public String getCreated_At() {
        return Created_At;
    }

    public void setCreated_At(String created_At) {
        Created_At = created_At;
    }

    @Override
    public String toString() {
        return "User{" +
                "query='" + query + '\'' +
                '}';
    }

}
