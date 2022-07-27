package dw.editora;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfiguration {

	private  LogoutHandler logoutHandler;
	
	public SecurityConfiguration(LogoutHandler logoutHandler) {
        this.logoutHandler = logoutHandler;
    }
	
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	//requires login expect for "/"
    	http.authorizeRequests()
        .mvcMatchers("/").permitAll()
        .anyRequest().authenticated()
        .and().oauth2Login();
    	
    	return http.oauth2Login()
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .addLogoutHandler(logoutHandler)
                .and().build();
    }
}
