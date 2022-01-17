package br.com.santander.challenge.config.documentation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfig {

	@Value("${api.version}")
	private String appVersion;
	@Value("${api.description}")
	private String appDescription;

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(getInfo());
	}

	private Info getInfo() {
		return new Info().title("API Challenge Bank F1rst - Santander").version(appVersion).description(appDescription)
				.contact(new Contact().name("Leandro Ferreira").email("leandro.ferreira777@gmail.com"));
	}

}