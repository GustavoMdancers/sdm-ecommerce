package br.unibh.sdm.ecommerce;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;

import br.unibh.sdm.ecommerce.persistance.DynamoDBConfig;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@Import({DynamoDBConfig.class})
@OpenAPIDefinition(info = @Info(title = "Ecommerce API", version = "1.0", description = "API para gerenciamento de uma loja de ecommerce", 
	license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0"),
	contact = @Contact(name = "Suporte espiritual", email = "pedepradeus@ulife.com.br"), 
	termsOfService = "http://empresa.com/termos_uso_api")	)
public class Application {
    
    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        log.info("Iniciando a aplicacao Spring Boot...");
        System.setProperty("server.servlet.context-path", "/ecommerce");
        new SpringApplicationBuilder(Application.class).web(WebApplicationType.SERVLET).run(args);
    }

}
