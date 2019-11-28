package ltd.webbiskools.quizmgr.security;

import java.util.Map;
import ltd.webbiskools.quizmgr.model.UserCredentialChecker;
import ltd.webbiskools.quizmgr.model.dbmappings.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserCredentialChecker userCredentialChecker;

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        Map<Integer, String> permissions = userCredentialChecker.getAllRefPermissions();
        for (UserDetails user : userCredentialChecker.getAllUserDetails()) {
            manager.createUser(User.withDefaultPasswordEncoder()
                .username(user.getUserName())
                .password(user.getPassword())
                .roles(permissions.get(user.getPermission()))
                .build());
        }
        return manager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .anyRequest().authenticated()
        .and().formLogin()
            .loginPage("/login")
            .permitAll()
            .successForwardUrl("/login")
        .and().logout();
    }

}
