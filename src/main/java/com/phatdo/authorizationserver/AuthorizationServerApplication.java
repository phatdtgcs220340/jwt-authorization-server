package com.phatdo.authorizationserver;

import com.phatdo.authorizationserver.authentication.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthorizationServerApplication implements ApplicationRunner {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationServerApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		customUserDetailsService.saveUser("Phat Do", "ddtphat2004@gmail.com", "password");
	}
}
