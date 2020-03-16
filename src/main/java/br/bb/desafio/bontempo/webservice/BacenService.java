package br.bb.desafio.bontempo.webservice;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

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
//		CotacaoDolarWS result = mock(data);
		return result;
	}

	private CotacaoDolarWS mock(String data) {
		String reverseData = null;
		try {
			Date objDate = new SimpleDateFormat("dd-MM-yyyy").parse(data);
			reverseData = new SimpleDateFormat("yyyy-MM-dd").format(objDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		CotacaoDolarWS result = new CotacaoDolarWS();
		result.setDataHoraCotacao(reverseData + " 13:04:43.137");
		result.setCotacaoCompra(getRandomValue().toString());
		result.setCotacaoVenda(getRandomValue().toString());
		return result;
	}

	private Double getRandomValue() {
		Double vl1 = new Random().nextDouble() * 5;
		if (vl1.doubleValue() < 1) {
			vl1 = vl1 + 1;
		}
		Double vlC = BigDecimal.valueOf(vl1).setScale(4, RoundingMode.HALF_UP).doubleValue();
		return vlC;
	}
}
