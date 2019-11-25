package ltd.webbiskools.quizmgr.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class UserCredentialChecker {

    @Value("${dbUrl}")
    private String dbUrl;

    @Value("${dbUserName}")
    private String dbUserName;

    @Value("${dbPassword}")
    private String dbPassword;

    public boolean check(String userName, String password) {
        System.out.println(dbUrl+"~"+dbUserName+"~"+dbPassword);
        try {
            Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
        return true;
    }

}
