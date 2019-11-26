package ltd.webbiskools.quizmgr.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class QuizInfoRetriever extends DatabaseAccessor {

    public List<String> getAllQuizTitles() {
        List<String> list = new ArrayList<>();
        ResultSet results = selectFrom(QUIZ_TABLE_NAME, "title");
        if (results == null) {
            // TODO
        } else {
            try {
                while (results.next()) {
                    list.add(results.getString(1));
                }
            } catch (SQLException ex) {
                System.out.println("Exception encountered while trying to add results to list: " + ex);
            }
        }
        return list;
    }

}
