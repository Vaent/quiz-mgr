package ltd.webbiskools.quizmgr.model.dbmappings;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Question {

    private int id;
    private int quizId;
    private int questionIndex;
    private String questionText;

    public Question(int id, int quizId, int questionIndex, String questionText) {
        this.id = id;
        this.quizId = quizId;
        this.questionIndex = questionIndex;
        this.questionText = questionText;
    }

    public int getId() {
        return id;
    }

    public int getQuizId() {
        return quizId;
    }

    public int getQuestionIndex() {
        return questionIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public static List<Question> listFrom(ResultSet resultSet) {
        List<Question> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                list.add(new Question(
                    resultSet.getInt("id"),
                    resultSet.getInt("quiz_id"),
                    resultSet.getInt("question_index"),
                    resultSet.getString("question_text")
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Exception encountered while trying to add results to list: " + ex);
        }
        return list;
    }

}
