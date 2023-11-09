package com.in28minutes.learnspringsecurity.jwt;

import java.time.Instant;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtAuthenticationResource {
	
	private JwtEncoder jwtEncoder;
	
	public JwtAuthenticationResource(JwtEncoder jwtEncoder) {
		this.jwtEncoder = jwtEncoder;
	}
	
	@PostMapping(path="/authenticate")
	public JwtResponse authenticate(Authentication authentication) {
		// Spring Context가 자동으로 객체를 생성할 수 있도록 new로 만든듯?
		return new JwtResponse(createToken(authentication));
	}

	private String createToken(Authentication authentication) {
		// JWT 헤더, 페이로드, 써티피케이션 을 위한 claim 생성
		var claims = JwtClaimsSet.builder()
			.issuer("self")
			.issuedAt(Instant.now())
			.expiresAt(Instant.now().plusSeconds(60*15))
			.subject(authentication.getName())
			//권한 scope 생성
			.claim("Scope", createScope(authentication))
			.build();
		
		// from(claims)로 초기화 된 객체를 넘겨서 JWS headers and JWT Claims Set 을 Holder 하는 파라미터 객체 생성
		JwtEncoderParameters parameters = JwtEncoderParameters.from(claims);
		
		//JwtParameter 화 된 claims를 encode 하여 토큰 값 생성
		return jwtEncoder.encode(parameters).getTokenValue();
	}

	//권한 Scope 생성
	private String createScope(Authentication authentication) {
		// TODO Auto-generated method stub
		
		return authentication.getAuthorities().stream()
				.map(auth -> auth.getAuthority()).collect(Collectors.joining(" "));
	}
	
}

record JwtResponse(String token) {}