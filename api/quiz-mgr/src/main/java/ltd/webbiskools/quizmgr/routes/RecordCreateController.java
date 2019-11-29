package ltd.webbiskools.quizmgr.routes;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/record/create")
public class RecordCreateController {

    @PutMapping("/answer")
    public void createAnswer(HttpServletResponse response, @RequestParam Integer questionId, @RequestParam String answerText) {

    }

}
