package andrey.dev.springBank.model;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Account {
    private int accountId;
    private int userId;
    private int moneyAmount;
}
