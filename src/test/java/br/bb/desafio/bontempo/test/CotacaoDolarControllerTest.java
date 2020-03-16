package br.bb.desafio.bontempo.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.bb.desafio.bontempo.controller.dto.ResultDTO;

@SpringBootTest
class CotacaoDolarControllerTest {

	private RestTemplate restTemplate = new RestTemplate();

	@Test
	public void testRecuperarPorMes() {
		try {
			String uri = String.format("http://localhost:8080/cotacao/buscarPorMes/%s", 2);
			ResultDTO resultDTO =  restTemplate.getForObject(new URI(uri), ResultDTO.class);

			assertNotNull(resultDTO);
			assertNotNull(resultDTO.getStatus());
			assertEquals(true, resultDTO.getList().size() > 0);
		} catch (RestClientException e) {
			e.printStackTrace();
			fail("Erro ao recuperar as cotações do mês (RestClientException)");
		} catch (URISyntaxException e) {
			e.printStackTrace();
			fail("Erro ao recuperar as cotações do mês (URISyntaxException)");
		}
	}
	
	@Test
	public void testRecuperarPorData() {
		try {
			String uri = String.format("http://localhost:8080/cotacao/buscar/%s", "10-03-2020");
			ResultDTO resultDTO =  restTemplate.getForObject(new URI(uri), ResultDTO.class);

			assertNotNull(resultDTO);
			assertNotNull(resultDTO.getStatus());
			assertNotNull(resultDTO.getDto());
		} catch (RestClientException e) {
			e.printStackTrace();
			fail("Erro ao recuperar as cotações da data (RestClientException)");
		} catch (URISyntaxException e) {
			e.printStackTrace();
			fail("Erro ao recuperar as cotações da data (URISyntaxException)");
		}
	}
	
}
