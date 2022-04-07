package com.bank;

import com.bank.account.AccountStatement;
import com.bank.account.AccountStatementOperation;
import com.bank.account.AccountStatementWithBalance;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BankRepositoryUnitTest {
    private BankRepository bankRepository = new BankRepository();

    @Test
    public void shouldAddOperations() {
        String clientId = "clientId";
        AccountStatement accountStatement = new AccountStatement(125.5, LocalDate.now());

        bankRepository.addOperation(clientId, accountStatement);

        assertThat(bankRepository.getAccounts().get(clientId)).isNotEmpty();
        assertThat(bankRepository.getAccounts().get(clientId)).containsExactly(accountStatement);
    }

    @Test
    public void shouldGetOperationHistoryWithBalance() {
        String clientId = "newClientId";
        AccountStatement firstOperation = new AccountStatement(110, LocalDate.now());
        bankRepository.addOperation(clientId, firstOperation);

        AccountStatement secondOperation = new AccountStatement(-100, LocalDate.now().plusDays(1));
        bankRepository.addOperation(clientId, secondOperation);

        List<AccountStatementWithBalance> operationHistory = bankRepository.getOperationHistory(clientId);

        assertThat(operationHistory).isNotEmpty();

        assertThat(operationHistory.get(0).getAmount()).isEqualTo(firstOperation.getAmount());
        assertThat(operationHistory.get(0).getDate()).isEqualTo(firstOperation.getDate());
        assertThat(operationHistory.get(0).getAccountStatementOperation()).isEqualTo(AccountStatementOperation.DEPOSIT);
        assertThat(operationHistory.get(0).getBalance()).isEqualTo(firstOperation.getAmount());

        assertThat(operationHistory.get(1).getAmount()).isEqualTo(secondOperation.getAmount());
        assertThat(operationHistory.get(1).getDate()).isEqualTo(secondOperation.getDate());
        assertThat(operationHistory.get(1).getAccountStatementOperation()).isEqualTo(AccountStatementOperation.WITHDRAWAL);
        assertThat(operationHistory.get(1).getBalance()).isEqualTo(firstOperation.getAmount() + secondOperation.getAmount());
    }
}
