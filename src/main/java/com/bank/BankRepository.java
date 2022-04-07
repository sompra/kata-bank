package com.bank;

import com.bank.account.AccountStatement;
import com.bank.account.AccountStatementWithBalance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BankRepository {

    private Map<String, List<AccountStatement>> accounts = new HashMap<>();

    public void addOperation(String clientId, AccountStatement accountStatement) {
        List<AccountStatement> accountStatements = Optional.ofNullable(accounts.get(clientId)).orElse(new ArrayList<>());
        accountStatements.add(accountStatement);
        accounts.put(clientId, accountStatements);
    }

    public List<AccountStatementWithBalance> getOperationHistory(String clientId) {
        List<AccountStatement> accountStatements = accounts.get(clientId);

        double balance = 0;
        List<AccountStatementWithBalance> accountStatementWithBalances = new ArrayList<>();
        for (AccountStatement statement : accountStatements) {
            balance += statement.getAmount();
            accountStatementWithBalances.add(new AccountStatementWithBalance(statement, balance));
        }

        return accountStatementWithBalances;
    }

    public Map<String, List<AccountStatement>> getAccounts() {
        return accounts;
    }
}
