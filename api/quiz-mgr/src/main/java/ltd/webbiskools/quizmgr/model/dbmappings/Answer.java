package ltd.webbiskools.quizmgr.model.dbmappings;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Answer {

    private int id;
    private int questionId;
    private char answerIndex;
    private String answerText;

    public Answer(int id, int questionId, char answerIndex, String answerText) {
        this.id = id;
        this.questionId = questionId;
        this.answerIndex = answerIndex;
        this.answerText = answerText;
    }

    public int getId() {
        return id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public char getAnswerIndex() {
        return answerIndex;
    }

    public String getAnswerText() {
        return answerText;
    }

    public static List<Answer> listFrom(ResultSet resultSet) {
        List<Answer> list = new ArrayList<>();
        try {
            while (resultSet.next()) {
                list.add(new Answer(
                    resultSet.getInt("id"),
                    resultSet.getInt("question_id"),
                    resultSet.getString("answer_index").charAt(0),
                    resultSet.getString("answer_text")
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Exception encountered while trying to add results to list: " + ex);
        }
        return list;
    }

}
