package com.in28minutes.learnspringsecurity.jwt;

import static org.springframework.security.config.Customizer.withDefaults;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
public class JwtSecurityConfiguration {

	@Bean
	@Order(SecurityProperties.BASIC_AUTH_ORDER)
	SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.httpBasic(withDefaults());
		http.headers().frameOptions().sameOrigin();
		http.csrf().disable();

		http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);

		return http.build();
	}

	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
				.addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION).build();
	}

	@Bean
	public UserDetailsService userDetailsService(DataSource dataSource) {

		var user = User.withUsername("Lee")
//				.password("{noop}dummy")
				.password("dummy").passwordEncoder(str -> passwordEncoder().encode(str)).roles("USER").build();

		var admin = User.withUsername("admin")
//				.password("{noop}admin")
				.password("admin").passwordEncoder(str -> passwordEncoder().encode(str)).roles("ADMIN").build();

		var jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
		jdbcUserDetailsManager.createUser(user);
		jdbcUserDetailsManager.createUser(admin);

		return jdbcUserDetailsManager;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Step 1, Create Key Pair 
	@Bean
	public KeyPair keyPair() {
		try {
			var keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(2048);

			return keyPairGenerator.generateKeyPair();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	// Step 2, Create RSA Key(public, private) Using Key Pair for .get~()
	@Bean
	public RSAKey rsaKey(KeyPair keyPair) {
		return new RSAKey
				.Builder((RSAPublicKey) keyPair.getPublic())
				.privateKey(keyPair.getPrivate())
				.keyID(UUID.randomUUID().toString())
				.build();
	}
	
	// Step 3, Create JWK Resource and JWK Set Using RSA key
	@Bean
	public JWKSource<SecurityContext> jwkSource(RSAKey rsaKey) {
		var jwkSet = new JWKSet(rsaKey);
		
		// override 해야할 것을 ramda 식으로 간단하게 표현
		return (jwkSelector, context) -> jwkSelector.select(jwkSet);
	}
	
	// Step 4, Create Decoder Logic(=RSA public key) Using Nimbus
	@Bean
	public JwtDecoder jwtDecoder(RSAKey rsaKey) throws JOSEException {
		return NimbusJwtDecoder.withPublicKey(rsaKey.toRSAPublicKey()).build();
	}
	
	// Step 5, Create Encoder Logic Using Nimbus
	@Bean
	public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
		return new NimbusJwtEncoder(jwkSource);
	}
	
	
}
