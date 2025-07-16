package andrey.dev.springBank.controller;


import andrey.dev.springBank.model.Account;
import andrey.dev.springBank.services.Account.AccountService;
import andrey.dev.springBank.services.TransferService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Accounts")
@AllArgsConstructor
public class AccountController {
    private AccountService AccountService;

    @PostMapping("Create_Account")
    public Account createAccount(@RequestBody Account account) {
        return AccountService.createAccount(account);
    }

    @PutMapping("Withdrawal")
    public Account withdrawalFromAccount(@RequestBody TransferService transferService) {
        return AccountService.withdrawalFromAccount(transferService.getSource(), transferService.getCount());
    }

    @PutMapping("Replenishment")
    public Account replenishmentOnAccount(@RequestBody TransferService transferService) {
        return AccountService.replenishmentOnAccount(transferService.getSource(), transferService.getCount());
    }

    @PutMapping("Transfer")
    public List<Account> transfer(@RequestBody TransferService transferService) {
        return AccountService.transfer(transferService.getSource(), transferService.getApply(), transferService.getCount());
    }

    @DeleteMapping("Delete/{accountId}")
    void deleteAccount(@PathVariable int accountId) {
        AccountService.deleteAccount(accountId);
    }

    ;

}
