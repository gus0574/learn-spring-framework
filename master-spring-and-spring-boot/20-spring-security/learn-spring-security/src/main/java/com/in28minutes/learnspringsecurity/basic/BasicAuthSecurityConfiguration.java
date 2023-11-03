package com.in28minutes.learnspringsecurity.basic;

import static org.springframework.security.config.Customizer.withDefaults;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
public class BasicAuthSecurityConfiguration {

	@Bean
	@Order(SecurityProperties.BASIC_AUTH_ORDER)
	SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
		http.sessionManagement( session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//		http.formLogin(withDefaults());
		http.httpBasic(withDefaults());
		http.headers().frameOptions().sameOrigin();
		http.csrf().disable();
		
		return http.build();
	}
	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		
//		var user = User.withUsername("Lee")
//				.password("{noop}dummy")
//				.roles("USER")
//				.build();
//		
//		var admin = User.withUsername("admin")
//				.password("{noop}admin")
//				.roles("ADMIN")
//				.build();
//		
//		return new InMemoryUserDetailsManager(user, admin);
//	}
	
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
				.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService(DataSource dataSource) {
		
		var user = User.withUsername("Lee")
//				.password("{noop}dummy")
				.password("dummy")
				.passwordEncoder( str -> passwordEncoder().encode(str))
				.roles("USER")
				.build();
		
		var admin = User.withUsername("admin")
//				.password("{noop}admin")
				.password("admin")
				.passwordEncoder( str -> passwordEncoder().encode(str))
				.roles("ADMIN")
				.build();
		
		var jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
		jdbcUserDetailsManager.createUser(user);
		jdbcUserDetailsManager.createUser(admin);
		
		return jdbcUserDetailsManager;
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
