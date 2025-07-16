package andrey.dev.springBank.repositores.InDateBase;

import andrey.dev.springBank.model.Account;
import andrey.dev.springBank.model.User;
import andrey.dev.springBank.services.ConnectionService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static andrey.dev.springBank.Constants.*;


@Repository
public class InDateBaseUserRepository {
    private ConnectionService connectionService;
    private Connection connection;

    public InDateBaseUserRepository(ConnectionService connectionService) {
        this.connectionService = connectionService;
        connection = connectionService.getConnectionToUserRepository(DATE_BASE_NAME, USER, PASSWORD);
    }


    public User createUser(User user) {
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format(FORM_FOR_CHECK_LOGIN, NAME_OF_USER_TABLE));
            while (resultSet.next()) {
                if (resultSet.getString(USER_LOGIN_STRING).equals(user.getUserLogin())) {
                    throw new Exception();
                }
            }
            String query = String.format(FORM_FOR_INSERT_COMMAND_FOR_USER, NAME_OF_USER_TABLE, USER_LOGIN_STRING, user.getUserLogin());
            statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(MESSAGE_OF_ERROR_TO_INSERT_IN_DATE_BASE);
        }
        return user;
    }

    public User findUserByUserId(int userId) {
        Statement statementForUser, statementForAccount;
        ResultSet resultSet = null;
        ResultSet resultSetForAccounts = null;
        User user = User.builder().build();
        List<Account> accountList = new ArrayList<>();
        try {
            statementForUser = connection.createStatement();
            statementForAccount = connection.createStatement();
            resultSet = statementForUser.executeQuery(String.format(FORM_ROR_SEARCH_BY_ID_COMMAND_FOR_USER, NAME_OF_USER_TABLE, NAME_OF_ACCOUNT_TABLE, NAME_OF_USER_TABLE, NAME_OF_ACCOUNT_TABLE, NAME_OF_USER_TABLE, NAME_OF_ACCOUNT_TABLE, NAME_OF_USER_TABLE, userId));
            resultSetForAccounts = statementForAccount.executeQuery(String.format(FORM_ROR_SEARCH_BY_ID_COMMAND_FOR_USER, NAME_OF_USER_TABLE, NAME_OF_ACCOUNT_TABLE, NAME_OF_USER_TABLE, NAME_OF_ACCOUNT_TABLE, NAME_OF_USER_TABLE, NAME_OF_ACCOUNT_TABLE, NAME_OF_USER_TABLE, userId));
            while (resultSetForAccounts.next() && (resultSetForAccounts.getInt(ACCOUNT_ID_STRING) != 0)) {
                accountList.add(Account.builder().userId(userId).accountId(resultSetForAccounts.getInt(ACCOUNT_ID_STRING)).moneyAmount(resultSetForAccounts.getInt(MONEY_AMOUNT_STRING)).build());
            }
            if (resultSet.next()) {
                user.setUserId(resultSet.getInt(USER_ID_STRING));
                user.setUserLogin(resultSet.getString(USER_LOGIN_STRING));
                user.setAccountList(accountList);
                return user;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(MESSAGE_OF_ERROR_OF_SEARCHING);
        }
        return null;
    }

    public List<User> findAllUser() {
        Statement statement;
        ResultSet resultSet = null;
        List<User> userList = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(COMMAND_FOR_FIND_ALL_USERS);
            while (resultSet.next()) {
                userList.add(findUserByUserId(resultSet.getInt(ID_STRING)));
            }
        } catch (Exception e) {
            return null;
        }
        return userList;
    }

    public void deleteUserById(int userId) {
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(String.format(FORM_COMMAND_FOR_DELETE_USER, userId));
        } catch (Exception e) {
            System.out.println(MESSAGE_OF_ERROR_TO_DELETE_USER);
        }
    }
}
