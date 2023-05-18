package com.resourceradar.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    TokenService tokenService;

    @Autowired
    private EntryPoint entryPoint;


    // read the clientId from @value annotation from properties file

    @Bean
    public AuthenticationEntryPoint myAuthenticationEntryPoint() {
        return new EntryPoint();
    }
    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/swagger-ui/index.htm",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger*/**",
            "/configuration/**",
            "/webjars/**",
            "/swagger-ui/**",
            "/social"
            // other public endpoints of your API may be appended to this array
    };


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth.requestMatchers(AUTH_WHITELIST).permitAll().anyRequest().authenticated()).exceptionHandling();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authenticationProvider(new GoogleAuthProvider()).exceptionHandling()
                .authenticationEntryPoint(myAuthenticationEntryPoint());
//     ((request, response, e) -> {
//        response.setStatus(HttpStatus.UNAUTHORIZED.value());
//            response.setContentType("application/json");
//            response.getWriter().write("{ \r\n"
//                 + "  \"Error message\": \"You are not authenticated/Token Expired\",\r\n"
//                 + "  \"Status\": \"False\",\r\n"
//                 + "  \"Status code\": \"401\"\r\n"
//                 + "}" );
//        });


        http.apply(MyCustomDsl.customDsl(tokenService));
        http.csrf().disable();
        return http.build();
    }







}