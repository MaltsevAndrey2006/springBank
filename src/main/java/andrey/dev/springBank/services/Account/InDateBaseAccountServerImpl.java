package andrey.dev.springBank.services.Account;

import andrey.dev.springBank.model.Account;
import andrey.dev.springBank.repositores.InDateBase.InDateBaseAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Primary
public class InDateBaseAccountServerImpl implements AccountService {

    private InDateBaseAccountRepository inDateBaseAccountRepository;

    @Override
    public Account createAccount(Account account) {
        return inDateBaseAccountRepository.createAccount(account);
    }

    @Override
    public Account withdrawalFromAccount(int accountId, int count) {
        return inDateBaseAccountRepository.withdrawalFromAccount(accountId, count);
    }

    @Override
    public Account replenishmentOnAccount(int accountId, int count) {
        return inDateBaseAccountRepository.replenishmentOnAccount(accountId, count);
    }

    @Override
    public List<Account> transfer(int sourceId, int applyId, int count) {
        return inDateBaseAccountRepository.transfer(sourceId, applyId, count);
    }

    @Override
    public void deleteAccount(int accountId) {
        inDateBaseAccountRepository.deleteAccount(accountId);
    }
}
