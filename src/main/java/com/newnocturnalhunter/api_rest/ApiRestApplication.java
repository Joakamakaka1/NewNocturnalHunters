package com.newnocturnalhunter.api_rest;

import com.newnocturnalhunter.api_rest.security.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * The type Api rest application.
 */
@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class ApiRestApplication {

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(ApiRestApplication.class, args);
	}

}
