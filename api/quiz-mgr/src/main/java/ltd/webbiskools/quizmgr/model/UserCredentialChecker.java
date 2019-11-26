package ltd.webbiskools.quizmgr.model;

import org.springframework.stereotype.Component;

@Component
public class UserCredentialChecker extends DatabaseAccessor {

    public boolean check(String userName, String password) {
        // placeholder
        return true;
    }

}
