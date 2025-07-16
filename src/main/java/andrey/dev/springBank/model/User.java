package andrey.dev.springBank.model;

import lombok.Builder;
import lombok.Data;

import javax.annotation.processing.Generated;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class User {
    private int userId;
    private String userLogin;
    @Generated("Lombok")
    private List<Account> accountList = new ArrayList<>();

    public void addToAccountList(Account account) {
        accountList.add(account);
    }

    public void deleteFromAccountList(Account account) {
        accountList.remove(account);
    }
}
