package ltd.webbiskools.quizmgr.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import ltd.webbiskools.quizmgr.model.dbmappings.Answer;
import ltd.webbiskools.quizmgr.model.dbmappings.Question;
import ltd.webbiskools.quizmgr.model.dbmappings.Quiz;
import org.springframework.stereotype.Component;

@Component
public class QuizInfoRetriever extends DatabaseAccessor {

    /** Returns a list of all quizzes in the database in the order they are returned by the database */
    public List<Quiz> getAllQuizzes() {
        List<Quiz> list = new ArrayList<>();
        ResultSet results = selectFrom(QUIZ_TABLE_NAME, "id", "title");
        if (results != null) {
            list.addAll(Quiz.listFrom(results));
        }
        return list;
    }

    /** Returns a consolidated list of all answers in the database associated with each of the specified questions */
    public List<Answer> getAnswersFor(Question... questions) {
        return Arrays.stream(questions)
            .map(Question::getId)
            .map(this::getAnswersForQuestion)
            .flatMap(List::stream)
            .collect(Collectors.toList());
    }

    /** Returns a list, sorted by index, of all answers in the database matching the questionId. */
    public List<Answer> getAnswersForQuestion(int questionId) {
        List<Answer> list = new ArrayList<>();
        ResultSet results = selectFrom(ANSWER_TABLE_NAME, "id", "question_id", "answer_index", "answer_text");
        if (results != null) {
            list.addAll(Answer.listFrom(results));
        }
        return list.stream()
            .filter(a -> a.getQuestionId() == questionId)
            .sorted(Comparator.comparingInt(Answer::getAnswerIndex))
            .collect(Collectors.toList());
    }

    /** Returns a list of all answers for all questions associated with the quizId */
    public List<Answer> getAnswersForQuiz(int quizId) {
        return getQuestionsForQuiz(quizId).stream()
            .flatMap(q -> getAnswersForQuestion(q.getId()).stream())
            .collect(Collectors.toList());
    }

    /** Returns a list, sorted by index, of all questions in the database matching the quizId. */
    public List<Question> getQuestionsForQuiz(int quizId) {
        List<Question> list = new ArrayList<>();
        ResultSet results = selectFrom(QUESTION_TABLE_NAME, "id", "quiz_id", "question_index", "question_text");
        if (results != null) {
            list.addAll(Question.listFrom(results));
        }
        return list.stream()
            .filter(q -> q.getQuizId() == quizId)
            .sorted(Comparator.comparingInt(Question::getQuestionIndex))
            .collect(Collectors.toList());
    }

    /** Returns the title associated with the quizId in the database; or if none exists, returns an empty string */
    public String getTitleForQuiz(int quizId) {
        ResultSet results = execute("SELECT title FROM quiz WHERE id=" + quizId);
        try {
            if (results.next()) {
                return results.getString("title");
            }
        } catch (SQLException ex) {
            System.out.println("Exception thrown while getting title for quiz with ID " + quizId + ": " + ex);
        }
        return "";
    }

}
