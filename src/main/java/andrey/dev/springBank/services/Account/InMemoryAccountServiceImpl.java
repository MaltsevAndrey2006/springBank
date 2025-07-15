package andrey.dev.springBank.services.Account;

import andrey.dev.springBank.model.Account;
import andrey.dev.springBank.model.User;
import andrey.dev.springBank.repositores.InMemoryAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class InMemoryAccountServiceImpl implements AccountService {
    private InMemoryAccountRepository inMemoryAccountRepository;
    private RestTemplate restTemplate;
    private static final String USER_SERVICE_URL = "http://localhost:8080/api/v1/Users";

    @Override
    public Account createAccount(Account account) {
        if (restTemplate.getForObject(USER_SERVICE_URL + "/" + account.getUserId(), User.class) != null) {
            inMemoryAccountRepository.addToAccountList(account);
            restTemplate.getForObject(USER_SERVICE_URL + "/" + account.getUserId(), User.class).addToAccountList(account);
            return account;
        }
        return null;
    }

    @Override
    public Account withdrawalFromAccount(int accountId, int count) {
        if (inMemoryAccountRepository.findAccountById(accountId) != null && inMemoryAccountRepository.findAccountById(accountId).getMoneyAmount() >= count) {
            Account account = inMemoryAccountRepository.findAccountById(accountId);
            account.setMoneyAmount(account.getMoneyAmount() - count);
            return account;
        }
        return null;
    }

    @Override
    public Account replenishmentOnAccount(int accountId, int count) {
        if (inMemoryAccountRepository.findAccountById(accountId) != null) {
            Account account = inMemoryAccountRepository.findAccountById(accountId);
            account.setMoneyAmount(account.getMoneyAmount() + count);
            return account;
        }
        return null;
    }

    @Override
    public List<Account> transfer(int sourceId, int applyId, int count) {
        if (inMemoryAccountRepository.findAccountById(sourceId) != null && inMemoryAccountRepository.findAccountById(applyId) != null && inMemoryAccountRepository.findAccountById(sourceId).getMoneyAmount() >= count) {
            inMemoryAccountRepository.findAccountById(sourceId).setMoneyAmount(inMemoryAccountRepository.findAccountById(sourceId).getMoneyAmount() - count);
            inMemoryAccountRepository.findAccountById(applyId).setMoneyAmount(inMemoryAccountRepository.findAccountById(applyId).getMoneyAmount() + count);
            return List.of(inMemoryAccountRepository.findAccountById(sourceId), inMemoryAccountRepository.findAccountById(applyId));
        }
        return null;
    }

    @Override
    public void deleteAccount(int accountId) {
        if (inMemoryAccountRepository.findAccountById(accountId) != null) {
            restTemplate.getForObject(USER_SERVICE_URL + "/" + inMemoryAccountRepository.findAccountById(accountId).getUserId(), User.class).deleteFromAccountList(inMemoryAccountRepository.findAccountById(accountId));
            inMemoryAccountRepository.deleteAccountFromAccountList(accountId);
        }
    }
}
