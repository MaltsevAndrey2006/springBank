package andrey.dev.springBank.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class User {
    private int userId;
    private String userLogin;
    private List<Account> accountList;

}
