package br.bb.desafio.bontempo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Bean
	public Docket detalheApi() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2);
		docket.select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any())
				.build().apiInfo(this.informacoesApi().build());

		return docket;
	}

	private ApiInfoBuilder informacoesApi() {
		ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
		apiInfoBuilder.title("Cotacao-API");
		apiInfoBuilder.description("API para consulta de cotação do dólar - Desafio BB.");
		apiInfoBuilder.version("1.0");
		apiInfoBuilder.license("Licença - Open Source");
		apiInfoBuilder.contact(contato());
		return apiInfoBuilder;
	}
	
	private Contact contato() {
		return new Contact(
				"Luciana Bontempo",
				"https://github.com/lubontempo", 
				"lubontempo@gmail.com");
	}
}
