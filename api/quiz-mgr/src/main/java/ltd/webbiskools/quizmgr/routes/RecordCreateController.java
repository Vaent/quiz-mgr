package ltd.webbiskools.quizmgr.routes;

import javax.servlet.http.HttpServletResponse;
import ltd.webbiskools.quizmgr.model.QuizRecordEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/record/create")
public class RecordCreateController {

    @Autowired
    QuizRecordEditor quizRecordEditor;

    @PutMapping("/answer")
    public void createAnswer(HttpServletResponse response, @RequestParam int questionId, @RequestParam String answerText) {
        boolean success = quizRecordEditor.createAnswer(questionId, answerText);
        System.out.println(success ? "Answer created" : "Answer not created");
        response.setHeader("answerCreationStatus", String.valueOf(success));
    }

}
