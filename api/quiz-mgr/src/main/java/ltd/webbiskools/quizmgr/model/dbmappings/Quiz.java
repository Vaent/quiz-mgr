package ltd.webbiskools.quizmgr.model.dbmappings;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Quiz {

    private int id;
    private String title;

    public Quiz(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public static List<Quiz> listFrom(ResultSet resultSet) {
        List<Quiz> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                list.add(new Quiz(
                    resultSet.getInt("id"),
                    resultSet.getString("title")
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Exception encountered while trying to add results to list: " + ex);
        }
        return list;
    }

}
