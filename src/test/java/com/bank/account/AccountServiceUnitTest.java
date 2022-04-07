package com.bank.account;


import com.bank.BankRepository;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AccountServiceUnitTest {

    private BankRepository bankRepository = Mockito.mock(BankRepository.class);
    private AccountService accountService = new AccountService(bankRepository);

    @Test
    public void shouldSaveMoneyOnAccount() {
        accountService.saveMoney("firstClient", 1524.50);
        verify(bankRepository).addOperation(anyString(), Mockito.any(AccountStatement.class));
    }

    @Test
    public void shouldRetrieveMoneyOnAccount() {
        accountService.retrieveMoney("firstClient", -1524.50);
        verify(bankRepository).addOperation(anyString(), Mockito.any(AccountStatement.class));
    }

    @Test
    public void shouldGetEmptyOperationsHistory() {
        when(bankRepository.getOperationHistory(anyString())).thenReturn(new ArrayList<>());

        List<AccountStatementWithBalance> accountStatementWithBalances = accountService.getAccountHistory("firstClient");

        assertThat(accountStatementWithBalances).isNotNull();
        assertThat(accountStatementWithBalances).isEmpty();
    }
}
