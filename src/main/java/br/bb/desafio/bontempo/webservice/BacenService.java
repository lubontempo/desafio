package br.bb.desafio.bontempo.webservice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.bb.desafio.bontempo.webservice.dto.CotacaoDolarWS;

@Component
public class BacenService {

	/**
	 * @param data Data da cotação. Formato esperado: DD-MM-AAAA
	 * @return
	 */
	public CotacaoDolarWS callCotacaoPorData(String data) {
		String reverseData = null;
		try {
			Date objDate = new SimpleDateFormat("dd-MM-yyyy").parse(data);
			reverseData = new SimpleDateFormat("MM-dd-yyyy").format(objDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		RestTemplate template = new RestTemplate();
		String dados = template.getForObject("https://olinda.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata/CotacaoDolarDia(dataCotacao=@dataCotacao)?"
				+ "@dataCotacao='" + reverseData + "'&$format=json", String.class);
		CotacaoDolarWS result = null;
		try {
			final ObjectNode node = new ObjectMapper().readValue(dados, ObjectNode.class);
			node.remove("@odata.context");
			node.arrayNode();

			String obj = node.get("value").toString();
			CotacaoDolarWS[] cotacaoValue = new ObjectMapper().readValue(obj, CotacaoDolarWS[].class);
			if (cotacaoValue.length > 0) {
				result = cotacaoValue[0];
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<CotacaoDolarWS> callCotacaoPorMes(String mes) {
		if (mes.length() == 1) {
			mes = "0" + mes;
		}
		RestTemplate template = new RestTemplate();
		String dados = template.getForObject("https://olinda.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata/CotacaoDolarPeriodo(dataInicial=@dataInicial,dataFinalCotacao=@dataFinalCotacao)?"
				+ "@dataInicial='" + mes + "-01-2020'&@dataFinalCotacao='" + mes + "-29-2020'&$top=100&$format=json", String.class);
		List<CotacaoDolarWS> result = new ArrayList<CotacaoDolarWS>();
		try {
			final ObjectNode node = new ObjectMapper().readValue(dados, ObjectNode.class);
			node.remove("@odata.context");
			node.arrayNode();

			String obj = node.get("value").toString();
			CotacaoDolarWS[] cotacaoValue = new ObjectMapper().readValue(obj, CotacaoDolarWS[].class);
			if (cotacaoValue.length > 0) {
				for (int i = 0; i < cotacaoValue.length; i++) {
					result.add(cotacaoValue[i]);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
