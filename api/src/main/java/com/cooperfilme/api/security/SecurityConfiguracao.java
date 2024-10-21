package com.cooperfilme.api.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
public class SecurityConfiguracao{
	
	private static String[] PATH_PUBLICO_POST = {"/api/v1/auth/login", "/api/v1/roteiro"};
	private static String[] PATH_PUBLICO_GET = {"/api/v1/roteiro/cliente"};
	
	@Autowired
	private JWTUtil jwtUtil;
	
	
	@Bean
    UserDetailsService userDetailsService() {
        return new UsuarioDetalheService();
    }
	
	@Bean
	BCryptPasswordEncoder bCryptPasswordEnconder() {
		return new BCryptPasswordEncoder();
	}

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception  {

		http.csrf(customizer -> customizer.disable());
		http.cors(cors -> cors.configurationSource(corsConfigurationSource()));
		http.authorizeHttpRequests(request -> 
			request.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
			.requestMatchers(HttpMethod.POST, PATH_PUBLICO_POST).permitAll()
			.requestMatchers(HttpMethod.GET, PATH_PUBLICO_GET).permitAll().anyRequest().authenticated()
		);
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.addFilterBefore(new JWTAutorizacaoFiltro(this.jwtUtil, (UsuarioDetalheService) userDetailsService()), 
			UsernamePasswordAuthenticationFilter.class);
		http.authenticationProvider(authenticationProvider());
		

		return http.build();
	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	     
	    authProvider.setUserDetailsService(userDetailsService());
	    authProvider.setPasswordEncoder(bCryptPasswordEnconder());
	 
	    return authProvider;
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) 
			throws Exception {
	    return authConfig.getAuthenticationManager();
	}
	
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList( "http://localhost:5173", "http://localhost", "http://localhost:81"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
        configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
	}
	
}
