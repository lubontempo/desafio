package br.bb.desafio.bontempo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"br.bb.desafio.bontempo"})
public class CotacaoDolarApp {

	public static void main(String[] args) {
		SpringApplication.run(CotacaoDolarApp.class, args);
	}

}
