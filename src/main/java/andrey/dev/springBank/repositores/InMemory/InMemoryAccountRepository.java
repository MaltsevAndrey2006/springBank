package andrey.dev.springBank.repositores.InMemory;

import andrey.dev.springBank.model.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryAccountRepository {
    private List<Account> accountList = new ArrayList<>();

    public void addToAccountList(Account account) {
        accountList.add(account);
    }

    public Account findAccountById(int accountId) {
        return accountList.stream()
                .filter(e -> e.getAccountId() == accountId).findFirst().orElse(null);
    }

    public void deleteAccountFromAccountList(int accountId) {
        Account account = accountList.stream().filter(e -> e.getAccountId() == accountId).findFirst().orElse(null);
        if (account != null) {
            accountList.remove(account);
        }
    }

}
