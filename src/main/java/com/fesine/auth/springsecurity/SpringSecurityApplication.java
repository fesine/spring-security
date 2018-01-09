package com.fesine.auth.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration
		.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

	@GetMapping("/")
	public String home(){
		return "hello spring boot";
	}

	@GetMapping("/hello")
	public String hello(){
		return "hello spring security";
	}

	/**
	 * 只有admin的权限才可以访问
	 * 使用此方法需要加新的注解
	 * @EnableGlobalMethodSecurity(prePostEnabled = true)
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/roleAuth")
	public String roleAuth(){
		return "admin auth";
	}

}
