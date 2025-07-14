package andrey.dev.springBank.services.Account;

import andrey.dev.springBank.model.Account;

import java.util.List;

public interface AccountService {
    Account createAccount(Account account);

    Account withdrawalFromAccount(int accountId, int count);

    Account replenishmentOnAccount(int accountId, int count);

    List<Account> transfer(int sourceId, int applyId, int count);

    void deleteAccount(int accountId);
}
