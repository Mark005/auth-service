package com.bmo.common.authservice;

import com.bmo.common.authservice.model.TokenBody;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class AuthServiceApplicationTests {

	@Test
	void contextLoads() {
//		String secret = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";
//
//		String jwtToken = Jwts.builder()
//				.claim("tokenBody", TokenBody.builder().userId(UUID.randomUUID()).build())
//				.setId(UUID.randomUUID().toString())
//				.setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
//				.setExpiration(Date.from(ZonedDateTime.now().plus(5L, ChronoUnit.HOURS).toInstant()))
//				.signWith(SignatureAlgorithm.HS256, secret)
//				.compact();
//
//
//		Jws<Claims> claimsJws = Jwts.parser()
//				.setSigningKey(secret)
//				.parseClaimsJws(jwtToken);
//
//		Claims claims = claimsJws.getBody();
//
//		String subject = claims.getSubject();
//		String email = claims.get("email", String.class);
//
//
//		System.out.println(subject);
	}

}
