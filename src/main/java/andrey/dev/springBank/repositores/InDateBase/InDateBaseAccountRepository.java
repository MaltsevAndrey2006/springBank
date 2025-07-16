package andrey.dev.springBank.repositores.InDateBase;

import andrey.dev.springBank.model.Account;
import andrey.dev.springBank.services.ConnectionService;
import org.springframework.stereotype.Repository;

import java.security.interfaces.EdECKey;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static andrey.dev.springBank.Constants.*;

@Repository
public class InDateBaseAccountRepository {

    private ConnectionService connectionService;
    private Connection connection;

    public InDateBaseAccountRepository(ConnectionService connectionService) {
        this.connectionService = connectionService;
        this.connection = connectionService.getConnectionToUserRepository(DATE_BASE_NAME, USER, PASSWORD);
    }

    public Account createAccount(Account account) {
        Statement statementToInsert, statementToCheck;
        ResultSet resultSet;
        try {
            statementToInsert = connection.createStatement();
            statementToCheck = connection.createStatement();
            resultSet = statementToCheck.executeQuery(COMMAND_TO_CHECK_USERS);
            while (resultSet.next()) {
                if (resultSet.getInt(ID_STRING) == account.getUserId()) {
                    statementToInsert.executeUpdate(String.format(FORM_FOR_COMMAND_TO_INNER_ACCOUNT, account.getUserId(), account.getMoneyAmount()));
                    return account;
                }
            }
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE_OF_CREATING_ACCOUNT);
        }
        return null;
    }

    public Account withdrawalFromAccount(int accountId, int count) {
        Statement statementForUpdate, statementForReturn, statementForCheck;
        ResultSet resultSet, forCheck;
        try {
            statementForUpdate = connection.createStatement();
            statementForReturn = connection.createStatement();
            statementForCheck = connection.createStatement();
            forCheck = statementForCheck.executeQuery(String.format(FORM_FOR_COMMAND_TO_SELECT_ALL_FROM_ACCOUNT_BY_ID, accountId));
            if (forCheck.next() && forCheck.getInt(MONEY_AMOUNT_STRING) >= count) {
                statementForUpdate.executeUpdate(String.format(FORM_FOR_COMMAND_TO_WITHDRAWAL_FROM_ACCOUNT, count, accountId));
                resultSet = statementForReturn.executeQuery(String.format(FORM_FOR_COMMAND_TO_SELECT_ALL_FROM_ACCOUNT_BY_ID, accountId));
                if (resultSet.next()) {
                    return Account.builder().accountId(accountId).moneyAmount(resultSet.getInt(MONEY_AMOUNT_STRING)).userId(resultSet.getInt(USER_ID_STRING)).build();
                }
            }
        } catch (Exception e) {
            System.out.println(MESSAGE_OF_ERROR_TO_WITHDRAWAL_FROM_ACCOUNT);
        }
        return null;
    }

    public Account replenishmentOnAccount(int accountId, int count) {
        Statement statementForUpdate, statementForReturn, statementForCheck;
        ResultSet resultSet, forCheck;
        try {
            statementForUpdate = connection.createStatement();
            statementForReturn = connection.createStatement();
            statementForCheck = connection.createStatement();
            forCheck = statementForCheck.executeQuery(String.format(FORM_FOR_COMMAND_TO_SELECT_ALL_FROM_ACCOUNT_BY_ID, accountId));
            if (forCheck.next()) {
                statementForUpdate.executeUpdate(String.format(FORM_FOR_COMMAND_TO_REPLENISHMENT_TO_ACCOUNT, count, accountId));
                resultSet = statementForReturn.executeQuery(String.format(FORM_FOR_COMMAND_TO_SELECT_ALL_FROM_ACCOUNT_BY_ID, accountId));
                if (resultSet.next()) {
                    return Account.builder().accountId(accountId).moneyAmount(resultSet.getInt(MONEY_AMOUNT_STRING)).userId(resultSet.getInt(USER_ID_STRING)).build();
                }
            }
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE_OF_REPLENISHMENT_TO_ACCOUNT);
        }
        return null;
    }

    public List<Account> transfer(int sourceId, int applyId, int count) {
        Statement statement, statementForApply;
        ResultSet resultSet, resultSetForApply;
        List<Account> accountList = new ArrayList<>();
        try {
            statement = connection.createStatement();
            statementForApply = connection.createStatement();
            resultSetForApply = statementForApply.executeQuery(String.format(FORM_FOR_COMMAND_TO_SELECT_ALL_FROM_ACCOUNT_BY_ID, applyId));
            resultSet = statement.executeQuery(String.format(FORM_FOR_COMMAND_TO_SELECT_ALL_FROM_ACCOUNT_BY_ID, sourceId));
            if (resultSet.next() && resultSet.getInt(MONEY_AMOUNT_STRING) >= count && resultSetForApply.next()) {
                accountList.add(withdrawalFromAccount(sourceId, count));
                accountList.add(replenishmentOnAccount(applyId, count));
                return accountList;
            }
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE_OF_TRANSFER_FROM_ACCOUNT_TO_ACCOUNT);
        }
        return null;
    }

    public void deleteAccount(int accountId) {
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(String.format(FORM_FOR_COMMAND_TO_DELETE_ACCOUNT_BY_ID, accountId));
        } catch (Exception e) {
            System.out.println(ERROR_MESSAGE_OF_DELETING_ACCOUNT);
        }
    }


}
