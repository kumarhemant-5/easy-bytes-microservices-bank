package com.eazybytes.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication

/*
    @ComponentScans({ @componentScan("com.eazybytes.accounts.controller") })
    @EnableJpaRepositories("com.eazybytes.accounts.repository")
    @EntityScan("com.eazybytes.accounts.model")

    }) */
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
			title = "Accounts microservice REST API Documentation",
			description = "Eazy Bank Accounts Microservice REST API Documentation",
			version = "V1.0.0",
			contact = @Contact(
					name = "Madan Reddy",
					email = "tutor@eazybytes.com",
					url = "https://www.eazybytes.com"
			),

		    license = @License(
			    name = "Apache 2.0",
			    url = "https://www.eazybytes.com"
		    )
		),
		externalDocs = @ExternalDocumentation(
				description = "Eazy Bank Accounts Microservice REST API Documentation",
				url = "https://www.eazybytes.com"
		)

)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
