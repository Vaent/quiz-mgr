package ltd.webbiskools.quizmgr.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ltd.webbiskools.quizmgr.model.dbmappings.Quiz;
import org.springframework.stereotype.Component;

@Component
public class QuizInfoRetriever extends DatabaseAccessor {

    public List<Quiz> getAllQuizzes() {
        List<Quiz> list = new ArrayList<>();
        ResultSet results = selectFrom(QUIZ_TABLE_NAME, "id", "title");
        if (results != null) {
            try {
                while (results.next()) {
                    list.add(new Quiz(
                        results.getInt(1),
                        results.getString(2)
                    ));
                }
            } catch (SQLException ex) {
                System.out.println("Exception encountered while trying to add results to list: " + ex);
            }
        }
        return list;
    }

}
