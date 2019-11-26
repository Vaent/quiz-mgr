package ltd.webbiskools.quizmgr.routes;

import ltd.webbiskools.quizmgr.model.UserCredentialChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserCredentialChecker userCredentialChecker;

    @GetMapping()
    public String login() {
        userCredentialChecker.check("AL", "bar");
        return "login";
    }

}
