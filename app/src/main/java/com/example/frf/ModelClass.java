package com.example.frf;

public class ModelClass{




    public String date, to, from, purpose, kms, advance, balance;

    ModelClass() {

    }


    public ModelClass(String date, String to, String from, String purpose, String kms, String advance, String balance) {
        this.date = date;
        this.to = to;
        this.from = from;
        this.purpose = purpose;
        this.kms = kms;
        this.advance = advance;
        this.balance = balance;

    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getKms() {
        return kms;
    }

    public void setKms(String kms) {
        this.kms = kms;
    }

    public String getAdvance() {
        return advance;
    }

    public void setAdvance(String advance) {
        this.advance = advance;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}



