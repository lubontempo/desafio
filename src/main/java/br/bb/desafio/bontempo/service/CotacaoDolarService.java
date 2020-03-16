package br.bb.desafio.bontempo.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.bb.desafio.bontempo.entity.CotacaoDolar;
import br.bb.desafio.bontempo.repository.CotacaoDolarRepository;
import br.bb.desafio.bontempo.webservice.BacenService;
import br.bb.desafio.bontempo.webservice.dto.CotacaoDolarWS;

@Service
public class CotacaoDolarService {

	@Autowired
	private CotacaoDolarRepository repository;
	
	@Autowired
	private BacenService bacenWS;
	
	/**
	 * Busca a cotação do dólar armazenada em banco de dados. Caso a informação não esteja registrada ainda,
	 * busca a cotação no WebService do BACEN e registra na base local.
	 * 
	 * @param data Data de coração. Formato esperado: DD-MM-AAAA
	 * @return Objeto {@link br.bb.desafio.bontempo.entity.CotacaoDolar}
	 */
	public CotacaoDolar buscarCotacao(String data) {
		CotacaoDolar result = repository.recuperaCotacaoPorData(data);
		if (result == null) {
			result = buscarCotacaoBacen(data);
		}
		return result;
	}

	private CotacaoDolar buscarCotacaoBacen(String data) {
		CotacaoDolar result = null;
		CotacaoDolarWS cotacaoWS = bacenWS.callCotacaoPorData(data);
		if (cotacaoWS != null) {
			CotacaoDolar cotacaoDB = new CotacaoDolar();
			cotacaoDB.setDataCotacao(data);
			cotacaoDB.setDataRequisicao(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
			cotacaoDB.setDataHoraCotacao(cotacaoWS.getDataHoraCotacao());
			cotacaoDB.setVlCotacaoCompra(cotacaoWS.getCotacaoCompra());
			cotacaoDB.setVlCotacaoVenda(cotacaoWS.getCotacaoVenda());
			
			repository.saveAndFlush(cotacaoDB);
			result = cotacaoDB;
		}
		
		return result;
	}

	public List<CotacaoDolar> buscarCotacaoPorMes(String mes) {
		List<CotacaoDolar> result = new ArrayList<CotacaoDolar>();
		List<CotacaoDolarWS> lista = bacenWS.callCotacaoPorMes(mes);
		for (CotacaoDolarWS cotacaoWS : lista) {
			CotacaoDolar cotacaoDB = new CotacaoDolar();
			cotacaoDB.setDataRequisicao(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
			cotacaoDB.setDataHoraCotacao(cotacaoWS.getDataHoraCotacao());
			cotacaoDB.setVlCotacaoCompra(cotacaoWS.getCotacaoCompra());
			cotacaoDB.setVlCotacaoVenda(cotacaoWS.getCotacaoVenda());
			
			result.add(cotacaoDB);
		}
		
		return result;
	}
}
