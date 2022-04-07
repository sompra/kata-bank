package com.bank.account;

import java.time.LocalDate;

public class AccountStatement {
    private double amount;
    private LocalDate date;

    public AccountStatement(double amount, LocalDate date) {
        this.amount = amount;
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }
}
