package andrey.dev.springBank;

public class Constants {
    //connection
    public static final String DATE_BASE_NAME = "BankDb";
    public static final String USER = "postgres";
    public static final String PASSWORD = "13579qwerzxc";
    public static final String URL_FOR_CONNECTION = "jdbc:postgresql://localhost:5432/%s";

    //Messages of states
    public static final String MESSAGE_OF_SUCCESS_CONNECTION = "Success to connection to dateBase: %s'\n'";
    public static final String MESSAGE_OF_ERROR_TO_CONNECTION = "No connection to dateBase :%s'\n'";
    public static final String MESSAGE_OF_ERROR_TO_INSERT_IN_DATE_BASE = "Error of inserting";

    //UserRepository
    public static final String NAME_OF_USER_TABLE = "user_table";
    public static final String FORM_FOR_CHECK_LOGIN = "SELECT user_login FROM %s;";
    public static final String USER_LOGIN_STRING = "user_login";
    public static final String FORM_FOR_INSERT_COMMAND_FOR_USER = "INSERT INTO %s(%s) values('%s')";
    public static final String FORM_ROR_SEARCH_BY_ID_COMMAND_FOR_USER = "SELECT %s.id AS user_id , user_login , %s.id AS account_id , user_id , money_amount FROM %s LEFT JOIN  %s ON %s.id = %s.user_id  WHERE %s.id = %d";
    public static final String ID_STRING = "id";
    public static final String MESSAGE_OF_ERROR_OF_SEARCHING = "Error of finding";
    public static final String USER_ID_STRING = "user_id";
    public static final String ACCOUNT_ID_STRING = "account_id";
    public static final String COMMAND_FOR_FIND_ALL_USERS = "SELECT id FROM user_table ";
    public static final String FORM_COMMAND_FOR_DELETE_USER = "DELETE FROM user_table WHERE id = %s";
    public static final String MESSAGE_OF_ERROR_TO_DELETE_USER = "Error to delete ";

    //AccountRepository
    public static final String NAME_OF_ACCOUNT_TABLE = "account_table";
    public static final String MONEY_AMOUNT_STRING = "money_amount";
    public static final String COMMAND_TO_CHECK_USERS = "SELECT id FROM user_table";
    public static final String FORM_FOR_COMMAND_TO_INNER_ACCOUNT = "INSERT INTO account_table(user_id , money_amount) VALUES (%d , %d)";
    public static final String ERROR_MESSAGE_OF_CREATING_ACCOUNT = "Error of creating account ";
    public static final String FORM_FOR_COMMAND_TO_SELECT_ALL_FROM_ACCOUNT_BY_ID = "SELECT * FROM account_table WHERE id = %d";
    public static final String FORM_FOR_COMMAND_TO_WITHDRAWAL_FROM_ACCOUNT = "UPDATE account_table SET money_amount = money_amount - %d WHERE id = %d";
    public static final String MESSAGE_OF_ERROR_TO_WITHDRAWAL_FROM_ACCOUNT = "Error of withdrawal ";
    public static final String FORM_FOR_COMMAND_TO_REPLENISHMENT_TO_ACCOUNT = "UPDATE account_table SET money_amount = money_amount + %d WHERE id = %d";
    public static final String ERROR_MESSAGE_OF_REPLENISHMENT_TO_ACCOUNT = "Error of replenishment ";
    public static final String ERROR_MESSAGE_OF_TRANSFER_FROM_ACCOUNT_TO_ACCOUNT = "transfer went wrong";
    public static final String FORM_FOR_COMMAND_TO_DELETE_ACCOUNT_BY_ID = "DELETE FROM account_table WHERE id = %s";
    public static final String ERROR_MESSAGE_OF_DELETING_ACCOUNT = "Error of deleting account";
}
