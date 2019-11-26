package ltd.webbiskools.quizmgr.routes;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/quizzes")
public class QuizViewsController {

    @GetMapping()
    public String quizzes() {
        return "quizzes";
    }

    @GetMapping("/{quizTitle}")
    public String questions(ModelMap model, @PathVariable String quizTitle) {
        model.addAttribute(quizTitle);
        return "questions";
    }

}
