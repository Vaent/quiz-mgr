package ltd.webbiskools.quizmgr.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.stereotype.Component;

@Component
public class QuizRecordEditor extends DatabaseAccessor {

    public boolean createAnswer(int questionId, String answerText) {
        String sql = "SELECT fn_create_answer(" + questionId + ",'" + answerText + "')";
        ResultSet results = execute(sql);
        try {
            results.next();
            boolean result = results.getBoolean("fn_create_answer");
            if (results.next()) {
                throw new SQLException("Only one result should be returned by fn_create_answer");
            }
            return result;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }

}
