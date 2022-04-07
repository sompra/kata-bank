package com.bank.account;

public class AccountStatementWithBalance extends AccountStatement {

    private double balance;
    private AccountStatementOperation accountStatementOperation;

    public AccountStatementWithBalance(AccountStatement accountStatement, double balance) {
        super(accountStatement.getAmount(), accountStatement.getDate());
        this.balance = balance;
        this.accountStatementOperation = accountStatement.getAmount() > 0 ? AccountStatementOperation.DEPOSIT : AccountStatementOperation.WITHDRAWAL;
    }

    public double getBalance() {
        return balance;
    }

    public AccountStatementOperation getAccountStatementOperation() {
        return accountStatementOperation;
    }
}
