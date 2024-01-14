package trojan.taco.security.config;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig  {

    private final static String[] staticResources  =  {
            "/static/**",
            "/css/**",
            "/images/**",
    };
    private final static String[] developerAccess = {
        "/h2-console/**"
    };

    @Autowired
    private final PasswordEncoder encoder;
    @Autowired
    private final UserDetailsService userDetailsService;

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }

    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(authz -> authz
                        .requestMatchers(staticResources).permitAll()
                        .requestMatchers(developerAccess).permitAll()
                        .requestMatchers("/home","/register").permitAll()
                        .requestMatchers("/design/**", "/orders/**").hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .defaultSuccessUrl("/design")
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                );
        http.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**")
                .disable());
        http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
        return http.build();
    }
}
