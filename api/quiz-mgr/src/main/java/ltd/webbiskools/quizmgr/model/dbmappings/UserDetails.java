package ltd.webbiskools.quizmgr.model.dbmappings;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDetails {

    private String userName;
    private String password;
    private int permission;

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getPermission() {
        return permission;
    }

    public UserDetails(String userName, String password, int permission) {
        this.userName = userName;
        this.password = password;
        this.permission = permission;
    }

    public static List<UserDetails> listFrom(ResultSet resultSet) {
        List<UserDetails> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                list.add(new UserDetails(
                    resultSet.getString("user_name"),
                    resultSet.getString("user_password"),
                    resultSet.getInt("user_permission")
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Exception encountered while trying to add results to list: " + ex);
        }
        return list;
    }

}
