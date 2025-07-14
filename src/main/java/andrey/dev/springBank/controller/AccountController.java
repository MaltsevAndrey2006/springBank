package andrey.dev.springBank.controller;


import andrey.dev.springBank.config.ApplicationConfig;
import andrey.dev.springBank.model.Account;
import andrey.dev.springBank.services.Account.InMemoryAccountServiceImpl;
import andrey.dev.springBank.services.TransferService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Accounts")
@AllArgsConstructor
public class AccountController {
    private InMemoryAccountServiceImpl inMemoryAccountService;

    @PostMapping("Create_Account")
    Account createAccount(@RequestBody Account account) {
        return inMemoryAccountService.createAccount(account);
    }

    @PutMapping("Withdrawal")
    public Account withdrawalFromAccount(@RequestBody TransferService transferService) {
        return inMemoryAccountService.withdrawalFromAccount(transferService.getSource(), transferService.getCount());
    }

    @PutMapping("Replenishment")
    public Account replenishmentOnAccount(@RequestBody TransferService transferService) {
        return inMemoryAccountService.replenishmentOnAccount(transferService.getSource(), transferService.getCount());
    }

    @PutMapping("Transfer")
    public List<Account> transfer(@RequestBody TransferService transferService) {
        return inMemoryAccountService.transfer(transferService.getSource(), transferService.getApply(), transferService.getCount());
    }

    @DeleteMapping("Delete/{accountId}")
    void deleteAccount(@PathVariable int accountId) {
        inMemoryAccountService.deleteAccount(accountId);
    }

    ;

}
