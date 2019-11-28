package ltd.webbiskools.quizmgr.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import ltd.webbiskools.quizmgr.model.dbmappings.Permission;
import ltd.webbiskools.quizmgr.model.dbmappings.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserCredentialChecker extends DatabaseAccessor {

    public Map<Integer, String> getAllRefPermissions() {
        ResultSet results = selectFrom(REF_PERMISSIONS_TABLE_NAME, "id", "description");
        Map<Integer, String> map = Permission.mapDetailsFrom(results);
        return map;
    }

    public List<UserDetails> getAllUserDetails() {
        List<UserDetails> list = new ArrayList<>();
        ResultSet results = selectFrom(USER_DETAILS_TABLE_NAME, "user_name", "user_password", "user_permission");
        if (results != null) {
            list.addAll(UserDetails.listFrom(results));
        }
        return list;
    }

    public int getLoggedInPermission() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String pDescription = auth.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .findFirst().orElse("");
        return getAllRefPermissions().entrySet().stream()
            .filter(entry -> ("ROLE_" + entry.getValue()).equals(pDescription))
            .map(Map.Entry::getKey)
            .findFirst().orElse(-1);
    }

}
