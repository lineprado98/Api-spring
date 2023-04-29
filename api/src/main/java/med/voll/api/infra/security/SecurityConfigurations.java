package med.voll.api.infra.security;

import org.hibernate.jdbc.Expectations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
    

    //Habilitando a autenticação Stateless
    @Bean //
    public SecurityFilterChain  securityFilterChain(HttpSecurity http)throws Exception{
        return http.csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().build();
    }

    @Bean
    public AuthenticationManager autenticationManager(AuthenticationConfiguration configuration)throws Exception{
        return configuration.getAuthenticationManager();

    }

    @Bean 
    public PasswordEncoder passworEncoder(){

        return new BCryptPasswordEncoder();
    }
    
}
