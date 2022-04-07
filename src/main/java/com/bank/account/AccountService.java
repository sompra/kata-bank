package com.bank.account;

import com.bank.BankRepository;

import java.time.LocalDate;
import java.util.List;

public class AccountService {

    private BankRepository bankRepository;

    public AccountService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public void saveMoney(String clientId, double amount) {
        bankRepository.addOperation(clientId, new AccountStatement(amount, LocalDate.now()));
    }

    public void retrieveMoney(String clientId, double amount) {
        bankRepository.addOperation(clientId, new AccountStatement(amount, LocalDate.now()));
    }

    public List<AccountStatementWithBalance> getAccountHistory(String clientId) {
        return bankRepository.getOperationHistory(clientId);
    }
}
