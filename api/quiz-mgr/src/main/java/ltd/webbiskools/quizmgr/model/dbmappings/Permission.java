package ltd.webbiskools.quizmgr.model.dbmappings;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Permission {
    
    private int id;
    private String description;

    public Permission(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static List<Permission> listFrom(ResultSet resultSet) {
        List<Permission> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                list.add(new Permission(
                    resultSet.getInt("id"),
                    resultSet.getString("description")
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Exception encountered while trying to add results to list: " + ex);
        }
        return list;
    }

    public static Map<Integer, String> mapDetailsFrom(ResultSet resultSet) {
        List<Permission> permissions = listFrom(resultSet);
        return mapDetailsFrom(permissions);
    }

    private static Map<Integer, String> mapDetailsFrom(List<Permission> permissions) {
        return permissions.stream()
            .collect(Collectors.toMap(
                Permission::getId,
                Permission::getDescription
            ));
    }

}
