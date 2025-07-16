package andrey.dev.springBank.services;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;

import static andrey.dev.springBank.Constants.*;

@Service
public class ConnectionService {

    public Connection getConnectionToUserRepository(String DataBaseName, String user, String password) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(String.format(URL_FOR_CONNECTION, DataBaseName), user, password);
            System.out.printf((MESSAGE_OF_SUCCESS_CONNECTION), DataBaseName);
        } catch (Exception e) {
            System.out.printf(MESSAGE_OF_ERROR_TO_CONNECTION, DataBaseName);
            System.out.println(e);
        }
        return connection;
    }
}
