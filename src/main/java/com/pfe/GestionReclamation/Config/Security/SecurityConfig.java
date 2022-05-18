package com.pfe.GestionReclamation.Config.Security;
import com.pfe.GestionReclamation.service.User.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@EnableWebSecurity
@Configuration
@Profile("development")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer{
    @Autowired
    private UserDetailsService userServiceDetails;

    @Autowired
    private JwtAuthenticationFilter authenticationFilter;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
//                .antMatchers("/auth/login").permitAll()
                .anyRequest()
                .authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.cors();
    }

    @Override
    @Autowired
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        // configure AuthenticationManager so that it knows from where to load
        // user for matching credentials
        // Use BCryptPasswordEncoder
        authenticationManagerBuilder.userDetailsService(userServiceDetails).passwordEncoder(passwordEncoder);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .exposedHeaders("Authorization");
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

}

