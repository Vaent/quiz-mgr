package ltd.webbiskools.quizmgr.routes;

import ltd.webbiskools.quizmgr.model.QuizInfoRetriever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/quizzes")
public class QuizViewsController {

    @Autowired
    QuizInfoRetriever quizInfoRetriever;

    @GetMapping()
    public String quizzes(ModelMap model) {
        model.addAttribute("quizzes", quizInfoRetriever.getAllQuizzes());
        return "quizzes";
    }

    @GetMapping("/{quizId}/{quizTitle}")
    public String questions(ModelMap model, @PathVariable int quizId, @PathVariable String quizTitle) {
        model.addAttribute("questions", quizInfoRetriever.getQuestionsForQuiz(quizId));
        String refTitle = quizInfoRetriever.getTitleForQuiz(quizId);
        model.addAttribute("quizTitle", (refTitle.equals("") ? quizTitle : refTitle));
        return "questions";
    }

}
